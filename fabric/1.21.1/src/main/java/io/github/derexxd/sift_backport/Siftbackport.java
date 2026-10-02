package io.github.derexxd.sift_backport;

import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.command.SiftTeleportCommand;
import io.github.derexxd.sift_backport.entity.ModEntities;
import io.github.derexxd.sift_backport.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Siftbackport implements ModInitializer {
    public static final String MODID = "sift_backport";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("HELLO FROM SIFT FABRIC INITIALIZE " + MODID);
        ModBlocks.register();
        ModEntities.register();
        ModItems.register();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            SiftTeleportCommand.register(dispatcher);
        });
    }
}

