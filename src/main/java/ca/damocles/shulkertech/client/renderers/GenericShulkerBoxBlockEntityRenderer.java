package ca.damocles.shulkertech.client.renderers;

import ca.damocles.shulkertech.common.block.GenericShulkerBoxBlock;
import ca.damocles.shulkertech.common.block.entity.GenericShulkerBoxBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.model.ShulkerEntityModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

@Environment(EnvType.CLIENT)
public class GenericShulkerBoxBlockEntityRenderer<BE extends GenericShulkerBoxBlockEntity> extends BlockEntityRenderer<BE>{
    protected final ShulkerEntityModel<ShulkerEntity> model = new ShulkerEntityModel<>();
    public static final Identifier SHULKER_BOXES_ATLAS_TEXTURE = new Identifier("textures/atlas/shulker_boxes.png");
    private String type;


    public GenericShulkerBoxBlockEntityRenderer(BlockEntityRenderDispatcher blockEntityRenderDispatcher, String type) {
        super(blockEntityRenderDispatcher);
        this.type = type;
    }

    @Override
    public void render(BE shulkerBoxBlockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {

        Direction direction = Direction.UP;
        if (shulkerBoxBlockEntity.hasWorld()) {
            BlockState blockState = shulkerBoxBlockEntity.getWorld().getBlockState(shulkerBoxBlockEntity.getPos());
            if (blockState.getBlock() instanceof GenericShulkerBoxBlock) {
                direction = (Direction)blockState.get(GenericShulkerBoxBlock.FACING);
            }
        }

        SpriteIdentifier spriteIdentifier2 = new SpriteIdentifier(SHULKER_BOXES_ATLAS_TEXTURE, new Identifier("shulkertech", "shulkers/"+type+"/shulker"));

        matrixStack.push();
        matrixStack.translate(0.5D, 0.5D, 0.5D);
        float g = 0.9995F;
        matrixStack.scale(0.9995F, 0.9995F, 0.9995F);
        matrixStack.multiply(direction.getRotationQuaternion());
        matrixStack.scale(1.0F, -1.0F, -1.0F);
        matrixStack.translate(0.0D, -1.0D, 0.0D);
        VertexConsumer vertexConsumer = spriteIdentifier2.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntityCutoutNoCull);
        model.getBottomShell().render(matrixStack, vertexConsumer, light, overlay);
        matrixStack.translate(0.0D, (double)(-shulkerBoxBlockEntity.getAnimationProgress(tickDelta) * 0.5F), 0.0D);
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(270.0F * shulkerBoxBlockEntity.getAnimationProgress(tickDelta)));
        model.getTopShell().render(matrixStack, vertexConsumer, light, overlay);
        matrixStack.pop();
    }

}