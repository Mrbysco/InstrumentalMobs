package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.init.InstrumentalDamageTypes;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class InstrumentalDamageTypeProvider {
	public static void bootstrap(BootstapContext<DamageType> context) {
		context.register(InstrumentalDamageTypes.SOUND, new DamageType("instrumentalmobs.sound", 0.1F));
	}
}
