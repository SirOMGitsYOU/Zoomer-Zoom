package com.siromgitsyou.zoomer_zoom;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

@Mod("zoomerzoom")
public class ZoomerZoom {

    private static boolean currentlyZoomed = false;
    private static boolean originalSmoothCameraEnabled = false;
    private static final Minecraft mc = Minecraft.getInstance();

    

    public static final double ZOOM_LEVEL = 0.23;

    public ZoomerZoom() {

        KeyBindings.init();
        ignoreServerOnly();

    }

    // Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
    private void ignoreServerOnly() {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(
                () -> FMLNetworkConstants.IGNORESERVERONLY,
                (Forgeisdumb, whycantforgebemorelikefabric) -> true)
        );
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
