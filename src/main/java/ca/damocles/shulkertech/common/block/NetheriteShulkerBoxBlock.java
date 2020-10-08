package ca.damocles.shulkertech.common.block;

import ca.damocles.shulkertech.Registries;
import ca.damocles.shulkertech.common.block.entity.NetheriteShulkerBoxBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;

public class NetheriteShulkerBoxBlock extends GenericShulkerBoxBlock{

    public NetheriteShulkerBoxBlock(){
        super(FabricBlockSettings.copyOf(Blocks.SHULKER_BOX).hardness(1200.0f).strength(50.0f).breakByTool(FabricToolTags.PICKAXES, 3));
    }

    @Override
    public ItemStack getItemStack() {
        return new ItemStack(Registries.NETHERITE_SHULKER_BOX);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new NetheriteShulkerBoxBlockEntity();
    }

    @Override
    public int getInventorySize(){ return 54;}
}
