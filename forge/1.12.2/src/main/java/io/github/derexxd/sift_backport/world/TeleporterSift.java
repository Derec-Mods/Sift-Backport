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
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x, 180, z);
        while (pos.getY() < 250 && (!this.world.isAirBlock(pos) || !this.world.isAirBlock(pos.up()))) {
            pos.setY(pos.getY() + 1);
        }

        entityIn.setLocationAndAngles(entityIn.posX, (double) pos.getY(), entityIn.posZ, entityIn.rotationYaw, 0.0F);
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
