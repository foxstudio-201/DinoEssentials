package org.foxstudio.dinoessentials.api;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Registry trung tâm — các mod khác dùng đây để gắn prefix/suffix/placeholder.
 * Đăng ký khi mod init (constructor) là an toàn nhất.
 */
public final class DinoEssentialsAPI {

    private static final CopyOnWriteArrayList<NamedTagProvider> PREFIXES = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<NamedTagProvider> SUFFIXES = new CopyOnWriteArrayList<>();
    private static final Map<String, PlaceholderProvider> PLACEHOLDERS = new LinkedHashMap<>();

    private DinoEssentialsAPI() {
    }

    /**
     * Đăng ký prefix. Nhiều mod đăng ký sẽ được nối theo thứ tự đăng ký.
     */
    public static void registerPrefix(String modId, TagProvider provider) {
        PREFIXES.add(new NamedTagProvider(modId, provider));
    }

    /**
     * Đăng ký suffix.
     */
    public static void registerSuffix(String modId, TagProvider provider) {
        SUFFIXES.add(new NamedTagProvider(modId, provider));
    }

    /**
     * Đăng ký placeholder tuỳ biến. Ví dụ "level" → chat format có thể dùng {level}.
     */
    public static synchronized void registerPlaceholder(String name, PlaceholderProvider provider) {
        PLACEHOLDERS.put(name, provider);
    }

    public static Component buildPrefix(ServerPlayer player) {
        Component result = Component.literal("");
        for (NamedTagProvider p : PREFIXES) {
            result = result.copy().append(safeTag(p.provider, player));
        }
        return result;
    }

    public static Component buildSuffix(ServerPlayer player) {
        Component result = Component.literal("");
        for (NamedTagProvider p : SUFFIXES) {
            result = result.copy().append(safeTag(p.provider, player));
        }
        return result;
    }

    public static Component resolvePlaceholder(String name, ServerPlayer player) {
        PlaceholderProvider p = PLACEHOLDERS.get(name);
        return p == null ? null : safePlaceholder(p, player);
    }

    private static Component safeTag(TagProvider provider, ServerPlayer player) {
        try {
            return provider.get(player);
        } catch (Throwable t) {
            return Component.empty();
        }
    }

    private static Component safePlaceholder(PlaceholderProvider provider, ServerPlayer player) {
        try {
            return provider.get(player);
        } catch (Throwable t) {
            return Component.empty();
        }
    }

    private record NamedTagProvider(String modId, TagProvider provider) {
    }
}
