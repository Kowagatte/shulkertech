package ca.damocles.shulkertech.common.block;

import ca.damocles.shulkertech.Registries;
import ca.damocles.shulkertech.common.block.entity.DiamondShulkerBoxBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;

public class DiamondShulkerBoxBlock extends GenericShulkerBoxBlock{

    public DiamondShulkerBoxBlock(){
        super(FabricBlockSettings.copyOf(Blocks.SHULKER_BOX).hardness(5.0f).strength(6.0f).breakByTool(FabricToolTags.PICKAXES, 2));
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Registries.DIAMOND_SHULKER_BOX);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new DiamondShulkerBoxBlockEntity();
    }

    @Override
    public int getInventorySize(){ return 45;}
}
