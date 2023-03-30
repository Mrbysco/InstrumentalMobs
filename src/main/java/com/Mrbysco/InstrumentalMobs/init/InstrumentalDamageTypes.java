package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class InstrumentalDamageTypes {
	public static final ResourceKey<DamageType> SOUND = register("sound");

	private static ResourceKey<DamageType> register(String name) {
		return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(Reference.MOD_ID, name));
	}
}
