package io.github.derexxd.sift_backport;

import io.github.derexxd.sift_backport.command.CommandSiftTp;
import io.github.derexxd.sift_backport.world.ModDimensions;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Siftbackport.MODID, name = Siftbackport.NAME, version = Siftbackport.VERSION)
public class Siftbackport
{
    public static final String MODID = "sift_backport";
    public static final String NAME = "Sift Backport";
    public static final String VERSION = "1.0.0.0";

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        logger.info("Initializing Sift Backport dimension...");
        ModDimensions.registerDimensions();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Sift Backport initialized successfully.");
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSiftTp());
    }
}

