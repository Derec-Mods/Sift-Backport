package io.github.derexxd.sift_backport.world;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterSift extends Teleporter {

    private final WorldServer world;

    public TeleporterSift(WorldServer worldIn) {
        super(worldIn);
        this.world = worldIn;
    }

    @Override
    public void placeInPortal(Entity entityIn, double x, double y, double z, float rotationYaw) {
        int blockX = MathHelper.floor_double(entityIn.posX);
        int blockZ = MathHelper.floor_double(entityIn.posZ);
        int topY = this.world.getTopSolidOrLiquidBlock(blockX, blockZ);

        if (topY <= 0) {
            topY = 65;
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dz = -1; dz <= 1; ++dz) {
                    this.world.setBlock(blockX + dx, topY - 1, blockZ + dz, Blocks.stone);
                    this.world.setBlockToAir(blockX + dx, topY, blockZ + dz);
                    this.world.setBlockToAir(blockX + dx, topY + 1, blockZ + dz);
                }
            }
        }

        entityIn.setLocationAndAngles((double) blockX + 0.5D, (double) topY + 1.0D, (double) blockZ + 0.5D, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = 0.0D;
        entityIn.motionY = 0.0D;
        entityIn.motionZ = 0.0D;
    }

    @Override
    public boolean placeInExistingPortal(Entity entityIn, double x, double y, double z, float rotationYaw) {
        this.placeInPortal(entityIn, x, y, z, rotationYaw);
        return true;
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return true;
    }
}
