package org.foxstudio.dinoessentials.format;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.level.ServerPlayer;
import org.foxstudio.dinoessentials.api.DinoEssentialsAPI;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;

public final class Formatter {

    private Formatter() {
    }

    public static Component chat(ServerPlayer player, String message) {
        return format(DinoEssentialsConfig.CHAT_FORMAT.get(), player, message);
    }

    public static Component tab(ServerPlayer player) {
        return format(DinoEssentialsConfig.TAB_FORMAT.get(), player, "");
    }

    public static Component joinLeave(String template, ServerPlayer player) {
        return format(template, player, "");
    }

    private static Component format(String template, ServerPlayer player, String message) {
        MutableComponent out = Component.literal("");
        int i = 0;
        int len = template.length();
        while (i < len) {
            char c = template.charAt(i);
            if (c == '{') {
                int end = template.indexOf('}', i + 1);
                if (end > i) {
                    String key = template.substring(i + 1, end);
                    Component resolved = resolve(key, player, message);
                    if (resolved != null) {
                        out.append(resolved);
                        i = end + 1;
                        continue;
                    }
                }
                out.append(colorize("{"));
                i++;
                continue;
            }
            int next = template.indexOf('{', i);
            if (next < 0) {
                out.append(colorize(template.substring(i)));
                break;
            }
            out.append(colorize(template.substring(i, next)));
            i = next;
        }
        return out;
    }

    private static Component resolve(String key, ServerPlayer player, String message) {
        switch (key) {
            case "player" -> {
                return safe(player.getName());
            }
            case "username" -> {
                return Component.literal(player.getGameProfile().getName());
            }
            case "prefix" -> {
                return withDefault(DinoEssentialsConfig.DEFAULT_PREFIX.get())
                        .append(DinoEssentialsAPI.buildPrefix(player));
            }
            case "suffix" -> {
                return withDefault(DinoEssentialsConfig.DEFAULT_SUFFIX.get())
                        .append(DinoEssentialsAPI.buildSuffix(player));
            }
            case "message" -> {
                return Component.literal(message);
            }
            default -> {
                return DinoEssentialsAPI.resolvePlaceholder(key, player);
            }
        }
    }

    private static MutableComponent withDefault(String cfg) {
        return (MutableComponent) colorize(cfg);
    }

    private static Component safe(Component c) {
        return c == null ? Component.empty() : c;
    }

    public static Component colorize(String input) {
        if (input == null || input.isEmpty()) {
            return Component.empty();
        }
        MutableComponent root = Component.literal("");
        StringBuilder cur = new StringBuilder();
        Style style = Style.EMPTY;

        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '&' && i + 1 < input.length()) {
                char code = input.charAt(i + 1);
                if (code == '#' && i + 7 < input.length()) {
                    String hex = input.substring(i + 2, i + 8);
                    try {
                        int rgb = Integer.parseInt(hex, 16);
                        flush(root, cur, style);
                        style = style.withColor(TextColor.fromRgb(rgb));
                        i += 8;
                        continue;
                    } catch (NumberFormatException ignored) {
                    }
                }
                ChatFormatting cf = ChatFormatting.getByCode(code);
                if (cf != null) {
                    flush(root, cur, style);
                    style = style.applyLegacyFormat(cf);
                    i += 2;
                    continue;
                }
            }
            cur.append(c);
            i++;
        }
        flush(root, cur, style);
        return root;
    }

    private static void flush(MutableComponent root, StringBuilder cur, Style style) {
        if (cur.length() > 0) {
            root.append(Component.literal(cur.toString()).withStyle(style));
            cur.setLength(0);
        }
    }
}
