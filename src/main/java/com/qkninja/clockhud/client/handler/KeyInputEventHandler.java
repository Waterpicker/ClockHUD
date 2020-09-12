package com.qkninja.clockhud.client.handler;

import com.qkninja.clockhud.client.settings.KeyBindings;
import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Key;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

/**
 * Handles all results for keyboard events.
 */
public class KeyInputEventHandler implements ClientTickEvents.EndTick {

    private static Key getPressedKeyBinding() {
        if (KeyBindings.TOGGLE.isPressed())
            return Key.TOGGLE;
        else return Key.UNKNOWN;
    }

    @Override
    public void onEndTick(MinecraftClient minecraftClient) {
        if (getPressedKeyBinding() == Key.TOGGLE)
            ConfigValues.INS.guiActive = !ConfigValues.INS.guiActive;
    }
}
