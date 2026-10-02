package io.github.derexxd.sift_backport.entity;

import io.github.derexxd.sift_backport.Siftbackport;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

@Mod.EventBusSubscriber(modid = Siftbackport.MODID)
public class ModEntities {
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        event.getRegistry().register(
            EntityEntryBuilder.create()
                .entity(BlubEntity.class)
                .id(new ResourceLocation(Siftbackport.MODID, "blub"), 1)
                .name(Siftbackport.MODID + ".blub")
                .tracker(80, 3, true)
                .egg(0x40E0D0, 0x008080)
                .build()
        );
    }
}
