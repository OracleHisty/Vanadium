package com.oraclehisty.vanadium.fabric.datagen;

import com.oraclehisty.vanadium.Vanadium;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.Nullable;

public class DatagenInitializer implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var output=fabricDataGenerator.createPack();
        output.addProvider(VanadiumLanguageProvider::new);
    }

    @Override
    public @Nullable String getEffectiveModId() {
        return Vanadium.MOD_ID;
    }
}
