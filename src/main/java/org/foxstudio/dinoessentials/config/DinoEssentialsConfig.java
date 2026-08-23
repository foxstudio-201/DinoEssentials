package org.foxstudio.dinoessentials.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class DinoEssentialsConfig {

    public static final ForgeConfigSpec SERVER_SPEC;

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
        ENABLED = b.define("enabled", true);
        CHAT_ENABLED = b.define("chat.enabled", true);
        TAB_ENABLED = b.define("tab.enabled", true);
        b.pop();

        b.push("chat");
        CHAT_FORMAT = b.define("format", "{prefix}{player}{suffix}&r: {message}");
        b.pop();

        b.push("tab");
        TAB_FORMAT = b.define("format", "{prefix}{team}{player}{suffix}");
        b.pop();

        b.push("defaults");
        DEFAULT_PREFIX = b.define("prefix", "");
        DEFAULT_SUFFIX = b.define("suffix", "");
        b.pop();

        SERVER_SPEC = b.build();
    }

    private DinoEssentialsConfig() {
    }
}
