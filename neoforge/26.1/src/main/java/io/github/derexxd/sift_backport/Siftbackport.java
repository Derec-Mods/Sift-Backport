package io.github.derexxd.sift_backport;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Siftbackport.MODID)
public class Siftbackport {
    public static final String MODID = "sift_backport";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Siftbackport(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        
        io.github.derexxd.sift_backport.block.ModBlocks.BLOCKS.register(modEventBus);
        io.github.derexxd.sift_backport.item.ModItems.ITEMS.register(modEventBus);
        io.github.derexxd.sift_backport.entity.ModEntities.ENTITY_TYPES.register(modEventBus);
        
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.addListener(io.github.derexxd.sift_backport.command.SiftTeleportCommand::onRegisterCommands);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        
        modEventBus.addListener(Siftbackport::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SIFT COMMON SETUP");
    }

    private static void addCreative(net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent event) {
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

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent event) {
            event.put(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), io.github.derexxd.sift_backport.entity.BlubEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void registerSpawnPlacements(net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent event) {
            event.register(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), net.minecraft.world.entity.SpawnPlacementTypes.ON_GROUND, net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, io.github.derexxd.sift_backport.entity.BlubEntity::checkBlubSpawnRules, net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent.Operation.REPLACE);
        }
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM SIFT CLIENT SETUP");
        }

        @SubscribeEvent
        public static void registerRenderers(net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), io.github.derexxd.sift_backport.client.renderer.BlubRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(io.github.derexxd.sift_backport.client.model.BlubModel.LAYER_LOCATION, io.github.derexxd.sift_backport.client.model.BlubModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerDimensionEffects(RegisterDimensionSpecialEffectsEvent event) {
            event.register(net.minecraft.resources.ResourceLocation.parse("sift:sift"), new net.minecraft.client.renderer.DimensionSpecialEffects(Float.NaN, true, net.minecraft.client.renderer.DimensionSpecialEffects.SkyType.NORMAL, false, false) {
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

