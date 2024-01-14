package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

public class InstrumentalSoundProvider extends SoundDefinitionsProvider {

	public InstrumentalSoundProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, Constants.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(InstrumentalSounds.XYLOPHONE_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.XYLOPHONE_SOUND.getId()))
				.with(sound(modLoc("instruments/xylophone/xylophone"))));
		this.add(InstrumentalSounds.TUBA_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.TUBA_SOUND.getId()))
				.with(sound(modLoc("instruments/tuba/tuba"))));
		this.add(InstrumentalSounds.FRENCH_HORN_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.FRENCH_HORN_SOUND.getId()))
				.with(sound(modLoc("instruments/french_horn/frenchhorn"))));
		this.add(InstrumentalSounds.DRUM_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.DRUM_SOUND.getId()))
				.with(sound(modLoc("instruments/drum/drum"))));
		this.add(InstrumentalSounds.SINGLE_DRUM_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.SINGLE_DRUM_SOUND.getId()))
				.with(sound(modLoc("instruments/drum/singledrum"))));
		this.add(InstrumentalSounds.CYMBALS_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.CYMBALS_SOUND.getId()))
				.with(sound(modLoc("instruments/cymbals/cymbals"))));
		this.add(InstrumentalSounds.MARACA_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.MARACA_SOUND.getId()))
				.with(sound(modLoc("instruments/maraca/maraca"))));
		this.add(InstrumentalSounds.TRUMPET_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalSounds.TRUMPET_SOUND.getId()))
				.with(sound(modLoc("instruments/trumpet/trumpet"))));
	}


	public String modSubtitle(ResourceLocation id) {
		return Constants.MOD_ID + ".subtitle." + id.getPath();
	}

	public ResourceLocation modLoc(String name) {
		return new ResourceLocation(Constants.MOD_ID, name);
	}
}
