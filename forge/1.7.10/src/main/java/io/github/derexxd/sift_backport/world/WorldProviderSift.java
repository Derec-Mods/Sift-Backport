package io.github.derexxd.sift_backport.world;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.github.derexxd.sift_backport.world.biome.ModBiomes;
import io.github.derexxd.sift_backport.world.gen.ChunkProviderSift;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderSift extends WorldProvider {

    @Override
    public String getDimensionName() {
        return "The Sift";
    }

    @Override
    protected void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldChunkManagerHell(ModBiomes.siftBiome, 0.0F);
        this.hasNoSky = false;
        this.dimensionId = ModDimensions.SIFT_DIM_ID;
    }

    @Override
    public IChunkProvider createChunkGenerator() {
        return new ChunkProviderSift(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 0.0F;
    }

    @Override
    protected void generateLightBrightnessTable() {
        for (int i = 0; i <= 15; i++) {
            float base = 1.0F - (float) i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - base) / (base * 3.0F + 1.0F) * (1.0F - 0.5F) + 0.5F;
        }
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        Block block = this.worldObj.getTopBlock(x, z);
        return block != null && block.getMaterial().isSolid();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float celestialAngle, float partialTicks) {
        float daylight = MathHelper.cos(celestialAngle * ((float) Math.PI * 2.0F)) * 2.0F + 0.5F;
        daylight = MathHelper.clamp_float(daylight, 0.0F, 1.0F);

        float r = (110.0F / 255.0F) * (daylight * 0.94F + 0.06F);
        float g = (225.0F / 255.0F) * (daylight * 0.94F + 0.06F);
        float b = (211.0F / 255.0F) * (daylight * 0.91F + 0.09F);
        return Vec3.createVectorHelper(r, g, b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return Vec3.createVectorHelper(110.0D / 255.0D, 225.0D / 255.0D, 211.0D / 255.0D);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getCloudHeight() {
        return 192.0F;
    }

    @Override
    public int getAverageGroundLevel() {
        return 64;
    }

    @Override
    public double getHorizon() {
        return 0.0D;
    }
}
