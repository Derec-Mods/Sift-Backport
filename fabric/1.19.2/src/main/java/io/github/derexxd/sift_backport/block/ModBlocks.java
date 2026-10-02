package io.github.derexxd.sift_backport.block;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {

    public static final Block SCULK_GRASS_BLOCK = registerBlock("sculk_grass_block",
            new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_ORANGE)
                    .strength(0.6F)
                    .sound(SoundType.SCULK)));

    public static final Block LIGHT_SCULK_GRASS_BLOCK = registerBlock("light_sculk_grass_block",
            new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_PINK)
                    .strength(0.6F)
                    .sound(SoundType.SCULK)));

    public static final Block SCULK_GRASS = registerBlock("sculk_grass",
            new SculkGrassBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XYZ)));

    public static final Block TALL_SCULK_GRASS = registerDoublePlantBlock("tall_sculk_grass",
            new TallSculkGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)));

    private static Block registerBlock(String name, Block block) {
        ResourceLocation id = new ResourceLocation("sift", name);
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        return block;
    }

    private static Block registerDoublePlantBlock(String name, Block block) {
        ResourceLocation id = new ResourceLocation("sift", name);
        Registry.register(Registry.BLOCK, id, block);
        Registry.register(Registry.ITEM, id, new DoubleHighBlockItem(block, new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        return block;
    }

    public static void register() {
    }
}
