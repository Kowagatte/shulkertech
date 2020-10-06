package ca.damocles.shulkertech.blocks.blockentity;

import ca.damocles.shulkertech.ExampleMod;
import ca.damocles.shulkertech.screen.handler.IronShulkerScreenHandler;
import ca.damocles.shulkertech.utilities.ImplementedInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Tickable;

public class IronShulkerBoxBlockEntity extends GenericShulkerBoxBlockEntity implements NamedScreenHandlerFactory, ImplementedInventory, Tickable {

    public IronShulkerBoxBlockEntity(){
        super(ExampleMod.IRON_SHULKER_ENTITY, 36);
    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new IronShulkerScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new IronShulkerScreenHandler(syncId, playerInventory, this);
    }

}