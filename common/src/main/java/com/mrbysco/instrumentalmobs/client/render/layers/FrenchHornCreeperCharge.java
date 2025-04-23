package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.client.render.state.FrenchRenderState;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.client.renderer.entity.state.CreeperRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FrenchHornCreeperCharge extends EnergySwirlLayer<FrenchRenderState, FrenchHornCreeperModel> {
	private static final ResourceLocation LIGHTNING_TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/creeper/creeper_armor.png");
	private final FrenchHornCreeperModel creeperModel;

	public FrenchHornCreeperCharge(RenderLayerParent<FrenchRenderState, FrenchHornCreeperModel> layerParent, EntityModelSet modelSet) {
		super(layerParent);
		this.creeperModel = new FrenchHornCreeperModel(modelSet.bakeLayer(ModelLayers.CREEPER_ARMOR));
	}

	@Override
	protected boolean isPowered(FrenchRenderState state) {
		return state.isPowered;
	}

	@Override
	protected float xOffset(float $$0) {
		return $$0 * 0.01F;
	}

	@NotNull
	@Override
	protected ResourceLocation getTextureLocation() {
		return LIGHTNING_TEXTURE;
	}

	@NotNull
	@Override
	protected FrenchHornCreeperModel model() {
		return this.creeperModel;
	}
}
