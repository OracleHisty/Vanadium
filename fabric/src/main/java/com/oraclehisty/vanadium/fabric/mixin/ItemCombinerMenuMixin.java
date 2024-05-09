package com.oraclehisty.vanadium.fabric.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ResultContainer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemCombinerMenu.class)
public abstract class ItemCombinerMenuMixin {
    @Shadow @Final protected ResultContainer resultSlots;

    @Shadow @Final protected Player player;

    @Shadow @Final protected Container inputSlots;

    @Unique
    public ResultContainer getResultSlot() {
        return resultSlots;
    }
}