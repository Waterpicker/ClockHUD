package com.qkninja.clockhud.utility;

import com.qkninja.clockhud.reference.Reference;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper Methods for logging.
 */
public class LogHelper
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static void log(Level logLevel, Object object)
    {
        LOGGER.log(logLevel, String.valueOf(object));
    }

    public static void all(Object object) { log(Level.ALL, object); }

    public static void debug(Object object) { log(Level.DEBUG, object); }

    public static void error(Object object) { log(Level.ERROR, object); }

    public static void fatal(Object object) { log(Level.FATAL, object); }

    public static void info(Object object) { log(Level.INFO, object); }

    public static void off(Object object) { log(Level.OFF, object); }

    public static void trace(Object object) { log(Level.TRACE, object); }

    public static void warn(Object object) { log(Level.WARN, object); }
}
