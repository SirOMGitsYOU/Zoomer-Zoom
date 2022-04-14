package com.siromgitsyou.zoomer_zoom;

import com.siromgitsyou.zoomer_zoom.KeyBindings;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;


@Mod("zoomerzoom")
public class ZoomerZoom {

    private static boolean currentlyZoomed = false;
    private static boolean originalSmoothCameraEnabled = false;
    private static final Minecraft mc = Minecraft.getInstance();

    public static final double ZOOM_LEVEL = 0.23;

    public ZoomerZoom() {

        KeyBindings.init();

    }

    public static boolean isZooming() {
        return KeyBindings.keyZoom.isDown();
    }

    public static void manageSmoothCamera() {

        if (zoomStarting()) {
            zoomStarted();
            enableSmoothCamera();
        }

        if (zoomStopping()) {
            zoomStopped();
            resetSmoothCamera();
        }

    }

    private static boolean isSmoothCamera() {
        return mc.options.smoothCamera;
    }

    private static void enableSmoothCamera() {
        mc.options.smoothCamera = true;
    }

    private static void disableSmoothCamera() {
        mc.options.smoothCamera = false;
    }

    private static boolean zoomStarting() {
        return isZooming() && !currentlyZoomed;
    }

    private static boolean zoomStopping() {
        return !isZooming() && currentlyZoomed;
    }

    private static void zoomStarted() {
        originalSmoothCameraEnabled = isSmoothCamera();
        currentlyZoomed = true;
    }

    private static void zoomStopped() {
        currentlyZoomed = false;
    }

    private static void resetSmoothCamera() {
        if (originalSmoothCameraEnabled) {
            enableSmoothCamera();
        } else {
            disableSmoothCamera();
        }
    }

}
