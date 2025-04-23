package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.DrumLayer;
import com.mrbysco.instrumentalmobs.client.render.state.DrumRenderState;
import com.mrbysco.instrumentalmobs.entities.DrumZombie;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class DrumZombieRenderer extends AbstractZombieRenderer<DrumZombie, DrumRenderState, ZombieModel<DrumRenderState>> {
	public DrumZombieRenderer(EntityRendererProvider.Context context) {
		this(context, ModelLayers.ZOMBIE, ModelLayers.ZOMBIE_BABY, ModelLayers.ZOMBIE_INNER_ARMOR, ModelLayers.ZOMBIE_OUTER_ARMOR, ModelLayers.ZOMBIE_BABY_INNER_ARMOR, ModelLayers.ZOMBIE_BABY_OUTER_ARMOR);
		this.addLayer(new DrumLayer<>(this));
	}

	public DrumZombieRenderer(EntityRendererProvider.Context context, ModelLayerLocation zombie, ModelLayerLocation baby,
	                          ModelLayerLocation innerArmor, ModelLayerLocation outerArmor,
	                          ModelLayerLocation innerBaby, ModelLayerLocation outerBaby) {
		super(context,
				new ZombieModel<>(context.bakeLayer(zombie)),
				new ZombieModel<>(context.bakeLayer(baby)),
				new ZombieModel<>(context.bakeLayer(innerArmor)),
				new ZombieModel<>(context.bakeLayer(outerArmor)),
				new ZombieModel<>(context.bakeLayer(innerBaby)),
				new ZombieModel<>(context.bakeLayer(outerBaby)));
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
				state.chestEquipment, zombie.getItemBySlot(EquipmentSlot.CHEST), ItemDisplayContext.NONE, false, zombie
		);
	}
}
