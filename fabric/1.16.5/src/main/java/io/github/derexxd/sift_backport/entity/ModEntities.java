package io.github.derexxd.sift_backport.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntities {
    public static final EntityType<BlubEntity> BLUB = Registry.register(
        Registry.ENTITY_TYPE,
        new ResourceLocation("sift", "blub"),
        EntityType.Builder.of(BlubEntity::new, MobCategory.CREATURE)
            .sized(0.7F, 0.75F)
            .clientTrackingRange(10)
            .build("blub")
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(BLUB, BlubEntity.createAttributes());
    }
}
