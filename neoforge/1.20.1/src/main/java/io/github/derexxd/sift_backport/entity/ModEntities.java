package io.github.derexxd.sift_backport.entity;

import io.github.derexxd.sift_backport.Siftbackport;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "sift");

    public static final RegistryObject<EntityType<BlubEntity>> BLUB = ENTITY_TYPES.register("blub",
        () -> EntityType.Builder.of(BlubEntity::new, MobCategory.CREATURE)
            .sized(0.7F, 0.75F)
            .clientTrackingRange(10)
            .build("blub")
    );
}
