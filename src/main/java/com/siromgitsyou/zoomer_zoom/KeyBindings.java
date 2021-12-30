package com.siromgitsyou.zoomer_zoom;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static KeyBinding keyZoom;

    public static void init() {

        keyZoom = new KeyBinding("key.zoomer_zoom.zoom", InputMappings.Type.KEYSYM, GLFW.GLFW_KEY_C, "category.zoomer_zoom.zoom");
        ClientRegistry.registerKeyBinding(keyZoom);

    }

}
