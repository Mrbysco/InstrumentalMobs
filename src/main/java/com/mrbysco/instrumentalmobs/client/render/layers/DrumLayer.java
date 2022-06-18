package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrbysco.instrumentalmobs.entities.DrumZombieEntity;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class DrumLayer<T extends DrumZombieEntity, M extends EntityModel<T> & ArmedModel> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public DrumLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlot.CHEST);
		if (!itemstack.isEmpty()) {
			poseStack.pushPose();

			poseStack.translate(0.0F, 0.6F, -0.55F);
			poseStack.scale(0.75F, 0.75F, 0.75F);
			if (this.getParentModel().young) {
				float f = 0.5F;
				poseStack.translate(0.0D, 0.75D, 0.0D);
				poseStack.scale(f, f, f);
			}
			poseStack.mulPose(Vector3f.XP.rotationDegrees(-10F));

			itemInHandRenderer.renderItem(entitylivingbaseIn, itemstack, ItemTransforms.TransformType.NONE, false, poseStack, bufferSource, packedLightIn);

			poseStack.popPose();
		}
	}
}
