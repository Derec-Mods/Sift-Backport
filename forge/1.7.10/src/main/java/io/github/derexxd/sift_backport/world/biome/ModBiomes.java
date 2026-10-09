package io.github.derexxd.sift_backport.world.biome;

import io.github.derexxd.sift_backport.config.SiftConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class ModBiomes {
    public static BiomeGenBase siftBiome;

    public static void register() {
        int id = SiftConfig.biomeId;
        if (id < 0 || id >= BiomeGenBase.getBiomeGenArray().length || BiomeGenBase.getBiomeGenArray()[id] != null) {
            for (int i = 40; i < BiomeGenBase.getBiomeGenArray().length; i++) {
                if (BiomeGenBase.getBiomeGenArray()[i] == null) {
                    id = i;
                    break;
                }
            }
            SiftConfig.biomeId = id;
        }
        siftBiome = new BiomeSift(id);
    }
}
