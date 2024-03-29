package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class FrenchHornLayer<T extends FrenchHornCreeper, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public FrenchHornLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLightIn, T frenchHornCreeper,
					   float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = frenchHornCreeper.getItemBySlot(EquipmentSlot.MAINHAND);
		if (!itemstack.isEmpty()) {
			poseStack.pushPose();
			this.getParentModel().getHead().translateAndRotate(poseStack);

			poseStack.scale(0.75F, 0.75F, 0.75F);
			poseStack.mulPose(Axis.YP.rotationDegrees(90F));
			poseStack.translate(0.85F, 0.15F, 0.0F);
			poseStack.mulPose(Axis.ZP.rotationDegrees(-20F));

			itemInHandRenderer.renderItem(frenchHornCreeper, itemstack, ItemDisplayContext.NONE, false, poseStack, bufferSource, packedLightIn);

			poseStack.popPose();
		}
	}
}
