package io.github.derexxd.sift_backport.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class BlubModel extends ModelBase {
    private final ModelRenderer body;
    private final ModelRenderer leftHand;
    private final ModelRenderer rightHand;
    private final ModelRenderer leftLeg;
    private final ModelRenderer rightLeg;
    private final ModelRenderer leftEar;
    private final ModelRenderer rightEar;

    public BlubModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-5.0F, -7.0F, -5.0F, 10, 7, 10);
        this.body.setTextureOffset(0, 27).addBox(-1.0F, -4.0F, 5.0F, 2, 2, 1);

        this.leftHand = new ModelRenderer(this, 16, 17);
        this.leftHand.setRotationPoint(3.7F, 22.0F, -3.8F);
        this.leftHand.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);

        this.rightHand = new ModelRenderer(this, 16, 21);
        this.rightHand.setRotationPoint(-3.8F, 22.0F, -3.8F);
        this.rightHand.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);

        this.leftLeg = new ModelRenderer(this, 8, 23);
        this.leftLeg.setRotationPoint(3.8F, 22.0F, 3.8F);
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);

        this.rightLeg = new ModelRenderer(this, 0, 23);
        this.rightLeg.setRotationPoint(-3.8F, 22.0F, 3.8F);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 2, 2);

        this.leftEar = new ModelRenderer(this, 0, 17);
        this.leftEar.setRotationPoint(2.5F, 15.0F, -2.6F);
        this.leftEar.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1);

        this.rightEar = new ModelRenderer(this, 8, 17);
        this.rightEar.setRotationPoint(-3.0F, 15.0F, -2.6F);
        this.rightEar.addBox(-1.5F, -5.0F, -0.5F, 3, 5, 1);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        this.leftHand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        this.rightHand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

        this.body.rotateAngleY = netHeadYaw * ((float) Math.PI / 180.0F) * 0.25F;
        this.body.rotationPointY = 22.0F + Math.abs(MathHelper.sin(limbSwing * 0.6662F)) * 1.5F * limbSwingAmount;

        this.leftEar.rotateAngleZ = MathHelper.sin(ageInTicks * 0.1F) * 0.08F + (MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount);
        this.rightEar.rotateAngleZ = -MathHelper.sin(ageInTicks * 0.1F) * 0.08F - (MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);
        this.body.render(scale);
        this.leftHand.render(scale);
        this.rightHand.render(scale);
        this.leftLeg.render(scale);
        this.rightLeg.render(scale);
        this.leftEar.render(scale);
        this.rightEar.render(scale);
    }
}
