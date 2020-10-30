package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class TubaEndermanHeldItemLayer<T extends EndermanEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    public TubaEndermanHeldItemLayer(IEntityRenderer<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack stack = entitylivingbaseIn.getItemStackFromSlot(EquipmentSlotType.HEAD);
        if (!stack.isEmpty()) {
            matrixStackIn.push();

            matrixStackIn.translate(0.0F, -0.73F, -0.775F);
            matrixStackIn.scale(-1.5F, -1.5F, 1.5F);

            matrixStackIn.rotate(new Vector3f(1.0F, 90.0F, 0.0F).rotation(90F));
            matrixStackIn.rotate(new Vector3f( 0.0F, 0.0F, 1.0F).rotation(-90F));
            matrixStackIn.rotate(new Vector3f(1.0F, -1.0F, 1.0F).rotation(-10F));

            Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entitylivingbaseIn, stack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, packedLightIn);
            matrixStackIn.pop();
        }
    }
}