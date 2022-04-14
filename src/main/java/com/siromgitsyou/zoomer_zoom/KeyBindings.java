package com.siromgitsyou.zoomer_zoom;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static KeyMapping keyZoom;

    public static void init() {

        keyZoom = new KeyMapping("Toggle Zoom", GLFW.GLFW_KEY_C, "Zoomer Zoom");
        ClientRegistry.registerKeyBinding(keyZoom);

    }

}
