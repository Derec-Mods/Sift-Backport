package io.github.derexxd.sift_backport;

import io.github.derexxd.sift_backport.block.ModBlocks;
import io.github.derexxd.sift_backport.client.model.BlubModel;
import io.github.derexxd.sift_backport.client.renderer.BlubRenderer;
import io.github.derexxd.sift_backport.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class SiftbackportClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SCULK_GRASS, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TALL_SCULK_GRASS, RenderType.cutout());

        EntityModelLayerRegistry.registerModelLayer(BlubModel.LAYER_LOCATION, BlubModel::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.BLUB, BlubRenderer::new);

        DimensionRenderingRegistry.registerDimensionEffects(ResourceLocation.parse("sift:sift"), new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, false) {
            @Override
            public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
                return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
            }
            @Override
            public boolean isFoggyAt(int x, int y) {
                return false;
            }
        });
    }
}

