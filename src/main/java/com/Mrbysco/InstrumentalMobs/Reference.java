package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.init.InstrumentalDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public class Reference {
	public static final String MOD_ID = "instrumentalmobs";
	public static final String MOD_PREFIX = MOD_ID + ":";

	public static DamageSource causeSoundDamage(Entity entity) {
		return entity.damageSources().source(InstrumentalDamageTypes.SOUND, entity);
	}
}