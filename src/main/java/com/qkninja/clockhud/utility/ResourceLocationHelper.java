package com.qkninja.clockhud.utility;

import com.qkninja.clockhud.reference.Reference;
import net.minecraft.util.Identifier;

public class ResourceLocationHelper
{
    public static Identifier getResourceLocation(String modId, String path)
    {
        return new Identifier(modId, path);
    }

    public static Identifier getResourceLocation(String path)
    {
        return getResourceLocation(Reference.MOD_ID.toLowerCase(), path);
    }
}
