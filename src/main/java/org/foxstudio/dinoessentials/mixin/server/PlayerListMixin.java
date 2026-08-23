package org.foxstudio.dinoessentials.mixin.server;

import net.minecraft.server.players.PlayerList;
import org.foxstudio.dinoessentials.config.DinoEssentialsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public abstract class PlayerListMixin {

    @Inject(method = "placeNewPlayer", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/players/PlayerList;broadcastSystemMessage(Lnet/minecraft/network/chat/Component;Z)V",
            ordinal = 0
    ), cancellable = true)
    private void dinoessentials$cancelJoin(CallbackInfo ci) {
        if (DinoEssentialsConfig.ENABLED.get() && DinoEssentialsConfig.JOIN_ENABLED.get()) {
            ci.cancel();
        }
    }
}
