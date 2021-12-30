package com.logicalgeekboy.logical_zoom;

import net.minecraft.client.Minecraft;
import com.logicalgeekboy.keybinds.*;
import net.minecraftforge.fml.common.Mod;

import org.lwjgl.glfw.GLFW;

@Mod("logical_zoom")
public class LogicalZoom {

    private static boolean currentlyZoomed;
    private static boolean originalSmoothCameraEnabled;
    private static final Minecraft mc = Minecraft.getInstance();

    public static final double zoomLevel = 0.23;
    public static final String MODID = "LogicalZoom";


    public static boolean isZooming() {
        return KeyboardInput.zoomKey.isDown();
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
