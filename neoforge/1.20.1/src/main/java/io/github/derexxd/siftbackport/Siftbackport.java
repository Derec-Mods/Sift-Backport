package io.github.derexxd.siftbackport;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Siftbackport.MODID)
public class Siftbackport {
    public static final String MODID = "siftbackport";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Siftbackport(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SIFT COMMON SETUP");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM SIFT CLIENT SETUP");
            
            event.enqueueWork(() -> {
                net.minecraft.client.renderer.DimensionSpecialEffects.EFFECTS.put(new net.minecraft.resources.ResourceLocation("sift", "sift"), new net.minecraft.client.renderer.DimensionSpecialEffects(Float.NaN, true, net.minecraft.client.renderer.DimensionSpecialEffects.SkyType.NORMAL, false, false) {
                    @Override
                    public net.minecraft.world.phys.Vec3 getBrightnessDependentFogColor(net.minecraft.world.phys.Vec3 biomeFogColor, float daylight) {
                        return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
                    }
                    @Override
                    public boolean isFoggyAt(int x, int y) {
                        return false;
                    }
                });
            });
        }
    }
}
