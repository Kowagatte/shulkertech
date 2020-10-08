package ca.damocles.shulkertech.common.block.entity;

import ca.damocles.shulkertech.Registries;
import ca.damocles.shulkertech.common.screenhandler.DiamondShulkerBoxBlockScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class DiamondShulkerBoxBlockEntity extends GenericShulkerBoxBlockEntity {

    public DiamondShulkerBoxBlockEntity(){
        super(Registries.DIAMOND_SHULKER_BOX_BLOCK_ENTITY, 45);
    }

    public static ItemStack getItemStack(){
        return new ItemStack(Registries.DIAMOND_SHULKER_BOX);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new DiamondShulkerBoxBlockScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new DiamondShulkerBoxBlockScreenHandler(syncId, playerInventory, this);
    }

}