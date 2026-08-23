package org.foxstudio.dinoessentials.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.foxstudio.dinoessentials.DinoEssentials;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;
import org.foxstudio.dinoessentials.format.Formatter;

@Mod.EventBusSubscriber(modid = DinoEssentials.MODID)
public final class JoinLeaveHandler {

    private JoinLeaveHandler() {
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!DinoEssentialsConfig.ENABLED.get() || !DinoEssentialsConfig.JOIN_ENABLED.get()) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        MinecraftServer server = player.server;
        Component msg = Formatter.joinLeave(DinoEssentialsConfig.JOIN_FORMAT.get(), player);
        server.getPlayerList().broadcastSystemMessage(msg, false);
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (!DinoEssentialsConfig.ENABLED.get() || !DinoEssentialsConfig.LEAVE_ENABLED.get()) {
            return;
        }
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }
        MinecraftServer server = player.server;
        Component msg = Formatter.joinLeave(DinoEssentialsConfig.LEAVE_FORMAT.get(), player);
        server.getPlayerList().broadcastSystemMessage(msg, false);
    }
}
