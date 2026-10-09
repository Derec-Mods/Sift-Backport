package io.github.derexxd.sift_backport.world.gen;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.world.biome.ModBiomes;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

public class ChunkProviderSift implements IChunkProvider {

    private final World world;
    private final Random rand;
    private final NoiseGeneratorOctaves noiseGen;
    private double[] noiseArray = new double[256];

    public ChunkProviderSift(World world, long seed) {
        this.world = world;
        this.rand = new Random(seed);
        this.noiseGen = new NoiseGeneratorOctaves(this.rand, 4);
    }

    @Override
    public Chunk provideChunk(int chunkX, int chunkZ) {
        Block[] blocks = new Block[65536];
        byte[] meta = new byte[65536];
        generateTerrain(chunkX, chunkZ, blocks, meta);

        Chunk chunk = new Chunk(this.world, blocks, meta, chunkX, chunkZ);
        byte[] biomes = chunk.getBiomeArray();
        byte biomeId = (byte) ModBiomes.siftBiome.biomeID;
        Arrays.fill(biomes, biomeId);

        chunk.generateSkylightMap();
        return chunk;
    }

    private void generateTerrain(int chunkX, int chunkZ, Block[] blocks, byte[] meta) {
        int baseHeight = 64;
        this.noiseArray = this.noiseGen.generateNoiseOctaves(this.noiseArray, chunkX * 16, 0, chunkZ * 16, 16, 1, 16, 0.015D, 1.0D, 0.015D);

        for (int localX = 0; localX < 16; ++localX) {
            for (int localZ = 0; localZ < 16; ++localZ) {
                int baseIndex = (localX * 16 + localZ) * 256;
                blocks[baseIndex] = Blocks.bedrock;

                for (int y = 1; y < 4; ++y) {
                    if (this.rand.nextInt(y + 1) == 0) {
                        blocks[baseIndex + y] = Blocks.bedrock;
                    }
                }

                double noiseVal = this.noiseArray[localX + localZ * 16] / 8.0D;
                int height = (int) (baseHeight + noiseVal * 16.0D);
                height = Math.max(10, Math.min(240, height));

                for (int y = 1; y <= height; ++y) {
                    if (blocks[baseIndex + y] != Blocks.bedrock) {
                        if (y == height) {
                            if (this.rand.nextFloat() < 0.15F) {
                                blocks[baseIndex + y] = ModBlocks.lightSculkGrassBlock;
                            } else {
                                blocks[baseIndex + y] = ModBlocks.sculkGrassBlock;
                            }
                        } else if (y >= height - 3) {
                            blocks[baseIndex + y] = ModBlocks.sculkGrassBlock;
                        } else {
                            blocks[baseIndex + y] = Blocks.stone;
                        }
                    }
                }
            }
        }
    }

    @Override
    public Chunk loadChunk(int chunkX, int chunkZ) {
        return provideChunk(chunkX, chunkZ);
    }

    @Override
    public boolean chunkExists(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public void populate(IChunkProvider provider, int chunkX, int chunkZ) {
        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;

        for (int i = 0; i < 4; ++i) {
            int rx = this.rand.nextInt(16) + 8;
            int rz = this.rand.nextInt(16) + 8;
            int px = worldX + rx;
            int pz = worldZ + rz;
            int py = this.world.getTopSolidOrLiquidBlock(px, pz);

            if (this.world.isAirBlock(px, py, pz) && ModBlocks.sculkGrass.canPlaceBlockAt(this.world, px, py, pz)) {
                if (this.rand.nextInt(3) == 0 && this.world.isAirBlock(px, py + 1, pz)) {
                    this.world.setBlock(px, py, pz, ModBlocks.tallSculkGrassBottom, 0, 2);
                    this.world.setBlock(px, py + 1, pz, ModBlocks.tallSculkGrassTop, 0, 2);
                } else {
                    this.world.setBlock(px, py, pz, ModBlocks.sculkGrass, 0, 2);
                }
            }
        }
    }

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate progress) {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks() {
        return false;
    }

    @Override
    public boolean canSave() {
        return true;
    }

    @Override
    public String makeString() {
        return "SiftLevelSource";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, int x, int y, int z) {
        BiomeGenBase biome = this.world.getBiomeGenForCoords(x, z);
        return biome == null ? null : biome.getSpawnableList(creatureType);
    }

    @Override
    public ChunkPosition func_147416_a(World world, String name, int x, int y, int z) {
        return null;
    }

    @Override
    public int getLoadedChunkCount() {
        return 0;
    }

    @Override
    public void recreateStructures(int chunkX, int chunkZ) {
    }

    @Override
    public void saveExtraData() {
    }
}
