package com.oraclehisty.vanadium.forge;

import com.oraclehisty.vanadium.Vanadium;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Vanadium.MOD_ID)
public final class VanadiumForge {
    public VanadiumForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Vanadium.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        Vanadium.init();
    }
}
