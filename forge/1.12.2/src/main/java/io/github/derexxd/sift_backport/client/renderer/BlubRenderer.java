package io.github.derexxd.sift_backport.client.renderer;

import io.github.derexxd.sift_backport.Siftbackport;
import io.github.derexxd.sift_backport.client.model.BlubModel;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class BlubRenderer extends RenderLiving<BlubEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Siftbackport.MODID, "textures/entity/blub.png");

    public BlubRenderer(RenderManager renderManagerIn) {
        super(renderManagerIn, new BlubModel(), 0.4F);
    }

    @Override
    protected ResourceLocation getEntityTexture(BlubEntity entity) {
        return TEXTURE;
    }
}
