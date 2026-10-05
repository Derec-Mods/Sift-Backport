package io.github.derexxd.sift_backport.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, "sift");

    public static final ResourceKey<EntityType<?>> BLUB_KEY = ResourceKey.create(
        Registries.ENTITY_TYPE,
        Identifier.parse("sift:blub")
    );

    public static final Supplier<EntityType<BlubEntity>> BLUB = ENTITY_TYPES.register("blub",
        () -> EntityType.Builder.of(BlubEntity::new, MobCategory.CREATURE)
            .sized(0.7F, 0.75F)
            .clientTrackingRange(10)
            .build(BLUB_KEY)
    );
}
