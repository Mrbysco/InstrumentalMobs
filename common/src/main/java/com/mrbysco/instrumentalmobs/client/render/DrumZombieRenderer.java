package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.DrumLayer;
import com.mrbysco.instrumentalmobs.client.render.state.DrumRenderState;
import com.mrbysco.instrumentalmobs.entities.DrumZombie;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class DrumZombieRenderer extends AbstractZombieRenderer<DrumZombie, DrumRenderState, ZombieModel<DrumRenderState>> {
	public DrumZombieRenderer(EntityRendererProvider.Context context) {
		this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_BABY, ModelLayers.ZOMBIE_ARMOR, ModelLayers.ZOMBIE_BABY_ARMOR);
		this.addLayer(new DrumLayer(this));
	}

	public DrumZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation zombie, ModelLayerLocation baby,
	                          ArmorModelSet<ModelLayerLocation> armor, ArmorModelSet<ModelLayerLocation> armorBaby) {
		super(context,
				new ZombieModel<>(context.bakeLayer(zombie)),
				new ZombieModel<>(context.bakeLayer(baby)),
				ArmorModelSet.bake(armor, context.getModelSet(), ZombieModel::new),
				ArmorModelSet.bake(armorBaby, context.getModelSet(), ZombieModel::new)
		);
	}

	@NotNull
	@Override
	public DrumRenderState createRenderState() {
		return new DrumRenderState();
	}

	@Override
	public void extractRenderState(@NotNull DrumZombie zombie, @NotNull DrumRenderState state, float partialTick) {
		super.extractRenderState(zombie, state, partialTick);
		itemModelResolver.updateForLiving(
				state.chestEquipment, zombie.getItemBySlot(EquipmentSlot.CHEST), ItemDisplayContext.NONE, zombie
		);
	}
}
