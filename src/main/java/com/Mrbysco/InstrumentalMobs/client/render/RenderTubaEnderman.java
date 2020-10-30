package com.mrbysco.instrumentalmobs.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanEyesLayer;
import com.mrbysco.instrumentalmobs.client.render.layers.TubaEndermanHeldItemLayer;
import com.mrbysco.instrumentalmobs.client.render.model.ModelTubaEnderman;
import com.mrbysco.instrumentalmobs.entities.TubaEndermanEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EndermanModel;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Random;

public class RenderTubaEnderman extends MobRenderer<TubaEndermanEntity, ModelTubaEnderman<TubaEndermanEntity>> {
	private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
	private final Random rnd = new Random();
	
	public RenderTubaEnderman(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelTubaEnderman(0.0F), 0.5F);
        this.addLayer(new TubaEndermanEyesLayer(this));
        this.addLayer(new TubaEndermanHeldItemLayer(this));
	}

    public void render(TubaEndermanEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        BlockState blockstate = entityIn.getHeldBlockState();
        EndermanModel<EndermanEntity> endermanmodel = (EndermanModel)this.getEntityModel();
        endermanmodel.isCarrying = blockstate != null;
        endermanmodel.isAttacking = entityIn.isScreaming();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public Vector3d getRenderOffset(TubaEndermanEntity entityIn, float partialTicks) {
        if (entityIn.isScreaming()) {
            double d0 = 0.02D;
            return new Vector3d(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(entityIn, partialTicks);
        }
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    public ResourceLocation getEntityTexture(TubaEndermanEntity entity)
    {
        return ENDERMAN_TEXTURES;
    }
}
