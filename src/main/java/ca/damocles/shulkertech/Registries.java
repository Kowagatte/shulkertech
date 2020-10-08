package ca.damocles.shulkertech;

import ca.damocles.shulkertech.common.block.DiamondShulkerBoxBlock;
import ca.damocles.shulkertech.common.block.IronShulkerBoxBlock;
import ca.damocles.shulkertech.common.block.NetheriteShulkerBoxBlock;
import ca.damocles.shulkertech.common.block.entity.DiamondShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.IronShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.NetheriteShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.screenhandler.DiamondShulkerBoxBlockScreenHandler;
import ca.damocles.shulkertech.common.screenhandler.IronShulkerBoxBlockScreenHandler;
import ca.damocles.shulkertech.common.screenhandler.NetheriteShulkerBoxBlockScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.screen.ScreenHandlerType;

public class Registries {
    public static final Block IRON_SHULKER_BOX = new IronShulkerBoxBlock();
    public static BlockEntityType<IronShulkerBoxBlockEntity> IRON_SHULKER_BOX_BLOCK_ENTITY;
    public static final ScreenHandlerType<IronShulkerBoxBlockScreenHandler> IRON_SHULKER_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ShulkerTech.getResourcePath("iron_shulker"), IronShulkerBoxBlockScreenHandler::new);

    public static final Block DIAMOND_SHULKER_BOX = new DiamondShulkerBoxBlock();
    public static BlockEntityType<DiamondShulkerBoxBlockEntity> DIAMOND_SHULKER_BOX_BLOCK_ENTITY;
    public static final ScreenHandlerType<DiamondShulkerBoxBlockScreenHandler> DIAMOND_SHULKER_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ShulkerTech.getResourcePath("diamond_shulker"), DiamondShulkerBoxBlockScreenHandler::new);

    public static final Block NETHERITE_SHULKER_BOX = new NetheriteShulkerBoxBlock();
    public static BlockEntityType<NetheriteShulkerBoxBlockEntity> NETHERITE_SHULKER_BOX_BLOCK_ENTITY;
    public static final ScreenHandlerType<NetheriteShulkerBoxBlockScreenHandler> NETHERITE_SHULKER_BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(ShulkerTech.getResourcePath("netherite_shulker"), NetheriteShulkerBoxBlockScreenHandler::new);
}
