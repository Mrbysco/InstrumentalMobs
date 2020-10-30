package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhastEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class MicrophoneLayer<T extends MicrophoneGhastEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public MicrophoneLayer(IEntityRenderer<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EquipmentSlotType.HEAD);
        if (!stack.isEmpty() && !entitylivingbaseIn.isSinging()) {
            matrixStackIn.push();

            matrixStackIn.scale(0.25F, 0.25F, 0.25F);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(-180F));
            matrixStackIn.translate(-0.5F, -6F, 2F);
            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entitylivingbaseIn, stack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.pop();
        }
    }
}
