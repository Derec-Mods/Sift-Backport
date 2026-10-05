package io.github.derexxd.sift_backport;

import com.mojang.logging.LogUtils;
import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.client.model.BlubModel;
import io.github.derexxd.sift_backport.client.renderer.BlubRenderer;
import io.github.derexxd.sift_backport.command.SiftTeleportCommand;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import io.github.derexxd.sift_backport.entity.ModEntities;
import io.github.derexxd.sift_backport.item.ModItems;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import org.slf4j.Logger;

@Mod(Siftbackport.MODID)
public class Siftbackport {
    public static final String MODID = "sift_backport";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Siftbackport(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(SiftTeleportCommand::onRegisterCommands);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(Siftbackport::addCreative);
        modEventBus.addListener(ModEventBusEvents::entityAttributeEvent);
        modEventBus.addListener(ModEventBusEvents::registerSpawnPlacements);

        if (FMLEnvironment.getDist() == Dist.CLIENT) {
            ClientModEvents.init(modEventBus);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SIFT COMMON SETUP");
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(ModItems.BLUB_SPAWN_EGG);
        }
        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.SCULK_GRASS_BLOCK);
            event.accept(ModBlocks.LIGHT_SCULK_GRASS_BLOCK);
            event.accept(ModBlocks.SCULK_GRASS);
            event.accept(ModBlocks.TALL_SCULK_GRASS);
        }
    }

    public static class ModEventBusEvents {
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntities.BLUB.get(), BlubEntity.createAttributes().build());
        }

        public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
            event.register(ModEntities.BLUB.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlubEntity::checkBlubSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
        }
    }

    public static class ClientModEvents {
        public static void init(IEventBus modEventBus) {
            modEventBus.addListener(ClientModEvents::onClientSetup);
            modEventBus.addListener(ClientModEvents::registerRenderers);
            modEventBus.addListener(ClientModEvents::registerLayerDefinitions);
        }

        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM SIFT CLIENT SETUP");
        }

        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntities.BLUB.get(), BlubRenderer::new);
        }

        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(BlubModel.LAYER_LOCATION, BlubModel::createBodyLayer);
        }
    }
}
