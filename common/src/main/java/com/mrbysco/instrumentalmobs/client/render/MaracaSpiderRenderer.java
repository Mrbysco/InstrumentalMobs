package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.MaracaSpiderEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.MaracasLayer;
import com.mrbysco.instrumentalmobs.client.render.model.MaracaSpiderModel;
import com.mrbysco.instrumentalmobs.client.render.state.MaracaRenderState;
import com.mrbysco.instrumentalmobs.entities.MaracaSpider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class MaracaSpiderRenderer extends MobRenderer<MaracaSpider, MaracaRenderState, MaracaSpiderModel> {
	private static final ResourceLocation SPIDER_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/spider/spider.png");

	public MaracaSpiderRenderer(EntityRendererProvider.Context context) {
		super(context, new MaracaSpiderModel(context.bakeLayer(ModelLayers.SPIDER)), 1.0F);
		this.addLayer(new MaracaSpiderEyesLayer(this));
		this.addLayer(new MaracasLayer(this));
	}

	@NotNull
	@Override
	public MaracaRenderState createRenderState() {
		return new MaracaRenderState();
	}

	@Override
	public void extractRenderState(@NotNull MaracaSpider spider, @NotNull MaracaRenderState state, float partialTick) {
		super.extractRenderState(spider, state, partialTick);
		if (state instanceof MaracaRenderState maracaState) {
			maracaState.isAttacking = spider.isAttacking();
			itemModelResolver.updateForLiving(
					maracaState.mainItem, spider.getMainHandItem(), ItemDisplayContext.THIRD_PERSON_LEFT_HAND, false, spider
			);
			itemModelResolver.updateForLiving(
					maracaState.offItem, spider.getOffhandItem(), ItemDisplayContext.THIRD_PERSON_RIGHT_HAND, false, spider
			);
		}
	}

	@NotNull
	@Override
	public ResourceLocation getTextureLocation(@NotNull MaracaRenderState state) {
		return SPIDER_TEXTURES;
	}

	@Override
	protected float getFlipDegrees() {
		return 180.0F;
	}
}
