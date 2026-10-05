package io.github.derexxd.sift_backport.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class BlubModel<T extends Entity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        new ResourceLocation("sift", "blub"), "main"
    );

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart leftHand;
    private final ModelPart rightHand;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftEar;
    private final ModelPart rightEar;

    public BlubModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.leftHand = root.getChild("left_hand");
        this.rightHand = root.getChild("right_hand");
        this.leftLeg = root.getChild("left_leg");
        this.rightLeg = root.getChild("right_leg");
        this.leftEar = root.getChild("left_ear");
        this.rightEar = root.getChild("right_ear");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition part = mesh.getRoot();

        part.addOrReplaceChild("body", CubeListBuilder.create()
            .texOffs(0, 0).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 7.0F, 10.0F)
            .texOffs(0, 27).addBox(-1.0F, -4.0F, 5.0F, 2.0F, 2.0F, 1.0F),
            PartPose.offset(0.0F, 22.0F, 0.0F));

        part.addOrReplaceChild("left_hand", CubeListBuilder.create()
            .texOffs(16, 17).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
            PartPose.offset(3.7F, 22.0F, -3.8F));

        part.addOrReplaceChild("right_hand", CubeListBuilder.create()
            .texOffs(16, 21).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
            PartPose.offset(-3.8F, 22.0F, -3.8F));

        part.addOrReplaceChild("left_leg", CubeListBuilder.create()
            .texOffs(8, 23).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
            PartPose.offset(3.8F, 22.0F, 3.8F));

        part.addOrReplaceChild("right_leg", CubeListBuilder.create()
            .texOffs(0, 23).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
            PartPose.offset(-3.8F, 22.0F, 3.8F));

        part.addOrReplaceChild("left_ear", CubeListBuilder.create()
            .texOffs(0, 17).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F),
            PartPose.offset(2.5F, 15.0F, -2.6F));

        part.addOrReplaceChild("right_ear", CubeListBuilder.create()
            .texOffs(8, 17).addBox(-1.5F, -5.0F, -0.5F, 3.0F, 5.0F, 1.0F),
            PartPose.offset(-3.0F, 15.0F, -2.6F));

        return LayerDefinition.create(mesh, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);

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
    public ModelPart root() {
        return this.root;
    }
}
