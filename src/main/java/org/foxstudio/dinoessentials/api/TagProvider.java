package org.foxstudio.dinoessentials.api;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

@FunctionalInterface
public interface TagProvider {

    Component get(ServerPlayer player);
}
