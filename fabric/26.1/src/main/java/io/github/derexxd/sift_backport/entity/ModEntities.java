package io.github.derexxd.sift_backport.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> BLUB_KEY = ResourceKey.create(
        Registries.ENTITY_TYPE,
        Identifier.parse("sift:blub")
    );

    public static final EntityType<BlubEntity> BLUB = Registry.register(
        BuiltInRegistries.ENTITY_TYPE,
        BLUB_KEY,
        FabricEntityType.Builder.createMob(
            BlubEntity::new,
            MobCategory.CREATURE,
            builder -> builder
                .defaultAttributes(BlubEntity::createAttributes)
                .spawnPlacement(
                    SpawnPlacementTypes.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    BlubEntity::checkBlubSpawnRules
                )
        )
        .sized(0.7F, 0.75F)
        .clientTrackingRange(10)
        .build(BLUB_KEY)
    );

    public static void register() {
    }
}
