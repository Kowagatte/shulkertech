package ca.damocles.shulkertech.client;

import ca.damocles.shulkertech.ExampleMod;
import ca.damocles.shulkertech.blocks.blockentity.renderer.GenericShulkerBoxBlockEntityRenderer;
import ca.damocles.shulkertech.screen.IronShulkerScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.util.Identifier;

public class ClientEntry implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ExampleMod.IRON_SHULKER_SCREEN_HANDLER, IronShulkerScreen::new);
        BlockEntityRendererRegistry.INSTANCE.register(ExampleMod.IRON_SHULKER_ENTITY,  d ->
                new GenericShulkerBoxBlockEntityRenderer<>(d, "iron"));
        ClientSpriteRegistryCallback.event(GenericShulkerBoxBlockEntityRenderer.SHULKER_BOXES_ATLAS_TEXTURE).register((atlasTexture, registry) ->{
            registry.register(new Identifier("shulkertech", "shulkers/iron/shulker"));
        });
    }
}
