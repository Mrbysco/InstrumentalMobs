package com.mrbysco.instrumentalmobs;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class Reference {
	public static final String MOD_ID = "instrumentalmobs";
	public static final String MOD_PREFIX = MOD_ID + ":";

	public static DamageSource causeSoundDamage(Entity entity) {
		return new EntityDamageSource(Reference.MOD_PREFIX + "soundDamage", entity);
	}
}