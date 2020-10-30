package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.HeldBoneLayer;
import com.mrbysco.instrumentalmobs.client.render.model.ModelXylophoneSkeleton;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeletonEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class RenderXylophoneSkeleton extends RenderCustomBiped<XylophoneSkeletonEntity, ModelXylophoneSkeleton<XylophoneSkeletonEntity>> {
    private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public RenderXylophoneSkeleton(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelXylophoneSkeleton(), 0.5F);
        this.addLayer(new HeldBoneLayer(this));
	}
	
	public ResourceLocation getEntityTexture(XylophoneSkeletonEntity entity)
    {
        return SKELETON_TEXTURES;
    }
}
