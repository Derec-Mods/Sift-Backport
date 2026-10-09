package io.github.derexxd.sift_backport.client.renderer;

import io.github.derexxd.sift_backport.Siftbackport;
import io.github.derexxd.sift_backport.client.model.BlubModel;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class BlubRenderer extends RenderLiving {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Siftbackport.MODID, "textures/entity/blub.png");

    public BlubRenderer() {
        super(new BlubModel(), 0.4F);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return TEXTURE;
    }
}
