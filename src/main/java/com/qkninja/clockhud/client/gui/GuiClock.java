package com.qkninja.clockhud.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import com.qkninja.clockhud.reference.Textures;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.world.World;

import static net.minecraft.text.LiteralText.EMPTY;

/**
 * Creates the Clock Gui.
 */
public class GuiClock extends Screen implements HudRenderCallback {
    private static final int TEXTURE_SCALE = 2;

    private static final int SUN_WIDTH = 48 / TEXTURE_SCALE;
    private static final int MOON_WIDTH = 32 / TEXTURE_SCALE;
    private static final int ICON_HEIGHT = 50 / TEXTURE_SCALE;
    private static final int BAR_LENGTH = 400 / TEXTURE_SCALE;
    private static final int BAR_HEIGHT = 10 / TEXTURE_SCALE;
    private static final int DOT = 10 / TEXTURE_SCALE;

    private MinecraftClient mc;

    public GuiClock(MinecraftClient mc) {
        super(EMPTY);
        this.mc = mc;
    }

    /**
     * Renders the clock GUI on the screen.
     *
     * @param event Variables associated with the event.
     */

    public void onHudRender(MatrixStack matrixStack, float delta) {
        if (!ConfigValues.INS.guiActive)
            return;

        this.mc.getTextureManager().bindTexture(Textures.Gui.HUD);

        GlStateManager.scaled(ConfigValues.INS.scale, ConfigValues.INS.scale, ConfigValues.INS.scale);

        int xCoord;
        if (ConfigValues.INS.centerClock) {
            xCoord = (int) ((MinecraftClient.getInstance().getWindow().getScaledWidth() - (BAR_LENGTH + SUN_WIDTH - DOT) * ConfigValues.INS.scale) / (2 * ConfigValues.INS.scale));
        } else {
            xCoord = ConfigValues.INS.xCoord;
        }

        int startX = xCoord + SUN_WIDTH / 2 - (DOT / 2);
        int startY = ConfigValues.INS.yCoord + ICON_HEIGHT / 2 - BAR_HEIGHT / 2;

        // Draw bar
        this.drawTexture(matrixStack, startX, startY, 0, 0, BAR_LENGTH, BAR_HEIGHT);

        if (isDay()) // Draw sun
        {
            this.drawTexture(matrixStack, xCoord + getScaledTime(), ConfigValues.INS.yCoord, 0, BAR_HEIGHT, SUN_WIDTH, ICON_HEIGHT);
        } else // Draw moon
        {
            this.drawTexture(matrixStack, xCoord + (SUN_WIDTH - MOON_WIDTH) / 2 + getScaledTime(), ConfigValues.INS.yCoord, SUN_WIDTH, BAR_HEIGHT, MOON_WIDTH, ICON_HEIGHT);
        }

        GlStateManager.scaled(1 / ConfigValues.INS.scale, 1 / ConfigValues.INS.scale, 1 / ConfigValues.INS.scale);
    }

    /**
     * Scales the current time to the length of the bar.
     *
     * @return Integer offset to be used when rendering the sun or moon.
     */
    private int getScaledTime() {
        int currentTime = getCurrentTime();

        if (isDay(currentTime)) {
            return currentTime * (BAR_LENGTH - DOT) / Reference.NEW_NIGHT_TICK;
        } else {
            return (currentTime - Reference.NEW_NIGHT_TICK) * (BAR_LENGTH - DOT) / (Reference.DAY_TICKS - Reference.NEW_NIGHT_TICK);
        }
    }

    /**
     * Determines if the world is currently in daytime.
     *
     * @return True if it is day, else false.
     */
    private boolean isDay() {
        return isDay(getCurrentTime());
    }

    /**
     * Determines if the world is currently in daytime.
     *
     * @param currentTime current tick of the day.
     * @return Ture if it is day, else false.
     */
    private boolean isDay(int currentTime) {
        return (currentTime >= 0 && currentTime <= Reference.NEW_NIGHT_TICK);
    }

    /**
     * Gets the current time of day.
     *
     * @return Current tick of the day.
     */
    private int getCurrentTime() {
        World world = MinecraftClient.getInstance().world;
        long time = world.getTimeOfDay();
        return (int) time % Reference.DAY_TICKS;
    }
}