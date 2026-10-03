package io.github.derexxd.sift_backport;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
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

        io.github.derexxd.sift_backport.block.ModBlocks.BLOCKS.register(modEventBus);
        io.github.derexxd.sift_backport.item.ModItems.ITEMS.register(modEventBus);
        io.github.derexxd.sift_backport.entity.ModEntities.ENTITY_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(io.github.derexxd.sift_backport.command.SiftTeleportCommand::onRegisterCommands);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM SIFT COMMON SETUP");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), io.github.derexxd.sift_backport.entity.BlubEntity.createAttributes().build());
        }

        @SubscribeEvent
        public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
            event.register(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), net.minecraft.world.entity.SpawnPlacements.Type.ON_GROUND, net.minecraft.world.level.levelgen.Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, io.github.derexxd.sift_backport.entity.BlubEntity::checkBlubSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM SIFT CLIENT SETUP");
        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(io.github.derexxd.sift_backport.entity.ModEntities.BLUB.get(), io.github.derexxd.sift_backport.client.renderer.BlubRenderer::new);
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(io.github.derexxd.sift_backport.client.model.BlubModel.LAYER_LOCATION, io.github.derexxd.sift_backport.client.model.BlubModel::createBodyLayer);
        }
    }
}
