package io.github.derexxd.sift_backport.world.gen;

import io.github.derexxd.sift_backport.world.biome.ModBiomes;
import io.github.derexxd.sift_backport.block.ModBlocks;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorSift implements IChunkGenerator {

    private final World world;
    private final Random rand;
    private final NoiseGeneratorPerlin surfaceNoise;

    public ChunkGeneratorSift(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.rand, 4);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        ChunkPrimer primer = new ChunkPrimer();
        int baseHeight = 64;

        for (int localX = 0; localX < 16; ++localX) {
            for (int localZ = 0; localZ < 16; ++localZ) {
                int worldX = (x << 4) + localX;
                int worldZ = (z << 4) + localZ;

                // Bedrock bottom layer
                primer.setBlockState(localX, 0, localZ, Blocks.BEDROCK.getDefaultState());
                for (int y = 1; y < 4; ++y) {
                    if (this.rand.nextInt(y + 1) == 0) {
                        primer.setBlockState(localX, y, localZ, Blocks.BEDROCK.getDefaultState());
                    }
                }

                // Terrain height variation based on perlin noise
                double noiseVal = this.surfaceNoise.getValue((double) worldX * 0.015D, (double) worldZ * 0.015D);
                int height = (int) (baseHeight + noiseVal * 16.0D);
                height = Math.max(10, Math.min(240, height));

                IBlockState topState = ModBiomes.SIFT_BIOME.topBlock;
                IBlockState fillerState = ModBiomes.SIFT_BIOME.fillerBlock;
                IBlockState baseState = Blocks.CONCRETE.getStateFromMeta(7);

                for (int y = 1; y <= height; ++y) {
                    if (primer.getBlockState(localX, y, localZ).getBlock() != Blocks.BEDROCK) {
                        if (y == height) {
                            if (this.rand.nextFloat() < 0.15F) {
                                primer.setBlockState(localX, y, localZ, ModBlocks.LIGHT_SCULK_GRASS_BLOCK.getDefaultState());
                            } else {
                                primer.setBlockState(localX, y, localZ, topState);
                            }
                        } else if (y >= height - 3) {
                            primer.setBlockState(localX, y, localZ, fillerState);
                        } else {
                            primer.setBlockState(localX, y, localZ, baseState);
                        }
                    }
                }
            }
        }

        Chunk chunk = new Chunk(this.world, primer, x, z);
        byte[] biomes = chunk.getBiomeArray();
        byte biomeId = (byte) Biome.getIdForBiome(ModBiomes.SIFT_BIOME);
        for (int i = 0; i < biomes.length; ++i) {
            biomes[i] = biomeId;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int x, int z) {
        int worldX = x * 16;
        int worldZ = z * 16;
        BlockPos blockpos = new BlockPos(worldX, 0, worldZ);

        for (int i = 0; i < 4; ++i) {
            int rx = this.rand.nextInt(16) + 8;
            int rz = this.rand.nextInt(16) + 8;
            BlockPos pos = this.world.getTopSolidOrLiquidBlock(blockpos.add(rx, 0, rz));

            if (this.world.isAirBlock(pos) && ModBlocks.SCULK_GRASS.canPlaceBlockAt(this.world, pos)) {
                if (this.rand.nextInt(3) == 0 && this.world.isAirBlock(pos.up())) {
                    this.world.setBlockState(pos, ModBlocks.TALL_SCULK_GRASS.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER), 2);
                    this.world.setBlockState(pos.up(), ModBlocks.TALL_SCULK_GRASS.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), 2);
                } else {
                    this.world.setBlockState(pos, ModBlocks.SCULK_GRASS.getDefaultState(), 2);
                }
            }
        }
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
