package com.mrbysco.instrumentalmobs.client.render.layers;

import com.mrbysco.instrumentalmobs.client.render.model.FrenchHornCreeperModel;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class FrenchHornCreeperCharge extends EnergyLayer<FrenchHornCreeperEntity, FrenchHornCreeperModel<FrenchHornCreeperEntity>> {
    private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
    private final FrenchHornCreeperModel<FrenchHornCreeperEntity> creeperModel = new FrenchHornCreeperModel<>(2.0F);

    public FrenchHornCreeperCharge(IEntityRenderer<FrenchHornCreeperEntity, FrenchHornCreeperModel<FrenchHornCreeperEntity>> p_i50947_1_) {
        super(p_i50947_1_);
    }

    protected float func_225634_a_(float p_225634_1_) {
        return p_225634_1_ * 0.01F;
    }

    protected ResourceLocation func_225633_a_() {
        return LIGHTNING_TEXTURE;
    }

    protected EntityModel<FrenchHornCreeperEntity> func_225635_b_() {
        return this.creeperModel;
    }
}
