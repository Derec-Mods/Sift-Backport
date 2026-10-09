package io.github.derexxd.sift_backport.world;

import io.github.derexxd.sift_backport.config.SiftConfig;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
    public static int SIFT_DIM_ID;

    public static void register() {
        SIFT_DIM_ID = SiftConfig.dimensionId;
        if (DimensionManager.isDimensionRegistered(SIFT_DIM_ID)) {
            for (int id = 2; id < 1000; id++) {
                if (!DimensionManager.isDimensionRegistered(id)) {
                    SIFT_DIM_ID = id;
                    break;
                }
            }
            SiftConfig.dimensionId = SIFT_DIM_ID;
        }
        DimensionManager.registerProviderType(SIFT_DIM_ID, WorldProviderSift.class, false);
        DimensionManager.registerDimension(SIFT_DIM_ID, SIFT_DIM_ID);
    }
}
