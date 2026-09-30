package io.github.derexxd.sift_backport;

import net.fabricmc.api.ClientModInitializer;
// import net.fabricmc.fabric.api.client.rendering.v1.DimensionRenderingRegistry;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

public class SiftbackportClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Dimension rendering registry might not be available in 1.16.5 Fabric API
        /*
        DimensionRenderingRegistry.registerDimensionEffects(new ResourceLocation("sift", "sift"), new DimensionSpecialEffects(Float.NaN, true, DimensionSpecialEffects.SkyType.NORMAL, false, false) {
            @Override
            public Vec3 getBrightnessDependentFogColor(Vec3 biomeFogColor, float daylight) {
                return biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F);
            }
            @Override
            public boolean isFoggyAt(int x, int y) {
                return false;
            }
        });
        */
    }
}

