package io.github.derexxd.sift_backport;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import org.slf4j.Logger;

@Mod(Siftbackport.MODID)
public class Siftbackport {
    // have to hardcode this because you can't read from gradle.properties at runtime dyamically
    public static final String MODID = "sift_backport";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Siftbackport(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(io.github.derexxd.sift_backport.command.SiftTeleportCommand::onRegisterCommands);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SIFT COMMON SETUP");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM SIFT CLIENT SETUP");
        }

        @SubscribeEvent
        public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
            event.register(new net.minecraft.resources.ResourceLocation("sift", "sift"), new net.minecraft.client.renderer.DimensionSpecialEffects(Float.NaN, true, net.minecraft.client.renderer.DimensionSpecialEffects.SkyType.NORMAL, false, false) {
                @Override
                public net.minecraft.world.phys.Vec3 getBrightnessDependentFogColor(net.minecraft.world.phys.Vec3 biomeFogColor, float daylight) {
                    return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
                }
                @Override
                public boolean isFoggyAt(int x, int y) {
                    return false;
                }
            });
        }
    }
}

