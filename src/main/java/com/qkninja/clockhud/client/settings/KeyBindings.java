package com.qkninja.clockhud.client.settings;

import com.qkninja.clockhud.reference.Names;
import net.minecraft.client.options.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final KeyBinding TOGGLE = new KeyBinding(Names.Keys.TOGGLE, GLFW.GLFW_KEY_COMMA, Names.Keys.CATEGORY);
}
