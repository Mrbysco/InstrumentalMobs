package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.TubaEndermanModel;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class TubaEndermanEyesLayer<S extends EndermanRenderState> extends EyesLayer<S, TubaEndermanModel<S>> {
	private static final RenderType RENDER_TYPE = RenderTypes.eyes(Identifier.withDefaultNamespace("textures/entity/enderman/enderman_eyes.png"));

	public TubaEndermanEyesLayer(RenderLayerParent<S, TubaEndermanModel<S>> layerParent) {
		super(layerParent);
	}

	@NotNull
	@Override
	public RenderType renderType() {
		return RENDER_TYPE;
	}
}