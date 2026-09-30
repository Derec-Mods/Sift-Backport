package io.github.derexxd.sift_backport.config;

import io.github.derexxd.sift_backport.Siftbackport;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Siftbackport.MODID, name = Siftbackport.MODID)
public class SiftConfig {

    @Config.Comment("The dimension ID for The Sift dimension.")
    @Config.Name("Dimension ID")
    public static int dimensionId = 23;

    @Mod.EventBusSubscriber(modid = Siftbackport.MODID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(Siftbackport.MODID)) {
                ConfigManager.sync(Siftbackport.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
