package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class InstrumentalSoundProvider extends SoundDefinitionsProvider {

	public InstrumentalSoundProvider(PackOutput packOutput) {
		super(packOutput, Constants.MOD_ID);
	}

	@Override
	public void registerSounds() {
		this.add(InstrumentalSounds.XYLOPHONE_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.XYLOPHONE_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/xylophone/xylophone"))));
		this.add(InstrumentalSounds.TUBA_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.TUBA_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/tuba/tuba"))));
		this.add(InstrumentalSounds.FRENCH_HORN_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.FRENCH_HORN_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/french_horn/frenchhorn"))));
		this.add(InstrumentalSounds.DRUM_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.DRUM_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/drum/drum"))));
		this.add(InstrumentalSounds.SINGLE_DRUM_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.SINGLE_DRUM_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/drum/singledrum"))));
		this.add(InstrumentalSounds.CYMBALS_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.CYMBALS_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/cymbals/cymbals"))));
		this.add(InstrumentalSounds.MARACA_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.MARACA_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/maraca/maraca"))));
		this.add(InstrumentalSounds.TRUMPET_SOUND.asHolder(), definition()
				.subtitle(modSubtitle(InstrumentalSounds.TRUMPET_SOUND.getId()))
				.with(sound(Constants.modLoc("instruments/trumpet/trumpet"))));
	}


	public String modSubtitle(Identifier id) {
		return Constants.MOD_ID + ".subtitle." + id.getPath();
	}
}
