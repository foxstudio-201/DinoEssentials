package org.foxstudio.dinoessentials.api;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Provider trả về một Component prefix cho player.
 * Mod khác đăng ký qua {@link DinoEssentialsAPI#registerPrefix(String, TagProvider)}.
 */
@FunctionalInterface
public interface TagProvider {

    /**
     * @param player player đang được format
     * @return Component prefix/suffix, có thể là Component.empty() nếu không có
     */
    Component get(ServerPlayer player);
}
