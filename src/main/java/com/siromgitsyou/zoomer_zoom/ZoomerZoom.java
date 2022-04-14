package com.siromgitsyou.zoomer_zoom;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod("zoomerzoom")
public class ZoomerZoom {

    public ZoomerZoom() {

        if (FMLEnvironment.dist == Dist.CLIENT) {

            try {

                ZoomHandler.init();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

}
