package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MaracaSpiderEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.MaracasLayer;
import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import com.mrbysco.instrumentalmobs.entities.MaracaSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RenderMaracaSpider extends MobRenderer<MaracaSpiderEntity, MaracaSpiderModel<MaracaSpiderEntity>> {
	private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/spider.png");
	
	public RenderMaracaSpider(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MaracaSpiderModel(), 1.0F);
        this.addLayer(new MaracaSpiderEyesLayer<>(this));
        this.addLayer(new MaracasLayer(this));
	}

    @Override
    public ResourceLocation getEntityTexture(MaracaSpiderEntity entity) {
        return SPIDER_TEXTURES;
    }

    protected float getDeathMaxRotation(MaracaSpiderEntity entityLivingBaseIn) {
        return 180.0F;
    }
}
