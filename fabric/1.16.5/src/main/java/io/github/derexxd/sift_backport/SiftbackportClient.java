package io.github.derexxd.sift_backport;

import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.client.renderer.BlubRenderer;
import io.github.derexxd.sift_backport.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.RenderType;

public class SiftbackportClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SCULK_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_SCULK_GRASS, RenderType.cutout());

        EntityRendererRegistry.INSTANCE.register(ModEntities.BLUB, (dispatcher, context) -> new BlubRenderer(dispatcher));
    }
}

