package io.github.derexxd.sift_backport.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeSift extends BiomeGenBase {
    public BiomeSift(int biomeId) {
        super(biomeId);
        this.setBiomeName("Sift");
        this.setHeight(new Height(0.125F, 0.05F));
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.setDisableRain();
        this.waterColorMultiplier = 7266771;
        this.topBlock = ModBlocks.sculkGrassBlock;
        this.fillerBlock = ModBlocks.sculkGrassBlock;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(BlubEntity.class, 10, 2, 4));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 7266771;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeGrassColor(int x, int y, int z) {
        return 15757693;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getBiomeFoliageColor(int x, int y, int z) {
        return 15249151;
    }
}
