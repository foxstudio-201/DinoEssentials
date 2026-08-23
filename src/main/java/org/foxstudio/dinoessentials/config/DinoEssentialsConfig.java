package org.foxstudio.dinoessentials.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class DinoEssentialsConfig {

    public static final ForgeConfigSpec SERVER_SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.BooleanValue CHAT_ENABLED;
    public static final ForgeConfigSpec.BooleanValue TAB_ENABLED;
    public static final ForgeConfigSpec.BooleanValue JOIN_ENABLED;
    public static final ForgeConfigSpec.BooleanValue LEAVE_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> TAB_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> JOIN_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> LEAVE_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> DEFAULT_PREFIX;
    public static final ForgeConfigSpec.ConfigValue<String> DEFAULT_SUFFIX;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        b.push("general");
        ENABLED = b
                .comment("Bật/tắt toàn bộ DinoEssentials.")
                .define("enabled", true);
        CHAT_ENABLED = b
                .comment("Bật/tắt định dạng lại tin nhắn chat.")
                .define("chat.enabled", true);
        TAB_ENABLED = b
                .comment("Bật/tắt đổi tên hiển thị trong tab list.")
                .define("tab.enabled", true);
        b.pop();

        b.push("chat");
        CHAT_FORMAT = b
                .comment("Định dạng tin nhắn chat.",
                        "Placeholder: {prefix} {player} {suffix} {message} {username}",
                        "Màu dùng & kèm chữ: &0-9, &a-f, &l, &o, &n, &m, &k, &r, &#RRGGBB")
                .define("format", "{prefix}{player}{suffix}&r: {message}");
        b.pop();

        b.push("tab");
        TAB_FORMAT = b
                .comment("Định dạng tên hiển thị trong tab list.",
                        "Placeholder: {prefix} {player} {suffix} {username} {team}",
                        "{team} = tên đội (do mod dinocore đăng ký).")
                .define("format", "{prefix}{team}{player}{suffix}");
        b.pop();

        b.push("join");
        JOIN_ENABLED = b
                .comment("Bật/tắt thông báo khi người chơi vào server.")
                .define("enabled", true);
        JOIN_FORMAT = b
                .comment("Thông báo khi người chơi vào server.",
                        "Placeholder: {prefix} {player} {suffix} {username} {team}")
                .define("format", "&a+ &e{player}");
        b.pop();

        b.push("leave");
        LEAVE_ENABLED = b
                .comment("Bật/tắt thông báo khi người chơi rời server.")
                .define("enabled", true);
        LEAVE_FORMAT = b
                .comment("Thông báo khi người chơi rời server.",
                        "Placeholder: {prefix} {player} {suffix} {username} {team}")
                .define("format", "&c- &e{player}");
        b.pop();

        b.push("defaults");
        DEFAULT_PREFIX = b
                .comment("Prefix mặc định cho mọi người chơi. Bỏ trống nếu không dùng.")
                .define("prefix", "");
        DEFAULT_SUFFIX = b
                .comment("Suffix mặc định cho mọi người chơi. Bỏ trống nếu không dùng.")
                .define("suffix", "");
        b.pop();

        SERVER_SPEC = b.build();
    }

    private DinoEssentialsConfig() {
    }
}
