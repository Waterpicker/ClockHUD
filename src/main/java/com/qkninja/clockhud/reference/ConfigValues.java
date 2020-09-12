package com.qkninja.clockhud.reference;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

/**
 * Stores config values for ClockHUD.
 * Default values are visible here.
 */

@Config(name = "clockhud")
public class ConfigValues implements ConfigData {

    public static ConfigValues INS;

    public boolean guiActive = true;
    public boolean showDayCount = true;
    public boolean centerClock = false;

    public int xCoord = 2;
    public int yCoord = 2;

    public double scale = .7F;
}
