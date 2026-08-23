package org.foxstudio.dinoessentials.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.foxstudio.dinoessentials.DinoEssentials;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;
import org.foxstudio.dinoessentials.format.Formatter;

/**
 * Chặn tin nhắn chat trước khi broadcast, thay bằng component đã format
 * theo CHAT_FORMAT (prefix + player + suffix + message).
 */
@Mod.EventBusSubscriber(modid = DinoEssentials.MODID)
public final class ChatHandler {

    private ChatHandler() {
    }

    @SubscribeEvent
    public static void onServerChat(ServerChatEvent event) {
        if (!DinoEssentialsConfig.ENABLED.get() || !DinoEssentialsConfig.CHAT_ENABLED.get()) {
            return;
        }
        ServerPlayer player = event.getPlayer();
        String raw = event.getRawText();
        Component formatted = Formatter.chat(player, raw);
        event.setMessage(formatted);
    }
}
