package io.github.derexxd.sift_backport.item;

import io.github.derexxd.sift_backport.entity.ModEntities;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {
    public static final Item BLUB_SPAWN_EGG = Registry.register(
        Registry.ITEM,
        new ResourceLocation("sift", "blub_spawn_egg"),
        new SpawnEggItem(ModEntities.BLUB, 0x4CB5DF, 0x23214B, new Item.Properties().tab(CreativeModeTab.TAB_MISC))
    );

    public static void register() {
    }
}
