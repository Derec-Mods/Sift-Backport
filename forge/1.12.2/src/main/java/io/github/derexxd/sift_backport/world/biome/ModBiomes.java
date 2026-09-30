package io.github.derexxd.sift_backport.world.biome;

import io.github.derexxd.sift_backport.Siftbackport;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Siftbackport.MODID)
public class ModBiomes {

    public static final Biome SIFT_BIOME = new BiomeSift();

    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        event.getRegistry().register(SIFT_BIOME);
    }
}
