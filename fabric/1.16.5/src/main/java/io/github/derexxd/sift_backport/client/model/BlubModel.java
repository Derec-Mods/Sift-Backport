package io.github.derexxd.sift_backport.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BlubModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart body;
    private final ModelPart leftHand;
    private final ModelPart rightHand;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftEar;
    private final ModelPart rightEar;

    public BlubModel() {
        this.texWidth = 64;
        this.texHeight = 64;

        this.body = new ModelPart(this);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.texOffs(0, 0).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 7.0F, 10.0F);
        this.body.texOffs(0, 27).addBox(-1.0F, -4.0F, 5.0F, 2.0F, 2.0F, 1.0F);

        this.leftHand = new ModelPart(this, 16, 17);
        this.leftHand.setPos(3.7F, 22.0F, -3.8F);
        this.leftHand.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F);

        this.rightHand = new ModelPart(this, 16, 21);
        this.rightHand.setPos(-3.8F, 22.0F, -3.8F);
        this.rightHand.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F);

        this.leftLeg = new ModelPart(this, 8, 23);
        this.leftLeg.setPos(3.8F, 22.0F, 3.8F);
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F);

        this.rightLeg = new ModelPart(this, 0, 23);
        this.rightLeg.setPos(-3.8F, 22.0F, 3.8F);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F);

        this.leftEar = new ModelPart(this, 0, 17);
        this.leftEar.setPos(2.5F, 15.0F, -2.6F);
        this.leftEar.addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F);

        this.rightEar = new ModelPart(this, 8, 17);
        this.rightEar.setPos(-3.0F, 15.0F, -2.6F);
        this.rightEar.addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.leftHand.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        this.rightHand.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;
        this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;
        this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;

        this.body.yRot = netHeadYaw * ((float) Math.PI / 180.0F) * 0.25F;
        this.body.y = 22.0F + Mth.abs(Mth.sin(limbSwing * 0.6662F)) * 1.5F * limbSwingAmount;

        this.leftEar.zRot = Mth.sin(ageInTicks * 0.1F) * 0.08F + (Mth.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount);
        this.rightEar.zRot = -Mth.sin(ageInTicks * 0.1F) * 0.08F - (Mth.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightHand.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.leftEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.rightEar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
