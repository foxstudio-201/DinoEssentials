package org.foxstudio.dinoessentials.mixin.server;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.network.chat.Component;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class ServerGamePacketListenerImplMixin {

    @Inject(method = "onDisconnect", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V"
    ), cancellable = true)
    private void dinoessentials$cancelLeave(Component reason, CallbackInfo ci) {
        if (DinoEssentialsConfig.ENABLED.get() && DinoEssentialsConfig.LEAVE_ENABLED.get()) {
            ci.cancel();
        }
    }
}