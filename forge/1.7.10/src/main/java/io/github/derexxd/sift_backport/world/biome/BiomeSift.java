package io.github.derexxd.sift_backport.world.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.derexxd.sift_backport.block.ModBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeSift extends BiomeGenBase {
    public BiomeSift(int biomeId) {
        super(biomeId);
        this.setBiomeName("Sift");
        this.setHeight(new Height(0.125F, 0.05F));
        this.temperature = 0.5F;
        this.rainfall = 0.5F;
        this.setDisableRain();
        this.waterColorMultiplier = 4159204;
        this.topBlock = ModBlocks.sculkGrassBlock;
        this.fillerBlock = ModBlocks.sculkGrassBlock;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        return 6477255;
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
