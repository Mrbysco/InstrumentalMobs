package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class InstrumentalSounds {
	public static SoundEvent xylophone_sound;
	public static SoundEvent tuba_sound;
	public static SoundEvent french_horn_sound;
	public static SoundEvent drum_sound;
	public static SoundEvent single_drum_sound;
	public static SoundEvent cymbals_sound;
	public static SoundEvent maraca_sound;
	
	public static void registerSounds() 
	{
		xylophone_sound = registerSound("xylophone.sound");
		tuba_sound = registerSound("tuba.sound");
		french_horn_sound = registerSound("frenchhorn.sound");
		drum_sound = registerSound("drum.sound");
		single_drum_sound = registerSound("drum.single.sound");
		cymbals_sound = registerSound("cymbal.sound");
		maraca_sound = registerSound("maraca.sound");
	}
	
	private static SoundEvent registerSound(String soundName)
	{
		ResourceLocation location = new ResourceLocation(Reference.MOD_ID, soundName);
		SoundEvent event = new SoundEvent(location);
		event.setRegistryName(location);
		ForgeRegistries.SOUND_EVENTS.register(event);
		return event;
	}
}
