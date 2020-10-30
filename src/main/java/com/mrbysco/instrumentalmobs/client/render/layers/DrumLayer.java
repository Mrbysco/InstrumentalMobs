package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.entities.DrumZombieEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;

public class DrumLayer<T extends DrumZombieEntity, M extends EntityModel<T> & IHasArm> extends LayerRenderer<T, M> {
	public DrumLayer(IEntityRenderer<T, M> p_i50934_1_) {
		super(p_i50934_1_);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EquipmentSlotType.CHEST);
		if (!itemstack.isEmpty()) {
			matrixStackIn.push();

			matrixStackIn.translate(0.0F, 0.6F, -0.55F);
			matrixStackIn.scale(0.75F, 0.75F, 0.75F);
			if (this.getEntityModel().isChild) {
				float f = 0.5F;
				matrixStackIn.translate(0.0D, 0.75D, 0.0D);
				matrixStackIn.scale(f, f, f);
			}
			matrixStackIn.rotate(Vector3f.XP.rotation(-10F));

			Minecraft.getInstance().getFirstPersonRenderer().renderItemSide(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, packedLightIn);

			matrixStackIn.pop();
		}
	}
}
