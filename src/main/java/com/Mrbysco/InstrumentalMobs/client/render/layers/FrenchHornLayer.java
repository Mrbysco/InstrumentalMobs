package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class FrenchHornLayer<T extends FrenchHornCreeperEntity, M extends EntityModel<T> & HeadedModel> extends RenderLayer<T, M> {
	private final ItemInHandRenderer itemInHandRenderer;

	public FrenchHornLayer(RenderLayerParent<T, M> layerParent, ItemInHandRenderer itemInHandRenderer) {
		super(layerParent);
		this.itemInHandRenderer = itemInHandRenderer;
	}

	@Override
	public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ItemStack itemstack = entitylivingbaseIn.getItemBySlot(EquipmentSlot.MAINHAND);
		if (!itemstack.isEmpty()) {
			matrixStackIn.pushPose();
			this.getParentModel().getHead().translateAndRotate(matrixStackIn);

			matrixStackIn.scale(0.75F, 0.75F, 0.75F);
			matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F));
			matrixStackIn.translate(0.85F, 0.15F, 0.0F);
			matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(-20F));

			itemInHandRenderer.renderItem(entitylivingbaseIn, itemstack, ItemTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, packedLightIn);

			matrixStackIn.popPose();
		}
	}
}
