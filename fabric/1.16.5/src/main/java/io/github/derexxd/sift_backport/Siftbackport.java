package io.github.derexxd.sift_backport;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Siftbackport implements ModInitializer {
    public static final String MODID = "sift_backport";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("HELLO FROM SIFT FABRIC INITIALIZE " + MODID);
    }
}

