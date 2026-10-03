package io.github.derexxd.sift_backport.client.renderer;

import io.github.derexxd.sift_backport.client.model.BlubModel;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;

public class BlubRenderer extends MobRenderer<BlubEntity, LivingEntityRenderState, BlubModel> {
    private static final Identifier TEXTURE = Identifier.parse("sift:textures/entity/blub.png");

    public BlubRenderer(EntityRendererProvider.Context context) {
        super(context, new BlubModel(context.bakeLayer(BlubModel.LAYER_LOCATION)), 0.4F);
    }

    @Override
    public Identifier getTextureLocation(LivingEntityRenderState renderState) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
