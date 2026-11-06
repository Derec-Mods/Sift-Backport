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
        int targetY = 180;
        while (targetY < 250 && (!this.world.isAirBlock(blockX, targetY, blockZ) || !this.world.isAirBlock(blockX, targetY + 1, blockZ))) {
            targetY++;
        }

        entityIn.setLocationAndAngles(entityIn.posX, (double) targetY, entityIn.posZ, entityIn.rotationYaw, 0.0F);
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
