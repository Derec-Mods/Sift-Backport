package io.github.derexxd.sift_backport.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, "sift");

    public static final DeferredHolder<SoundEvent, SoundEvent> CHISELED_SOULSHALE_BREAK = register("block.chiseled_soulshale.break");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHISELED_SOULSHALE_STEP = register("block.chiseled_soulshale.step");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHISELED_SOULSHALE_PLACE = register("block.chiseled_soulshale.place");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHISELED_SOULSHALE_HIT = register("block.chiseled_soulshale.hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> CHISELED_SOULSHALE_FALL = register("block.chiseled_soulshale.fall");

    public static final DeferredSoundType CHISELED_SOULSHALE = new DeferredSoundType(1.0F, 1.0F,
            CHISELED_SOULSHALE_BREAK,
            CHISELED_SOULSHALE_STEP,
            CHISELED_SOULSHALE_PLACE,
            CHISELED_SOULSHALE_HIT,
            CHISELED_SOULSHALE_FALL);

    private static DeferredHolder<SoundEvent, SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.parse("sift:" + name)));
    }
}