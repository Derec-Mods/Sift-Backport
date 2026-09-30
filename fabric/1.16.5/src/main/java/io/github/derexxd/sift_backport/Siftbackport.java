package io.github.derexxd.sift_backport;

import io.github.derexxd.sift_backport.command.SiftTeleportCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Siftbackport implements ModInitializer {
    public static final String MODID = "sift_backport";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("HELLO FROM SIFT FABRIC INITIALIZE " + MODID);
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            SiftTeleportCommand.register(dispatcher);
        });
    }
}

