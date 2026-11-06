package io.github.derexxd.sift_backport.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "sift");

    public static final RegistryObject<SoundEvent> CHISELED_SOULSHALE_BREAK = register("block.chiseled_soulshale.break");
    public static final RegistryObject<SoundEvent> CHISELED_SOULSHALE_STEP = register("block.chiseled_soulshale.step");
    public static final RegistryObject<SoundEvent> CHISELED_SOULSHALE_PLACE = register("block.chiseled_soulshale.place");
    public static final RegistryObject<SoundEvent> CHISELED_SOULSHALE_HIT = register("block.chiseled_soulshale.hit");
    public static final RegistryObject<SoundEvent> CHISELED_SOULSHALE_FALL = register("block.chiseled_soulshale.fall");

    public static final ForgeSoundType CHISELED_SOULSHALE = new ForgeSoundType(1.0F, 1.0F,
            CHISELED_SOULSHALE_BREAK,
            CHISELED_SOULSHALE_STEP,
            CHISELED_SOULSHALE_PLACE,
            CHISELED_SOULSHALE_HIT,
            CHISELED_SOULSHALE_FALL);

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("sift", name)));
    }
}