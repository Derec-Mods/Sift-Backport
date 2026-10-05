package io.github.derexxd.sift_backport.item;

import io.github.derexxd.sift_backport.entity.ModEntities;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems("sift");

    public static final ResourceKey<Item> BLUB_SPAWN_EGG_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.parse("sift:blub_spawn_egg")
    );

    public static final DeferredItem<Item> BLUB_SPAWN_EGG = ITEMS.register("blub_spawn_egg",
        () -> new SpawnEggItem(new Item.Properties().setId(BLUB_SPAWN_EGG_KEY).spawnEgg(ModEntities.BLUB.get()))
    );
}
