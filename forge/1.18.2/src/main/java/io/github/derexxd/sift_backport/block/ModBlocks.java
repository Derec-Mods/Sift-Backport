package io.github.derexxd.sift_backport.block;

import io.github.derexxd.sift_backport.Siftbackport;
import io.github.derexxd.sift_backport.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Siftbackport.MODID);

    public static final RegistryObject<Block> SCULK_GRASS_BLOCK = registerBlock("sculk_grass_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_ORANGE)
                    .strength(0.6F)
                    .sound(SoundType.SCULK_SENSOR)));

    public static final RegistryObject<Block> LIGHT_SCULK_GRASS_BLOCK = registerBlock("light_sculk_grass_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.GRASS, MaterialColor.COLOR_PINK)
                    .strength(0.6F)
                    .sound(SoundType.SCULK_SENSOR)));

    public static final RegistryObject<Block> SCULK_GRASS = registerBlock("sculk_grass",
            () -> new SculkGrassBlock(BlockBehaviour.Properties.of(Material.PLANT, MaterialColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)));

    public static final RegistryObject<Block> TALL_SCULK_GRASS = registerDoublePlantBlock("tall_sculk_grass",
            () -> new TallSculkGrassBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT, MaterialColor.COLOR_PINK)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)));

    private static RegistryObject<Block> registerBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new BlockItem(toReturn.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        return toReturn;
    }

    private static RegistryObject<Block> registerDoublePlantBlock(String name, Supplier<Block> block) {
        RegistryObject<Block> toReturn = BLOCKS.register(name, block);
        ModItems.ITEMS.register(name, () -> new DoubleHighBlockItem(toReturn.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
        return toReturn;
    }
}
