package com.qkninja.clockhud.proxy;

import com.qkninja.clockhud.client.gui.GuiClock;
import com.qkninja.clockhud.client.gui.GuiDayCount;
import com.qkninja.clockhud.client.handler.KeyInputEventHandler;
import com.qkninja.clockhud.client.settings.KeyBindings;
import com.qkninja.clockhud.reference.ConfigValues;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

/**
 * Handles the client side of the proxy.
 */
public class ClientProxy implements ClientModInitializer {

    private MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        preInit();
        init();
    }

    private void registerRenderers() {
        HudRenderCallback.EVENT.register(new GuiClock(mc));
        HudRenderCallback.EVENT.register(new GuiDayCount(mc));
    }

    private void registerKeyBindings() {
        KeyBindingHelper.registerKeyBinding(KeyBindings.TOGGLE);
    }

    public void preInit() {
        registerKeyBindings();
        AutoConfig.register(ConfigValues.class, GsonConfigSerializer::new);
    }

    public void init() {
        registerRenderers();
        ClientTickEvents.END_CLIENT_TICK.register(a -> new KeyInputEventHandler());
    }
}
