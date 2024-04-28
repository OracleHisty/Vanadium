package com.oraclehisty.vanadium.forge;

import com.oraclehisty.vanadium.Vanadium;
import com.oraclehisty.vanadium.anvil.AnvilEvents;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;

import static net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.*;

@Mod(Vanadium.MOD_ID)
public final class VanadiumForge {
    public VanadiumForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(Vanadium.MOD_ID, get().getModEventBus());
        var EVENT_BUS = MinecraftForge.EVENT_BUS;
        EVENT_BUS.addListener((Consumer<AnvilUpdateEvent>) event -> {
            var e = new AnvilEvents.AnvilUpdateEvent(event.getLeft(), event.getRight(), event.getName(), event.getCost(), event.getMaterialCost(), event.getPlayer());

            AnvilEvents.ANVIL_CHANGE.invoker().accept(e);

            event.setOutput(e.getOutput());
            event.setCost(e.getCost());
            event.setMaterialCost(e.getMaterialCost());
            event.setCanceled(e.isCancelled());
        });
        // Run our common setup.
        Vanadium.init();
    }
}
