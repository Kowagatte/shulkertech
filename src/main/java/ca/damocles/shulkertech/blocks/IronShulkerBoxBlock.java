package ca.damocles.shulkertech.blocks;

import ca.damocles.shulkertech.ExampleMod;
import ca.damocles.shulkertech.blocks.blockentity.IronShulkerBoxBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;

public class IronShulkerBoxBlock extends GenericShulkerBoxBlock{

    public IronShulkerBoxBlock(){
        super(FabricBlockSettings.of(Material.SHULKER_BOX).hardness(5.0f).strength(6.0f).breakByTool(FabricToolTags.PICKAXES, 1));
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(ExampleMod.IRON_SHULKER_BOX);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new IronShulkerBoxBlockEntity();
    }
}
