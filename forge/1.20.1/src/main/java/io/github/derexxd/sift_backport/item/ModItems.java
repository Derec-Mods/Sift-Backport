package io.github.derexxd.sift_backport.item;

import io.github.derexxd.sift_backport.Siftbackport;
import io.github.derexxd.sift_backport.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Siftbackport.MODID);

    public static final RegistryObject<Item> BLUB_SPAWN_EGG = ITEMS.register("blub_spawn_egg",
        () -> new ForgeSpawnEggItem(ModEntities.BLUB, 0x4CB5DF, 0x23214B, new Item.Properties())
    );
}
