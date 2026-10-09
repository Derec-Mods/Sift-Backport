package io.github.derexxd.sift_backport.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import io.github.derexxd.sift_backport.client.renderer.BlubRenderer;
import io.github.derexxd.sift_backport.entity.BlubEntity;
import io.github.derexxd.sift_backport.proxy.CommonProxy;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(BlubEntity.class, new BlubRenderer());
    }
}
