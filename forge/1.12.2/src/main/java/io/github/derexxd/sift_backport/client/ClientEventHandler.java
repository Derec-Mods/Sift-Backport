package io.github.derexxd.sift_backport.client;

import io.github.derexxd.sift_backport.Siftbackport;
import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.client.renderer.BlubRenderer;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.block.BlockDoublePlant;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Siftbackport.MODID)
public class ClientEventHandler {

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(BlubEntity.class, BlubRenderer::new);

        ModelLoader.setCustomStateMapper(ModBlocks.TALL_SCULK_GRASS, new StateMap.Builder().ignore(BlockDoublePlant.VARIANT, BlockDoublePlant.FACING).build());

        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.SCULK_GRASS_BLOCK), 0,
                new ModelResourceLocation(ModBlocks.SCULK_GRASS_BLOCK.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.LIGHT_SCULK_GRASS_BLOCK), 0,
                new ModelResourceLocation(ModBlocks.LIGHT_SCULK_GRASS_BLOCK.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.SCULK_GRASS), 0,
                new ModelResourceLocation(ModBlocks.SCULK_GRASS.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.TALL_SCULK_GRASS), 0,
                new ModelResourceLocation(ModBlocks.TALL_SCULK_GRASS.getRegistryName(), "inventory"));
    }
}
