package io.github.derexxd.sift_backport.item;

import io.github.derexxd.sift_backport.entity.ModEntities;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {
    public static final Item BLUB_SPAWN_EGG = Registry.register(
        BuiltInRegistries.ITEM,
        ResourceLocation.parse("sift:blub_spawn_egg"),
        new SpawnEggItem(ModEntities.BLUB, 0x4CB5DF, 0x23214B, new Item.Properties())
    );

    public static void register() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.accept(BLUB_SPAWN_EGG);
        });
    }
}
