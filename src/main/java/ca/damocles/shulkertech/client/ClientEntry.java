package ca.damocles.shulkertech.client;

import ca.damocles.shulkertech.Registries;
import ca.damocles.shulkertech.client.renderers.BuiltinModelitemRender;
import ca.damocles.shulkertech.client.renderers.GenericShulkerBoxBlockEntityRenderer;
import ca.damocles.shulkertech.client.screens.DiamondShulkerBoxBlockScreen;
import ca.damocles.shulkertech.client.screens.IronShulkerBoxBlockScreen;
import ca.damocles.shulkertech.client.screens.NetheriteShulkerBoxBlockScreen;
import ca.damocles.shulkertech.common.block.entity.DiamondShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.IronShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.NetheriteShulkerBoxBlockEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.util.Identifier;

public class ClientEntry implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Registries.IRON_SHULKER_BOX_SCREEN_HANDLER, IronShulkerBoxBlockScreen::new);
        ScreenRegistry.register(Registries.DIAMOND_SHULKER_BOX_SCREEN_HANDLER, DiamondShulkerBoxBlockScreen::new);
        ScreenRegistry.register(Registries.NETHERITE_SHULKER_BOX_SCREEN_HANDLER, NetheriteShulkerBoxBlockScreen::new);

        BlockEntityRendererRegistry.INSTANCE.register(Registries.IRON_SHULKER_BOX_BLOCK_ENTITY, d ->
                new GenericShulkerBoxBlockEntityRenderer<>(d, "iron"));

        BlockEntityRendererRegistry.INSTANCE.register(Registries.DIAMOND_SHULKER_BOX_BLOCK_ENTITY, d->
                new GenericShulkerBoxBlockEntityRenderer<>(d, "diamond"));

        BlockEntityRendererRegistry.INSTANCE.register(Registries.NETHERITE_SHULKER_BOX_BLOCK_ENTITY, d->
                new GenericShulkerBoxBlockEntityRenderer<>(d, "netherite"));


        ClientSpriteRegistryCallback.event(GenericShulkerBoxBlockEntityRenderer.SHULKER_BOXES_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier("shulkertech", "shulkers/iron/shulker"));
            registry.register(new Identifier("shulkertech", "shulkers/diamond/shulker"));
            registry.register(new Identifier("shulkertech", "shulkers/netherite/shulker"));
        });


        BuiltinItemRendererRegistry.INSTANCE.register(Registries.IRON_SHULKER_BOX, new BuiltinModelitemRender(new IronShulkerBoxBlockEntity()));
        BuiltinItemRendererRegistry.INSTANCE.register(Registries.DIAMOND_SHULKER_BOX, new BuiltinModelitemRender(new DiamondShulkerBoxBlockEntity()));
        BuiltinItemRendererRegistry.INSTANCE.register(Registries.NETHERITE_SHULKER_BOX, new BuiltinModelitemRender(new NetheriteShulkerBoxBlockEntity()));
    }

}