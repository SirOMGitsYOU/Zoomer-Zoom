package com.siromgitsyou.zoomer_zoom;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;

public class ZoomHandler {

    private static Minecraft mc = Minecraft.getInstance();

    public static boolean isZoomed = false;
    public static double zoomFactor = 0.25D;

    private static boolean cachedSmoothCamera;

    public static void init() {

        KeyBindings.init();

        MinecraftForge.EVENT_BUS.register(new ZoomHandler());

    }

    /**
     * Returns the modified/zoomed FOV.
     */
    public static double handleZoom(double fov) {

        if (KeyBindings.keyZoom.isDown()) {

            if (!isZoomed) {
                cachedSmoothCamera = mc.options.smoothCamera;
            }
            mc.options.smoothCamera = true;
            isZoomed = true;

            double modifiedZoom = fov * zoomFactor;
            if (modifiedZoom < 1.0D) {
                modifiedZoom = 1.0D;
            }
            if (modifiedZoom > 170.0D) {
                modifiedZoom = 170.0D;
            }
            return (modifiedZoom);

        } else {

            if (isZoomed) {
                mc.options.smoothCamera = cachedSmoothCamera;
                isZoomed = false;
            }

        }

        return fov;

    }

}
