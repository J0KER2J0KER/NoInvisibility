package com.j0ker2j0ker.noinvis.client.mixins;

import com.j0ker2j0ker.noinvis.client.NoinvisClient;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "isInvisibleTo", at = @At("HEAD"), cancellable = true)
    public void isInvisibleTo(CallbackInfoReturnable<Boolean> cir) {
        if (NoinvisClient.CONFIG.noInvis)cir.setReturnValue(false);
    }

}
