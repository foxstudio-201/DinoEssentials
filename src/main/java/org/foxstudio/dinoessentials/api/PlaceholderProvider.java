package org.foxstudio.dinoessentials.api;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Provider cho placeholder tuỳ biến trong format.
 * Ví dụ: mod dinorace đăng ký placeholder "level" để chat/tab có thể dùng {level}.
 */
@FunctionalInterface
public interface PlaceholderProvider {

    /**
     * @param player player đang được format
     * @return Component hiển thị cho placeholder, không được null
     */
    Component get(ServerPlayer player);
}
