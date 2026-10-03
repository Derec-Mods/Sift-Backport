package io.github.derexxd.sift_backport.block;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class ModBlocks {

    public static final Block SCULK_GRASS_BLOCK = registerBlock("sculk_grass_block", Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6F)
                    .sound(SoundType.SCULK));

    public static final Block LIGHT_SCULK_GRASS_BLOCK = registerBlock("light_sculk_grass_block", Block::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .strength(0.6F)
                    .sound(SoundType.SCULK));

    public static final Block SCULK_GRASS = registerBlock("sculk_grass", SculkGrassBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .pushReaction(PushReaction.DESTROY));

    public static final Block TALL_SCULK_GRASS = registerDoublePlantBlock("tall_sculk_grass", TallSculkGrassBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollision()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY));

    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Identifier id = Identifier.parse("sift:" + name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        properties.setId(blockKey);
        Block block = factory.apply(properties);
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

        Item item = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return block;
    }

    private static Block registerDoublePlantBlock(String name, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties) {
        Identifier id = Identifier.parse("sift:" + name);
        ResourceKey<Block> blockKey = ResourceKey.create(Registries.BLOCK, id);
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, id);

        properties.setId(blockKey);
        Block block = factory.apply(properties);
        Registry.register(BuiltInRegistries.BLOCK, blockKey, block);

        Item item = new DoubleHighBlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return block;
    }

    public static void register() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.NATURAL_BLOCKS).register(output -> {
            output.accept(SCULK_GRASS_BLOCK);
            output.accept(LIGHT_SCULK_GRASS_BLOCK);
            output.accept(SCULK_GRASS);
            output.accept(TALL_SCULK_GRASS);
        });
    }
}
