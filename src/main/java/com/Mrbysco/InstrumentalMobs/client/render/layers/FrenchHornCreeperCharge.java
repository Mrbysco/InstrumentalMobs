package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class FrenchHornCreeperCharge extends EnergySwirlLayer<FrenchHornCreeperEntity, FrenchHornCreeperModel<FrenchHornCreeperEntity>> {
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final FrenchHornCreeperModel<FrenchHornCreeperEntity> creeperModel;

    public FrenchHornCreeperCharge(RenderLayerParent<FrenchHornCreeperEntity, FrenchHornCreeperModel<FrenchHornCreeperEntity>> layerParent, EntityModelSet modelSet) {
        super(layerParent);
        this.creeperModel = new FrenchHornCreeperModel<>(modelSet.bakeLayer(ModelLayers.CREEPER_ARMOR));
    }

    protected float xOffset(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    protected ResourceLocation getTextureLocation() {
        return LIGHTNING_TEXTURE;
    }

    protected EntityModel<FrenchHornCreeperEntity> model() {
        return this.creeperModel;
    }
}
