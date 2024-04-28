package com.oraclehisty.vanadium.anvil;

import dev.architectury.event.Event;
import dev.architectury.event.EventFactory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class AnvilEvents {
    public static final Event<Consumer<AnvilUpdateEvent>> ANVIL_CHANGE = EventFactory.createConsumerLoop(AnvilUpdateEvent.class);

    //Wholesale copy of Forge event for multplatform reasons.
    public static class AnvilUpdateEvent {

        private final ItemStack left;
        private final ItemStack right;
        private final String name;
        private ItemStack output;
        private int cost;
        private int materialCost;
        private final Player player;
        private boolean canceled;

        public AnvilUpdateEvent(ItemStack left, ItemStack right, String name, int cost, int materialCost, Player player) {
            this.left = left;
            this.right = right;
            this.output = ItemStack.EMPTY;
            this.name = name;
            this.player = player;
            this.setCost(cost);
            this.setMaterialCost(materialCost);
        }

        /**
         * @return The item in the left input (leftmost) anvil slot.
         */
        public ItemStack getLeft()
        {
            return left;
        }

        /**
         * @return The item in the right input (center) anvil slot.
         */
        public ItemStack getRight()
        {
            return right;
        }

        /**
         * This is the name as sent by the client.  It may be null if none has been sent. <br>
         * If empty, it indicates the user wishes to clear the custom name from the item.
         * @return The name that the output item will be set to, if applicable.
         */
        @Nullable
        public String getName()
        {
            return name;
        }

        /**
         * This is the output as determined by the event, not by the vanilla behavior between these two items. <br>
         * If you are the first receiver of this event, it is guaranteed to be empty. <br>
         * It will only be non-empty if changed by an event handler. <br>
         * If this event is cancelled, this output stack is discarded.
         * @return The item to set in the output (rightmost) anvil slot.
         */
        public ItemStack getOutput()
        {
            return output;
        }

        /**
         * Sets the output slot to a specific itemstack.
         * @param output The stack to change the output to.
         */
        public void setOutput(ItemStack output)
        {
            this.output = output;
        }

        /**
         * This is the level cost of this anvil operation. <br>
         * When unchanged, it is guaranteed to be left.getRepairCost() + right.getRepairCost().
         * @return The level cost of this anvil operation.
         */
        public int getCost()
        {
            return cost;
        }

        /**
         * Changes the level cost of this operation. <br>
         * The level cost does prevent the output from being available.  <br>
         * That is, a player without enough experience may not take the output.
         * @param cost The new level cost.
         */
        public void setCost(int cost)
        {
            this.cost = cost;
        }

        /**
         * The material cost is how many units of the right input stack are consumed.
         * @return The material cost of this anvil operation.
         */
        public int getMaterialCost()
        {
            return materialCost;
        }

        /**
         * Sets how many right inputs are consumed. <br>
         * A material cost of zero consumes the entire stack. <br>
         * A material cost higher than the count of the right stack
         * consumes the entire stack. <br>
         * The material cost does not prevent the output from being available.
         * @param materialCost The new material cost.
         */
        public void setMaterialCost(int materialCost)
        {
            this.materialCost = materialCost;
        }

        /**
         * @return The player using this anvil container.
         */
        public Player getPlayer()
        {
            return this.player;
        }

        public boolean isCancelled() {
            return canceled;
        }

        public void setCanceled(boolean canceled) {
            this.canceled = canceled;
        }
    }
}