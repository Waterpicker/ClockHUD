package com.qkninja.clockhud.client.handler;

import com.qkninja.clockhud.client.settings.KeyBindings;
import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Key;
import net.minecraft.client.MinecraftClient;

/**
 * Handles all results for keyboard events.
 */
public class KeyInputEventHandler {

    private static Key getPressedKeyBinding() {
        if (KeyBindings.TOGGLE.wasPressed())
            return Key.TOGGLE;KeyBindings.TOGGLE.wasPressed()
        else return Key.UNKNOWN;
    }

    public static void onEndTick(MinecraftClient minecraftClient) {
        if (getPressedKeyBinding() == Key.TOGGLE)
            ConfigValues.INS.guiActive = !ConfigValues.INS.guiActive;
    }
}
