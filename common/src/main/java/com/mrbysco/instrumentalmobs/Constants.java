package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.registration.InstrumentalDamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "instrumentalmobs";
	public static final String MOD_NAME = "Instrumental Mobs";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static DamageSource causeSoundDamage(Entity entity) {
		return entity.damageSources().source(InstrumentalDamageTypes.SOUND, entity);
	}
}