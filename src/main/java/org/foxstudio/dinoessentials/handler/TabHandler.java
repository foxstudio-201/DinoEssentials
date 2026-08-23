package org.foxstudio.dinoessentials.handler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.foxstudio.dinoessentials.DinoEssentials;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;
import org.foxstudio.dinoessentials.format.Formatter;

@Mod.EventBusSubscriber(modid = DinoEssentials.MODID)
public final class TabHandler {

    private TabHandler() {
    }

    @SubscribeEvent
    public static void onTabListNameFormat(PlayerEvent.TabListNameFormat event) {
        if (!DinoEssentialsConfig.ENABLED.get() || !DinoEssentialsConfig.TAB_ENABLED.get()) {
            return;
        }
        if (event.getEntity() instanceof ServerPlayer player) {
            event.setDisplayName(Formatter.tab(player));
        }
    }

    @SubscribeEvent
    public static void onNameFormat(PlayerEvent.NameFormat event) {
        if (!DinoEssentialsConfig.ENABLED.get() || !DinoEssentialsConfig.TAB_ENABLED.get()) {
            return;
        }
        if (event.getEntity() instanceof ServerPlayer player) {
            event.setDisplayname(Formatter.tab(player));
        }
    }
}
