package com.mrbysco.instrumentalmobs.datagen;

import com.mrbysco.instrumentalmobs.registration.InstrumentalDamageTypes;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageType;

public class InstrumentalDamageTypeProvider {
	public static void bootstrap(BootstrapContext<DamageType> context) {
		context.register(InstrumentalDamageTypes.SOUND, new DamageType("instrumentalmobs.sound", 0.1F));
	}
}
