package io.github.derexxd.sift_backport.block;

import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ModBlocks {

    public static class BlockSculk extends Block {
        public BlockSculk(String name) {
            super(Material.grass);
            this.setHardness(0.6F);
            this.setStepSound(Block.soundTypeGrass);
            this.setBlockName("sift_backport." + name);
            this.setCreativeTab(CreativeTabs.tabBlock);
            this.setBlockTextureName("sift:" + name);
        }
    }

    public static class BlockSculkBush extends BlockBush {
        public BlockSculkBush(String name) {
            super(Material.plants);
            this.setStepSound(Block.soundTypeGrass);
            this.setBlockName("sift_backport." + name);
            this.setCreativeTab(CreativeTabs.tabDecorations);
            this.setBlockTextureName("sift:" + name);
        }

        @Override
        protected boolean canPlaceBlockOn(Block ground) {
            return ground == sculkGrassBlock || ground == lightSculkGrassBlock || ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.farmland;
        }
    }

    public static class BlockTallSculkBottom extends BlockBush {
        public BlockTallSculkBottom(String name) {
            super(Material.plants);
            this.setStepSound(Block.soundTypeGrass);
            this.setBlockName("sift_backport." + name);
            this.setCreativeTab(CreativeTabs.tabDecorations);
            this.setBlockTextureName("sift:" + name + "_bottom");
        }

        @Override
        protected boolean canPlaceBlockOn(Block ground) {
            return ground == sculkGrassBlock || ground == lightSculkGrassBlock || ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.farmland;
        }

        @Override
        public boolean canPlaceBlockAt(World world, int x, int y, int z) {
            return super.canPlaceBlockAt(world, x, y, z) && world.isAirBlock(x, y + 1, z);
        }

        @Override
        public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
            world.setBlock(x, y + 1, z, ModBlocks.tallSculkGrassTop, 0, 2);
        }

        @Override
        public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
            if (world.getBlock(x, y + 1, z) == ModBlocks.tallSculkGrassTop) {
                world.setBlockToAir(x, y + 1, z);
            }
        }

        @Override
        public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
            super.onNeighborBlockChange(world, x, y, z, neighbor);
            if (world.getBlock(x, y + 1, z) != ModBlocks.tallSculkGrassTop) {
                world.setBlockToAir(x, y, z);
            }
        }
    }

    public static class BlockTallSculkTop extends BlockBush {
        public BlockTallSculkTop(String name) {
            super(Material.plants);
            this.setStepSound(Block.soundTypeGrass);
            this.setBlockName("sift_backport." + name);
            this.setBlockTextureName("sift:" + name);
        }

        @Override
        protected boolean canPlaceBlockOn(Block ground) {
            return ground == ModBlocks.tallSculkGrassBottom;
        }

        @Override
        public boolean canBlockStay(World world, int x, int y, int z) {
            return world.getBlock(x, y - 1, z) == ModBlocks.tallSculkGrassBottom;
        }

        @Override
        public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player) {
            if (world.getBlock(x, y - 1, z) == ModBlocks.tallSculkGrassBottom) {
                world.setBlockToAir(x, y - 1, z);
            }
        }

        @Override
        public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
            if (!this.canBlockStay(world, x, y, z)) {
                world.setBlockToAir(x, y, z);
            }
        }

        @Override
        public Item getItemDropped(int meta, Random random, int fortune) {
            return Item.getItemFromBlock(ModBlocks.tallSculkGrassBottom);
        }

        @SideOnly(Side.CLIENT)
        @Override
        public Item getItem(World world, int x, int y, int z) {
            return Item.getItemFromBlock(ModBlocks.tallSculkGrassBottom);
        }
    }

    public static Block sculkGrassBlock;
    public static Block lightSculkGrassBlock;
    public static Block sculkGrass;
    public static Block tallSculkGrassBottom;
    public static Block tallSculkGrassTop;

    public static void register() {
        sculkGrassBlock = new BlockSculk("sculk_grass_block");
        lightSculkGrassBlock = new BlockSculk("light_sculk_grass_block");
        sculkGrass = new BlockSculkBush("sculk_grass");
        tallSculkGrassBottom = new BlockTallSculkBottom("tall_sculk_grass");
        tallSculkGrassTop = new BlockTallSculkTop("tall_sculk_grass_top");

        GameRegistry.registerBlock(sculkGrassBlock, "sculk_grass_block");
        GameRegistry.registerBlock(lightSculkGrassBlock, "light_sculk_grass_block");
        GameRegistry.registerBlock(sculkGrass, "sculk_grass");
        GameRegistry.registerBlock(tallSculkGrassBottom, "tall_sculk_grass");
        GameRegistry.registerBlock(tallSculkGrassTop, "tall_sculk_grass_top");
    }
}
