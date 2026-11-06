package io.github.derexxd.sift_backport.sound;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public class ModSounds {
    public static final SoundEvent CHISELED_SOULSHALE_BREAK = register("block.chiseled_soulshale.break");
    public static final SoundEvent CHISELED_SOULSHALE_STEP = register("block.chiseled_soulshale.step");
    public static final SoundEvent CHISELED_SOULSHALE_PLACE = register("block.chiseled_soulshale.place");
    public static final SoundEvent CHISELED_SOULSHALE_HIT = register("block.chiseled_soulshale.hit");
    public static final SoundEvent CHISELED_SOULSHALE_FALL = register("block.chiseled_soulshale.fall");

    public static final SoundType CHISELED_SOULSHALE = new SoundType(1.0F, 1.0F,
            CHISELED_SOULSHALE_BREAK,
            CHISELED_SOULSHALE_STEP,
            CHISELED_SOULSHALE_PLACE,
            CHISELED_SOULSHALE_HIT,
            CHISELED_SOULSHALE_FALL);

    private static SoundEvent register(String name) {
        ResourceLocation id = new ResourceLocation("sift", name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }
}