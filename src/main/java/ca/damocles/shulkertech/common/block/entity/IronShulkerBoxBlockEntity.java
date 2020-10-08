package ca.damocles.shulkertech.common.block.entity;

import ca.damocles.shulkertech.Registries;
import ca.damocles.shulkertech.common.screenhandler.IronShulkerBoxBlockScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class IronShulkerBoxBlockEntity extends GenericShulkerBoxBlockEntity {

    public IronShulkerBoxBlockEntity(){
        super(Registries.IRON_SHULKER_BOX_BLOCK_ENTITY, 36);
    }

    public static ItemStack getItemStack(){
        return new ItemStack(Registries.IRON_SHULKER_BOX);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new IronShulkerBoxBlockScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new IronShulkerBoxBlockScreenHandler(syncId, playerInventory, this);
    }

}