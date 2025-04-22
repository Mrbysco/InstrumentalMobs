package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;

public class FrenchHornCreeperCharge extends EnergySwirlLayer<CreeperRenderState, FrenchHornCreeperModel> {
	private static final ResourceLocation LIGHTNING_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
	private final FrenchHornCreeperModel creeperModel;

	public FrenchHornCreeperCharge(RenderLayerParent<CreeperRenderState, FrenchHornCreeperModel> layerParent, EntityModelSet modelSet) {
		super(layerParent);
		this.creeperModel = new FrenchHornCreeperModel(modelSet.bakeLayer(ModelLayers.CREEPER_ARMOR));
	}

	protected boolean isPowered(CreeperRenderState $$0) {
		return $$0.isPowered;
	}

	protected float xOffset(float $$0) {
		return $$0 * 0.01F;
	}

	protected ResourceLocation getTextureLocation() {
		return LIGHTNING_TEXTURE;
	}

	protected FrenchHornCreeperModel model() {
		return this.creeperModel;
	}
}
