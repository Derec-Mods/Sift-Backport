package io.github.derexxd.sift_backport.world;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterSift extends Teleporter {

    private final WorldServer world;

    public TeleporterSift(WorldServer worldIn) {
        super(worldIn);
        this.world = worldIn;
    }

    @Override
    public void placeInPortal(Entity entityIn, float rotationYaw) {
        int x = (int) entityIn.posX;
        int z = (int) entityIn.posZ;
        BlockPos topPos = this.world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z));

        if (topPos.getY() <= 0) {
            topPos = new BlockPos(x, 65, z);
            for (int dx = -1; dx <= 1; ++dx) {
                for (int dz = -1; dz <= 1; ++dz) {
                    this.world.setBlockState(topPos.add(dx, -1, dz), Blocks.STONE.getDefaultState());
                    this.world.setBlockToAir(topPos.add(dx, 0, dz));
                    this.world.setBlockToAir(topPos.add(dx, 1, dz));
                }
            }
        }

        entityIn.setLocationAndAngles((double) topPos.getX() + 0.5D, (double) topPos.getY() + 1.0D, (double) topPos.getZ() + 0.5D, entityIn.rotationYaw, 0.0F);
        entityIn.motionX = 0.0D;
        entityIn.motionY = 0.0D;
        entityIn.motionZ = 0.0D;
    }

    @Override
    public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
        this.placeInPortal(entityIn, rotationYaw);
        return true;
    }

    @Override
    public boolean makePortal(Entity entityIn) {
        return true;
    }
}
