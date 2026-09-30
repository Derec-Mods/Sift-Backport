package io.github.derexxd.sift_backport.world;

import io.github.derexxd.sift_backport.world.biome.ModBiomes;
import io.github.derexxd.sift_backport.world.gen.ChunkGeneratorSift;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderSift extends WorldProvider {

    @Override
    protected void init() {
        this.hasSkyLight = true;
        this.biomeProvider = new BiomeProviderSingle(ModBiomes.SIFT_BIOME);
    }

    @Override
    public DimensionType getDimensionType() {
        return ModDimensions.SIFT_DIM_TYPE;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkGeneratorSift(this.world, this.world.getSeed());
    }

    @Override
    public boolean canRespawnHere() {
        // Bed works in Sift dimension (matches "bed_works": true)
        return true;
    }

    @Override
    public boolean isSurfaceWorld() {
        return true;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        return this.world.getGroundAboveSeaLevel(new BlockPos(x, 0, z)).getMaterial().isSolid();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3d getFogColor(float celestialAngle, float partialTicks) {
        float daylight = MathHelper.cos(celestialAngle * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        daylight = MathHelper.clamp(daylight, 0.0F, 1.0F);

        // Consistent with DimensionSpecialEffects in 1.20/1.21:
        // biomeFogColor.multiply(daylight * 0.94F + 0.06F, daylight * 0.94F + 0.06F, daylight * 0.91F + 0.09F)
        // Biome fog color: 16758706 (0xFFB7B2)
        float r = 0.999F * (daylight * 0.94F + 0.06F);
        float g = 0.718F * (daylight * 0.94F + 0.06F);
        float b = 0.698F * (daylight * 0.91F + 0.09F);
        return new Vec3d((double)r, (double)g, (double)b);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3d getSkyColor(Entity cameraEntity, float partialTicks) {
        // Sky color: 6477255 (0x62D5C7)
        return new Vec3d(0.384D, 0.835D, 0.780D);
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
