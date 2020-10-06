package ca.damocles.shulkertech;

import ca.damocles.shulkertech.blocks.IronShulkerBoxBlock;
import ca.damocles.shulkertech.blocks.blockentity.IronShulkerBoxBlockEntity;
import ca.damocles.shulkertech.screen.handler.IronShulkerScreenHandler;
import ca.damocles.shulkertech.utilities.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID = "shulkertech";

	public static Identifier getNameSpace(String resourceName){
		return new Identifier(MOD_ID, resourceName);
	}

	public static final Block IRON_SHULKER_BOX = new IronShulkerBoxBlock();
	public static BlockEntityType<IronShulkerBoxBlockEntity> IRON_SHULKER_ENTITY;
	public static ScreenHandlerType<IronShulkerScreenHandler> IRON_SHULKER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(getNameSpace("iron_shulker"), IronShulkerScreenHandler::new);;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
		Registry.register(Registry.BLOCK, getNameSpace("iron_shulker"), IRON_SHULKER_BOX);
		Registry.register(Registry.ITEM, getNameSpace("iron_shulker"), new BlockItem(IRON_SHULKER_BOX, new Item.Settings().group(ItemGroup.MISC)));
		IRON_SHULKER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "shulkertech:iron_shulker", BlockEntityType.Builder.create(IronShulkerBoxBlockEntity::new, IRON_SHULKER_BOX).build(null));
	}

}
