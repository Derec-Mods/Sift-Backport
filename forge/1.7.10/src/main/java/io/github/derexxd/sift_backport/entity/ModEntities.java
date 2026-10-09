package io.github.derexxd.sift_backport.entity;

import cpw.mods.fml.common.registry.EntityRegistry;
import io.github.derexxd.sift_backport.Siftbackport;

public class ModEntities {

    public static void register() {
        int entityId = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(BlubEntity.class, "blub", entityId, 0x40E0D0, 0x008080);
        EntityRegistry.registerModEntity(BlubEntity.class, "blub", 1, Siftbackport.instance, 80, 3, true);
    }
}
