package com.logicalgeekboy.logical_zoom.mixin;

import com.logicalgeekboy.logical_zoom.LogicalZoom;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.renderer.GameRenderer;


@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
public class LogicalZoomMixin {

    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"), cancellable = true)
    public void getZoomLevel(CallbackInfoReturnable<Double> callbackInfo) {
        if(LogicalZoom.isZooming()) {
            double fov = callbackInfo.getReturnValue();
            callbackInfo.setReturnValue(fov * LogicalZoom.zoomLevel);
        }

        LogicalZoom.manageSmoothCamera();
    }
}
