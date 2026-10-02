package io.github.derexxd.sift_backport.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

    public static final Block SCULK_GRASS_BLOCK = registerBlock("sculk_grass_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .strength(0.6F)
                    .sound(SoundType.SCULK)));

    public static final Block LIGHT_SCULK_GRASS_BLOCK = registerBlock("light_sculk_grass_block",
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .strength(0.6F)
                    .sound(SoundType.SCULK)));

    public static final Block SCULK_GRASS = registerBlock("sculk_grass",
            new SculkGrassBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final Block TALL_SCULK_GRASS = registerDoublePlantBlock("tall_sculk_grass",
            new TallSculkGrassBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    private static Block registerBlock(String name, Block block) {
        ResourceLocation id = new ResourceLocation("sift", name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
        return block;
    }

    private static Block registerDoublePlantBlock(String name, Block block) {
        ResourceLocation id = new ResourceLocation("sift", name);
        Registry.register(BuiltInRegistries.BLOCK, id, block);
        Registry.register(BuiltInRegistries.ITEM, id, new DoubleHighBlockItem(block, new Item.Properties()));
        return block;
    }

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.accept(SCULK_GRASS_BLOCK);
            entries.accept(LIGHT_SCULK_GRASS_BLOCK);
            entries.accept(SCULK_GRASS);
            entries.accept(TALL_SCULK_GRASS);
        });
    }
}
