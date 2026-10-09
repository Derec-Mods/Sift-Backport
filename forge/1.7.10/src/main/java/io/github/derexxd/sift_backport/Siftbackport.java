package io.github.derexxd.sift_backport;

import org.apache.logging.log4j.Logger;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.command.CommandSiftTeleport;
import io.github.derexxd.sift_backport.command.CommandSiftTp;
import io.github.derexxd.sift_backport.config.SiftConfig;
import io.github.derexxd.sift_backport.entity.ModEntities;
import io.github.derexxd.sift_backport.proxy.CommonProxy;
import io.github.derexxd.sift_backport.world.ModDimensions;
import io.github.derexxd.sift_backport.world.biome.ModBiomes;

@Mod(modid = Siftbackport.MODID, name = Siftbackport.NAME, version = Siftbackport.VERSION, acceptedMinecraftVersions = "[1.7.10]")
public class Siftbackport {
    public static final String MODID = "sift";
    public static final String NAME = "Sift Backport";
    public static final String VERSION = "1.0.3.0";

    @Instance(MODID)
    public static Siftbackport instance;

    @SidedProxy(
        clientSide = "io.github.derexxd.sift_backport.client.ClientProxy",
        serverSide = "io.github.derexxd.sift_backport.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        logger.info("Initializing Sift Backport dimension...");
        SiftConfig.init(event.getSuggestedConfigurationFile());
        ModBlocks.register();
        ModBiomes.register();
        ModDimensions.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ModEntities.register();
        proxy.registerRenderers();
        logger.info("Sift Backport initialized successfully.");
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSiftTeleport());
        event.registerServerCommand(new CommandSiftTp());
    }
}
