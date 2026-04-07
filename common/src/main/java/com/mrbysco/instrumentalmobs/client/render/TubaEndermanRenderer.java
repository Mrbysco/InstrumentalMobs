package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanHeldItemLayer;
import com.mrbysco.instrumentalmobs.client.render.model.TubaEndermanModel;
import com.mrbysco.instrumentalmobs.entities.TubaEnderman;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class TubaEndermanRenderer extends MobRenderer<TubaEnderman, EndermanRenderState, TubaEndermanModel<EndermanRenderState>> {
	public static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
	private static final Identifier ENDERMAN_TEXTURES = Identifier.withDefaultNamespace("textures/entity/enderman/enderman.png");
	private final Random rnd = new Random();
	private final BlockModelResolver blockModelResolver;

	public TubaEndermanRenderer(EntityRendererProvider.Context context) {
		super(context, new TubaEndermanModel<>(context.bakeLayer(ModelLayers.ENDERMAN)), 0.5F);
		this.blockModelResolver = context.getBlockModelResolver();
		this.addLayer(new TubaEndermanEyesLayer<>(this));
		this.addLayer(new TubaEndermanHeldItemLayer(this));
	}

	@NotNull
	@Override
	public Vec3 getRenderOffset(EndermanRenderState state) {
		if (state.isCreepy) {
			return new Vec3(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
		} else {
			return super.getRenderOffset(state);
		}
	}

	@NotNull
	@Override
	public EndermanRenderState createRenderState() {
		return new EndermanRenderState();
	}

	@Override
	public void extractRenderState(@NotNull TubaEnderman enderman, @NotNull EndermanRenderState state, float partialTicks) {
		super.extractRenderState(enderman, state, partialTicks);
		HumanoidMobRenderer.extractHumanoidRenderState(enderman, state, partialTicks, this.itemModelResolver);
		state.isCreepy = enderman.isCreepy();
		BlockState carriedBlock = enderman.getCarriedBlock();
		if (carriedBlock != null) {
			this.blockModelResolver.update(state.carriedBlock, carriedBlock, BLOCK_DISPLAY_CONTEXT);
		} else {
			state.carriedBlock.clear();
		}
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@NotNull
	@Override
	public Identifier getTextureLocation(@NotNull EndermanRenderState state) {
		return ENDERMAN_TEXTURES;
	}
}
