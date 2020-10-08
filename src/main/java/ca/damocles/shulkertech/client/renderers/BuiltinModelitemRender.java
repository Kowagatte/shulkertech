package ca.damocles.shulkertech.client.renderers;

import ca.damocles.shulkertech.common.block.entity.GenericShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.IronShulkerBoxBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
public class BuiltinModelitemRender implements BuiltinItemRendererRegistry.DynamicItemRenderer {

    public BlockEntity blockEntity;

    public BuiltinModelitemRender(BlockEntity entity){
        blockEntity = entity;
    }

    @Override
    public void render(ItemStack itemStack, ModelTransformation.Mode mode, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int i1) {
        BlockEntityRenderDispatcher.INSTANCE.renderEntity(blockEntity, matrixStack, vertexConsumerProvider, i, i1);
    }
}
