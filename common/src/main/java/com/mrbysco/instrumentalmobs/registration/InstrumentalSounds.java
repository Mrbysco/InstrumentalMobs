package com.mrbysco.instrumentalmobs.registration;

import com.mrbysco.instrumentalmobs.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class InstrumentalSounds {

	public static final RegistrationProvider<SoundEvent> SOUND_EVENTS = RegistrationProvider.get(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);

	public static final RegistryObject<SoundEvent> CYMBALS_SOUND = registerSound("cymbal.sound");
	public static final RegistryObject<SoundEvent> DRUM_SOUND = registerSound("drum.sound");
	public static final RegistryObject<SoundEvent> FRENCH_HORN_SOUND = registerSound("frenchhorn.sound");
	public static final RegistryObject<SoundEvent> MARACA_SOUND = registerSound("maraca.sound");
	public static final RegistryObject<SoundEvent> SINGLE_DRUM_SOUND = registerSound("drum.single.sound");
	public static final RegistryObject<SoundEvent> TUBA_SOUND = registerSound("tuba.sound");
	public static final RegistryObject<SoundEvent> XYLOPHONE_SOUND = registerSound("xylophone.sound");
	public static final RegistryObject<SoundEvent> TRUMPET_SOUND = registerSound("trumpet.sound");

	private static RegistryObject<SoundEvent> registerSound(String name) {
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MOD_ID, name)));
	}

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
