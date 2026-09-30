package io.github.derexxd.sift_backport.world.biome;

import io.github.derexxd.sift_backport.Siftbackport;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeSift extends Biome {

    public BiomeSift() {
        super(new BiomeProperties("Sift")
                .setBaseHeight(0.125F)
                .setHeightVariation(0.05F)
                .setTemperature(0.5F)
                .setRainfall(0.5F)
                .setRainDisabled());

        setRegistryName(Siftbackport.MODID, "sift");

        this.topBlock = Blocks.STONE.getDefaultState();
        this.fillerBlock = Blocks.STONE.getDefaultState();

        // 4159204 = 0x3F76E4 (matches fabric/1.20.1 & temp)
        this.waterColor = 4159204;

        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getSkyColorByTemp(float currentTemperature) {
        // 6477255 = 0x62D5C7
        return 6477255;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getGrassColorAtPos(BlockPos pos) {
        // 15757693 = 0xF0717D
        return 15757693;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getFoliageColorAtPos(BlockPos pos) {
        // 15249151 = 0xE8AFFF
        return 15249151;
    }
}
