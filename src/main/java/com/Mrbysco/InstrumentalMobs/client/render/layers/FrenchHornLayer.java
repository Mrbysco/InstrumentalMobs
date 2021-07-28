package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasHead;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class FrenchHornLayer<T extends FrenchHornCreeperEntity, M extends EntityModel<T> & IHasHead> extends LayerRenderer<T, M> {
    public FrenchHornLayer(IEntityRenderer<T, M> p_i50934_1_) {
        super(p_i50934_1_);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlotType.MAINHAND);
        if (!itemstack.isEmpty()) {
            matrixStackIn.pushPose();
            this.getParentModel().getHead().translateAndRotate(matrixStackIn);

            matrixStackIn.scale(0.75F, 0.75F, 0.75F);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F));
            matrixStackIn.translate(0.85F, 0.15F, 0.0F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-20F));

            Minecraft.getInstance().getItemInHandRenderer().renderItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, packedLightIn);

            matrixStackIn.popPose();
        }
    }
}
