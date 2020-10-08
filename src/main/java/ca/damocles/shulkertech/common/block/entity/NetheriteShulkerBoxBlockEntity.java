package ca.damocles.shulkertech.common.block.entity;

import ca.damocles.shulkertech.Registries;
import ca.damocles.shulkertech.common.screenhandler.NetheriteShulkerBoxBlockScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

public class NetheriteShulkerBoxBlockEntity extends GenericShulkerBoxBlockEntity {

    public NetheriteShulkerBoxBlockEntity(){
        super(Registries.NETHERITE_SHULKER_BOX_BLOCK_ENTITY, 54);
    }

    public static ItemStack getItemStack(){
        return new ItemStack(Registries.NETHERITE_SHULKER_BOX);
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NetheriteShulkerBoxBlockScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new NetheriteShulkerBoxBlockScreenHandler(syncId, playerInventory, this);
    }

}