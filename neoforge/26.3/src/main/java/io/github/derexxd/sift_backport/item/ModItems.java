package io.github.derexxd.sift_backport.item;

import io.github.derexxd.sift_backport.Siftbackport;
import io.github.derexxd.sift_backport.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Siftbackport.MODID);

    public static final DeferredItem<Item> BLUB_SPAWN_EGG = ITEMS.register("blub_spawn_egg",
        () -> new DeferredSpawnEggItem(ModEntities.BLUB, 0x4CB5DF, 0x23214B, new Item.Properties())
    );
}
