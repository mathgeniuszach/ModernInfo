package com.mathgeniuszach.moderninfo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.mathgeniuszach.moderninfo.config.ConfigData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {
    public EntityLivingBaseMixin(World worldIn) { super(worldIn); }

    @Inject(method = "getAlwaysRenderNameTagForRender", at = @At("HEAD"), cancellable = true)
    private void getAlwaysRenderNameTag(CallbackInfoReturnable<Boolean> ci) {
        if (ConfigData.customNamesVisible && this.hasCustomName()) {
            ci.setReturnValue(true);
        }
    }
}
