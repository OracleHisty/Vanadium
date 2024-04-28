package com.oraclehisty.vanadium;

import com.oraclehisty.vanadium.anvil.AnvilEvents;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public final class Vanadium {
    public static final String MOD_ID = "vanadium";

    public static void init() {
        AnvilEvents.ANVIL_CHANGE.register(event -> {
            if (event.getLeft().is(Items.WET_SPONGE) && event.getRight().is(Items.CHARCOAL)) {
                event.setOutput(Items.SPONGE.getDefaultInstance());
                event.setMaterialCost(1);
                event.setCost(0);
            }
        });
    }
}
