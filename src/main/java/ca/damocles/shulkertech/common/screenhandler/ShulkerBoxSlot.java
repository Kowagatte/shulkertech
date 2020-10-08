package ca.damocles.shulkertech.common.screenhandler;

import ca.damocles.shulkertech.common.block.GenericShulkerBoxBlock;
import ca.damocles.shulkertech.common.block.IronShulkerBoxBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ShulkerBoxSlot extends Slot {

    public ShulkerBoxSlot(Inventory inventory, int i, int j, int k) {
        super(inventory, i, j, k);
    }

    public boolean canInsert(ItemStack stack) {
        return !((Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock) ||
                (Block.getBlockFromItem(stack.getItem()) instanceof GenericShulkerBoxBlock));
    }

}
