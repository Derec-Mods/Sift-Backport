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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Siftbackport.MODID)
public class Siftbackport {
    public static final String MODID = "sift_backport";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Siftbackport() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        io.github.derexxd.sift_backport.block.ModBlocks.BLOCKS.register(modEventBus);
        io.github.derexxd.sift_backport.item.ModItems.ITEMS.register(modEventBus);
        io.github.derexxd.sift_backport.entity.ModEntities.ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.addListener(io.github.derexxd.sift_backport.command.SiftTeleportCommand::onRegisterCommands);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SIFT COMMON SETUP");
    }

    private void addCreative(net.minecraftforge.event.BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.SPAWN_EGGS) {
            event.accept(io.github.derexxd.sift_backport.item.ModItems.BLUB_SPAWN_EGG);
        }
        if (event.getTabKey() == net.minecraft.world.item.CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(io.github.derexxd.sift_backport.block.ModBlocks.SCULK_GRASS_BLOCK);
            event.accept(io.github.derexxd.sift_backport.block.ModBlocks.LIGHT_SCULK_GRASS_BLOCK);
            event.accept(io.github.derexxd.sift_backport.block.ModBlocks.SCULK_GRASS);
            event.accept(io.github.derexxd.sift_backport.block.ModBlocks.TALL_SCULK_GRASS);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(net.minecraftforge.event.entity.EntityAttributeCreationEvent event) {
            event.put(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), io.github.derexxd.sift_backport.entity.BlubEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void registerSpawnPlacements(net.minecraftforge.event.entity.SpawnPlacementRegisterEvent event) {
            event.register(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), net.minecraft.world.entity.SpawnPlacements.Type.ON_GROUND, net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, io.github.derexxd.sift_backport.entity.BlubEntity::checkBlubSpawnRules, net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation.REPLACE);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM SIFT CLIENT SETUP");
            event.enqueueWork(() -> {
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(io.github.derexxd.sift_backport.block.ModBlocks.SCULK_GRASS.get(), net.minecraft.client.renderer.RenderType.cutout());
                net.minecraft.client.renderer.ItemBlockRenderTypes.setRenderLayer(io.github.derexxd.sift_backport.block.ModBlocks.TALL_SCULK_GRASS.get(), net.minecraft.client.renderer.RenderType.cutout());
            });
        }

        @SubscribeEvent
        public static void registerRenderers(net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), io.github.derexxd.sift_backport.client.renderer.BlubRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(io.github.derexxd.sift_backport.client.model.BlubModel.LAYER_LOCATION, io.github.derexxd.sift_backport.client.model.BlubModel::createBodyLayer);
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

