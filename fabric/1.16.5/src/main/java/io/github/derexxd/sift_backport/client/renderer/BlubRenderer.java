package io.github.derexxd.sift_backport.client.renderer;

import io.github.derexxd.sift_backport.client.model.BlubModel;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BlubRenderer extends MobRenderer<BlubEntity, BlubModel<BlubEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("sift", "textures/entity/blub.png");

    public BlubRenderer(EntityRenderDispatcher dispatcher) {
        super(dispatcher, new BlubModel<>(), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(BlubEntity entity) {
        return TEXTURE;
    }
}
