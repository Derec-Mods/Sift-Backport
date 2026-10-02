package io.github.derexxd.sift_backport.block;

import io.github.derexxd.sift_backport.Siftbackport;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Siftbackport.MODID)
public class ModBlocks {

    public static class BlockSculk extends Block {
        public BlockSculk(String name) {
            super(Material.GRASS, MapColor.CYAN);
            this.setHardness(0.6F);
            this.setSoundType(SoundType.PLANT);
            this.setRegistryName(name);
            this.setTranslationKey("sift_backport." + name);
            this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        }
    }

    public static class BlockSculkBush extends BlockBush {
        public BlockSculkBush(String name) {
            super(Material.PLANTS);
            this.setSoundType(SoundType.PLANT);
            this.setRegistryName(name);
            this.setTranslationKey("sift_backport." + name);
            this.setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    public static class BlockTallSculk extends BlockDoublePlant {
        public BlockTallSculk(String name) {
            super();
            this.setSoundType(SoundType.PLANT);
            this.setRegistryName(name);
            this.setTranslationKey("sift_backport." + name);
            this.setCreativeTab(CreativeTabs.DECORATIONS);
        }
    }

    public static final Block SCULK_GRASS_BLOCK = new BlockSculk("sculk_grass_block");
    public static final Block LIGHT_SCULK_GRASS_BLOCK = new BlockSculk("light_sculk_grass_block");
    public static final Block SCULK_GRASS = new BlockSculkBush("sculk_grass");
    public static final Block TALL_SCULK_GRASS = new BlockTallSculk("tall_sculk_grass");

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            SCULK_GRASS_BLOCK,
            LIGHT_SCULK_GRASS_BLOCK,
            SCULK_GRASS,
            TALL_SCULK_GRASS
        );
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            new ItemBlock(SCULK_GRASS_BLOCK).setRegistryName(SCULK_GRASS_BLOCK.getRegistryName()),
            new ItemBlock(LIGHT_SCULK_GRASS_BLOCK).setRegistryName(LIGHT_SCULK_GRASS_BLOCK.getRegistryName()),
            new ItemBlock(SCULK_GRASS).setRegistryName(SCULK_GRASS.getRegistryName()),
            new ItemBlock(TALL_SCULK_GRASS).setRegistryName(TALL_SCULK_GRASS.getRegistryName())
        );
    }
}
