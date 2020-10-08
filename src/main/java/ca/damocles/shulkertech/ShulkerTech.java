package ca.damocles.shulkertech;

import ca.damocles.shulkertech.common.block.entity.DiamondShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.IronShulkerBoxBlockEntity;
import ca.damocles.shulkertech.common.block.entity.NetheriteShulkerBoxBlockEntity;
import ca.damocles.shulkertech.utilities.ModConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ShulkerTech implements ModInitializer {

    public static final String MOD_ID = "shulkertech";

    public static Identifier getResourcePath(String path){
        return new Identifier(MOD_ID, path);
    }

    @Override
    public void onInitialize(){
        /*
         * This code runs as soon as Minecraft is in a mod-load-ready state.
         * However, some things (like resources) may still be uninitialized.
         * Proceed with mild caution.
         */
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        Registry.register(Registry.BLOCK, getResourcePath("iron_shulker"), Registries.IRON_SHULKER_BOX);
        Registry.register(Registry.ITEM, getResourcePath("iron_shulker"), new BlockItem(Registries.IRON_SHULKER_BOX, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(1)));
        Registries.IRON_SHULKER_BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "shulkertech:iron_shulker", BlockEntityType.Builder.create(IronShulkerBoxBlockEntity::new, Registries.IRON_SHULKER_BOX).build(null));

        Registry.register(Registry.BLOCK, getResourcePath("diamond_shulker"), Registries.DIAMOND_SHULKER_BOX);
        Registry.register(Registry.ITEM, getResourcePath("diamond_shulker"), new BlockItem(Registries.DIAMOND_SHULKER_BOX, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(1)));
        Registries.DIAMOND_SHULKER_BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "shulkertech:diamond_shulker", BlockEntityType.Builder.create(DiamondShulkerBoxBlockEntity::new, Registries.DIAMOND_SHULKER_BOX).build(null));

        Registry.register(Registry.BLOCK, getResourcePath("netherite_shulker"), Registries.NETHERITE_SHULKER_BOX);
        Registry.register(Registry.ITEM, getResourcePath("netherite_shulker"), new BlockItem(Registries.NETHERITE_SHULKER_BOX, new Item.Settings().group(ItemGroup.DECORATIONS).maxCount(1)));
        Registries.NETHERITE_SHULKER_BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "shulkertech:netherite_shulker", BlockEntityType.Builder.create(NetheriteShulkerBoxBlockEntity::new, Registries.NETHERITE_SHULKER_BOX).build(null));
    }


}
