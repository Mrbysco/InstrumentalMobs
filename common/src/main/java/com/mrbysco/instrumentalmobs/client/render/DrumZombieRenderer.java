package com.mrbysco.instrumentalmobs.client.render;

import com.mrbysco.instrumentalmobs.client.render.layers.DrumLayer;
import com.mrbysco.instrumentalmobs.client.render.state.DrumRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemDisplayContext;

public class DrumZombieRenderer extends ZombieRenderer {
	public DrumZombieRenderer(EntityRendererProvider.Context context) {
		super(context);
		this.addLayer(new DrumLayer(this));
	}

	@Override
	public ZombieRenderState createRenderState() {
		return new DrumRenderState();
	}

	@Override
	public void extractRenderState(Zombie zombie, ZombieRenderState state, float partialTick) {
		super.extractRenderState(zombie, state, partialTick);
		if (state instanceof DrumRenderState drumRenderState) {
			itemModelResolver.updateForLiving(
					drumRenderState.chestEquipment, zombie.getItemBySlot(EquipmentSlot.CHEST), ItemDisplayContext.NONE, false, zombie
			);
		}
	}
}
