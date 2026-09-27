package io.github.derexxd.siftbackport;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Siftbackport implements ModInitializer {
    public static final String MODID = "siftbackport";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("HELLO FROM SIFT FABRIC INITIALIZE " + MODID);
    }
}
