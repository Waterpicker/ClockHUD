package com.qkninja.clockhud.compat;

import com.qkninja.clockhud.reference.ConfigValues;
import com.qkninja.clockhud.reference.Reference;
import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.TranslatableText;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public String getModId() {
        return Reference.MOD_ID;
    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(new TranslatableText("title.examplemod.config"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();
            ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.clockhud.general"));
            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.clockhud.guiActive"), ConfigValues.INS.guiActive).setSaveConsumer(a -> ConfigValues.INS.guiActive = a).build());
            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.clockhud.showDayCount"), ConfigValues.INS.showDayCount).setSaveConsumer(a -> ConfigValues.INS.showDayCount = a).build());
            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("option.clockhud.centerClock"), ConfigValues.INS.centerClock).setSaveConsumer(a -> ConfigValues.INS.centerClock = a).build());
            general.addEntry(entryBuilder.startIntSlider(new TranslatableText("option.clockhud.xCoord"), ConfigValues.INS.xCoord, 0, 10000).setSaveConsumer(a -> ConfigValues.INS.xCoord = a).build());
            general.addEntry(entryBuilder.startIntSlider(new TranslatableText("option.clockhud.xCoord"), ConfigValues.INS.xCoord, 0, 10000).setSaveConsumer(a -> ConfigValues.INS.yCoord = a).build());
            general.addEntry(entryBuilder.startDoubleField(new TranslatableText("option.clockhud.scale"), ConfigValues.INS.scale).setSaveConsumer(a -> ConfigValues.INS.scale = a).build());
            return builder.build();
        };
    }
}
