package org.foxstudio.dinoessentials.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * Cấu hình DinoEssentials — định dạng chat, tab list, prefix/suffix mặc định.
 * File sinh ra: config/dinoessentials-common.toml
 *
 * Placeholder được hỗ trợ trong {@code format}:
 *   {player}   - tên hiển thị của người chơi (đã gồm prefix/suffix từ mod khác)
 *   {username} - tên tài khoản thô
 *   {prefix}   - prefix (config + mod đăng ký qua API)
 *   {suffix}   - suffix (config + mod đăng ký qua API)
 *   {level}    - level nếu mod dinorace cài (placeholder do mod khác đăng ký)
 *   {message}  - nội dung tin nhắn chat (chỉ dùng trong chat format)
 *
 * Màu: dùng cú pháp &amp;0-9, &amp;a-f, &amp;l, &amp;o, &amp;n, &amp;m, &amp;k, &amp;r, &amp;#RRGGBB
 */
public final class DinoEssentialsConfig {

    public static final ForgeConfigSpec COMMON_SPEC;

    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.BooleanValue CHAT_ENABLED;
    public static final ForgeConfigSpec.BooleanValue TAB_ENABLED;
    public static final ForgeConfigSpec.ConfigValue<String> CHAT_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> TAB_FORMAT;
    public static final ForgeConfigSpec.ConfigValue<String> DEFAULT_PREFIX;
    public static final ForgeConfigSpec.ConfigValue<String> DEFAULT_SUFFIX;

    static {
        ForgeConfigSpec.Builder b = new ForgeConfigSpec.Builder();

        b.push("general");
        ENABLED = b
                .comment("Tổng công tắc bật/tắt toàn bộ DinoEssentials.",
                        "Set false để tắt hết mà không cần gỡ mod.")
                .define("enabled", true);
        CHAT_ENABLED = b
                .comment("Bật định dạng lại tin nhắn chat theo CHAT_FORMAT.")
                .define("chat.enabled", true);
        TAB_ENABLED = b
                .comment("Bật đổi tên hiển thị trong tab list theo TAB_FORMAT.")
                .define("tab.enabled", true);
        b.pop();

        b.push("chat");
        CHAT_FORMAT = b
                .comment("Định dạng tin nhắn chat.",
                        "Placeholder: {prefix}{player}{suffix}{message}{username}",
                        "Mặc định: {prefix}{player}{suffix}&r: {message}")
                .define("format", "{prefix}{player}{suffix}&r: {message}");
        b.pop();

        b.push("tab");
        TAB_FORMAT = b
                .comment("Định dạng tên hiển thị trong tab list.",
                        "Placeholder: {prefix}{player}{suffix}{username}",
                        "Mặc định: {prefix}{player}{suffix}")
                .define("format", "{prefix}{player}{suffix}");
        b.pop();

        b.push("defaults");
        DEFAULT_PREFIX = b
                .comment("Prefix mặc định áp cho mọi người chơi.",
                        "Mod khác có thể đăng ký prefix riêng qua API và sẽ được nối sau prefix này.",
                        "Bỏ trống để không dùng.")
                .define("prefix", "");
        DEFAULT_SUFFIX = b
                .comment("Suffix mặc định áp cho mọi người chơi.",
                        "Bỏ trống để không dùng.")
                .define("suffix", "");
        b.pop();

        COMMON_SPEC = b.build();
    }

    private DinoEssentialsConfig() {
    }
}
