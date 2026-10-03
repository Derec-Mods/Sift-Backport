package io.github.derexxd.sift_backport;

import io.github.derexxd.sift_backport.client.model.BlubModel;
import io.github.derexxd.sift_backport.client.renderer.BlubRenderer;
import io.github.derexxd.sift_backport.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

public class SiftbackportClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Block render layers are now automatically determined by sprite properties in MC 26.x
        // No BlockRenderLayerMap registration needed

        ModelLayerRegistry.registerModelLayer(BlubModel.LAYER_LOCATION, BlubModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.BLUB, BlubRenderer::new);

        // Dimension special effects (sky rendering) API has changed in 26.x
        // Custom sky effects require a different approach via SkyRenderer
    }
}
