package io.github.derexxd.sift_backport.world;

import io.github.derexxd.sift_backport.config.SiftConfig;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {

    public static DimensionType SIFT_DIM_TYPE;
    public static int SIFT_DIM_ID;

    public static void registerDimensions() {
        SIFT_DIM_ID = SiftConfig.dimensionId;
        if (DimensionManager.isDimensionRegistered(SIFT_DIM_ID)) {
            SIFT_DIM_ID = DimensionManager.getNextFreeDimId();
            SiftConfig.dimensionId = SIFT_DIM_ID;
        }
        SIFT_DIM_TYPE = DimensionType.register("sift", "_sift", SIFT_DIM_ID, WorldProviderSift.class, false);
        DimensionManager.registerDimension(SIFT_DIM_ID, SIFT_DIM_TYPE);
    }
}
