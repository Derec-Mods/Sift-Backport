package io.github.derexxd.sift_backport.item;

import io.github.derexxd.sift_backport.entity.ModEntities;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {
    public static final ResourceKey<Item> BLUB_SPAWN_EGG_KEY = ResourceKey.create(
        Registries.ITEM,
        Identifier.parse("sift:blub_spawn_egg")
    );

    public static final Item BLUB_SPAWN_EGG = Registry.register(
        BuiltInRegistries.ITEM,
        BLUB_SPAWN_EGG_KEY,
        new SpawnEggItem(new Item.Properties().setId(BLUB_SPAWN_EGG_KEY).spawnEgg(ModEntities.BLUB))
    );

    public static void register() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.SPAWN_EGGS).register(output -> {
            output.accept(BLUB_SPAWN_EGG);
        });
    }
}
