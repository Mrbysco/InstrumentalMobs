package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MaracaSpiderEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.MaracasLayer;
import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import com.mrbysco.instrumentalmobs.entities.MaracaSpider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MaracaSpiderRenderer extends MobRenderer<MaracaSpider, MaracaSpiderModel<MaracaSpider>> {
	private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/spider.png");

	public MaracaSpiderRenderer(EntityRendererProvider.Context context) {
		super(context, new MaracaSpiderModel<>(context.bakeLayer(ModelLayers.SPIDER)), 1.0F);
		this.addLayer(new MaracaSpiderEyesLayer<>(this));
		this.addLayer(new MaracasLayer<>(this, context.getItemInHandRenderer()));
	}

	@Override
	public ResourceLocation getTextureLocation(MaracaSpider maracaSpider) {
		return SPIDER_TEXTURES;
	}

	protected float getFlipDegrees(MaracaSpider maracaSpider) {
		return 180.0F;
	}
}
