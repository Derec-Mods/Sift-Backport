package io.github.derexxd.sift_backport.config;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class SiftConfig {
    public static int dimensionId = 23;
    public static int biomeId = 180;

    public static void init(File configFile) {
        Configuration config = new Configuration(configFile);
        config.load();
        dimensionId = config.get(
            Configuration.CATEGORY_GENERAL,
            "dimensionId",
            23,
            "The dimension ID for The Sift dimension."
        ).getInt();
        biomeId = config.get(
            Configuration.CATEGORY_GENERAL,
            "biomeId",
            180,
            "The biome ID for the Sift biome (0-255)."
        ).getInt();
        if (config.hasChanged()) {
            config.save();
        }
    }
}
