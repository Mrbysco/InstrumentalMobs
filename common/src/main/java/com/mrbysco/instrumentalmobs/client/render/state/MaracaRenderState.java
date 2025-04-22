package com.mrbysco.instrumentalmobs.client.render.state;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;

public class MaracaRenderState extends LivingEntityRenderState {
	public ItemStackRenderState mainItem;
	public ItemStackRenderState offItem;
	public boolean isAttacking;
}
