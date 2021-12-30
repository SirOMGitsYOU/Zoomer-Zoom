package com.siromgitsyou.zoomer_zoom;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;

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
        return KeyBindings.keyZoom.isKeyDown();
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
        return mc.gameSettings.smoothCamera;
    }

    private static void enableSmoothCamera() {
        mc.gameSettings.smoothCamera = true;
    }

    private static void disableSmoothCamera() {
        mc.gameSettings.smoothCamera = false;
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
