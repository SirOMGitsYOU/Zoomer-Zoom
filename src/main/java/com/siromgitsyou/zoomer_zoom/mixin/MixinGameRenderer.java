package com.siromgitsyou.zoomer_zoom.mixin;

import net.minecraft.client.renderer.GameRenderer;

import com.siromgitsyou.zoomer_zoom.ZoomerZoom;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {

    @Inject(at = @At("RETURN"), method = "getFOVModifier", cancellable = true)
    public void onGetFOVModifier(CallbackInfoReturnable<Double> info) {

        if(ZoomerZoom.isZooming()) {
            double fov = info.getReturnValue();
            info.setReturnValue(fov * ZoomerZoom.ZOOM_LEVEL);
        }
        ZoomerZoom.manageSmoothCamera();

    }

}
