package com.oraclehisty.vanadium;

import com.oraclehisty.vanadium.anvil.AnvilEvents;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public final class Vanadium {
    public static final String MOD_ID = "vanadium";

    public static void init() {
        AnvilEvents.ANVIL_CHANGE.register(event -> {
            if (event.getLeft().is(Items.WET_SPONGE) && event.getRight().is(Items.CHARCOAL)) {
                event.setOutput(new ItemStack(Items.SPONGE, event.getLeft().getCount()));
                event.setMaterialCost(1);
                event.setCost(1);
                //Hardcoded Mojang Limitations result in the left stack totally being consumed for only 1 result. Mixins are expected to be required to fix.
            }
        });
    }
    @ExpectPlatform
    public static String getPlatformName (){
        throw new RuntimeException();
    }
}
