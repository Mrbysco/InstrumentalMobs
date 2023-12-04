package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.HeldBoneLayer;
import com.mrbysco.instrumentalmobs.client.render.model.XylophoneSkeletonModel;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeleton;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class XylophoneSkeletonRenderer extends CustomBipedRenderer<XylophoneSkeleton, XylophoneSkeletonModel<XylophoneSkeleton>> {
	private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");

	public XylophoneSkeletonRenderer(EntityRendererProvider.Context context) {
		super(context, new XylophoneSkeletonModel<>(context.bakeLayer(ModelLayers.SKELETON)), 0.5F);
		this.addLayer(new HeldBoneLayer<>(this, context.getItemInHandRenderer()));
	}

	public ResourceLocation getTextureLocation(XylophoneSkeleton xylophoneSkeleton) {
		return SKELETON_TEXTURES;
	}
}
