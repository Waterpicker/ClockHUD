package com.qkninja.clockhud.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Names;
import com.qkninja.clockhud.reference.Reference;
import com.qkninja.clockhud.utility.Algorithms;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;


/**
 * Renders the day count on the screen after each new day.
 *
 * @author Sam Beckmann
 */
public class GuiDayCount extends Screen implements HudRenderCallback {
    private MinecraftClient mc;

    private long endAnimationTime;
    private boolean isRunning;
    private static final int ANIMATION_TIME = 3000; // 3 second animation

    public GuiDayCount(MinecraftClient mc) {
        super(LiteralText.EMPTY);
        isRunning = false;
        this.mc = mc;
    }

    /**
     * Renders the Day Count on the screen.
     *
     * @param event variables associated with the event.
     */
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
       if (ConfigValues.INS.showDayCount && (isRunning || isNewDay())) {

            // long currentTime = Minecraft.getSystemTime();

            long currentTime = System.nanoTime() / 1000000;

            if (isRunning && currentTime >= endAnimationTime) {
                isRunning = false;
                return;
            }

            if (!isRunning) {
                isRunning = true;
                endAnimationTime = currentTime + ANIMATION_TIME;
            }

            float scaleFactor = getScaleFactor((endAnimationTime - currentTime) / (float) ANIMATION_TIME);
            String dayString = formDayString();

            GlStateManager.scalef(scaleFactor, scaleFactor, scaleFactor);

            int alpha = Math.max(getOpacityFactor((endAnimationTime - currentTime) / (float) ANIMATION_TIME), 5);
            int color = (alpha << 24) | 0xffffff;
            float xPos = (mc.getWindow().getScaledWidth() - mc.textRenderer.getWidth(dayString) * scaleFactor) / (2 * scaleFactor);
            float yPos = mc.getWindow().getScaledHeight() / 7 / scaleFactor;

            mc.textRenderer.draw(matrixStack, dayString, xPos, yPos, color);

            GlStateManager.scalef(1 / scaleFactor, 1 / scaleFactor, 1 / scaleFactor);
        }
    }

    /**
     * Tests if it's a new day.
     *
     * @return if the dayTime is the specified time of a new day.
     */
    private boolean isNewDay() {
        return MinecraftClient.getInstance().world.getTimeOfDay() % Reference.DAY_TICKS == Reference.NEW_DAY_TICK;
    }

    /**
     * Creates the day string based on total world time
     *
     * @return String of "Day: " + day number
     */
    private String formDayString() {
        return I18n.translate(Names.Text.DAYCOUNT, MinecraftClient.getInstance().world.getTimeOfDay() / Reference.DAY_TICKS);
    }

    /**
     * Gets the factor that the text should be scaled by.
     * Ensures even scaling throughout the time of the animation.
     *
     * @param percentRemaining scaled value between 0-1 indicating percent of the animation remaining.
     * @return Value evenly scaled between 2 and 2.5 based on input.
     */
    private float getScaleFactor(float percentRemaining) {
        return 2.5F - percentRemaining / 2;
    }

    /**
     * Gets the opacity at which the text should be displayed.
     * Handles fade in/out of text.
     *
     * @param percentRemaining scaled value between 0-1 indicating percent of the animation remaining.
     * @return value between 0 and 255, indicating alpha value.
     */
    private int getOpacityFactor(float percentRemaining) {
        if (percentRemaining > .8)
            return (int) (255 * (0.8 - Algorithms.scale(percentRemaining, 0.8, 1, 0, 0.8)));
        else if (percentRemaining < .2)
            return (int) (255 * Algorithms.scale(percentRemaining, 0, 0.2, 0, 0.8));
        else
            return (int) (255 * 0.8F);
    }
}
