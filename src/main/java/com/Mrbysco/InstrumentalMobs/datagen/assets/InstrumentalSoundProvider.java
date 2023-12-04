package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class InstrumentalSoundProvider extends SoundDefinitionsProvider {

	public InstrumentalSoundProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
		super(packOutput, Reference.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		this.add(InstrumentalRegistry.XYLOPHONE_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.XYLOPHONE_SOUND.getId()))
				.with(sound(modLoc("instruments/xylophone/xylophone"))));
		this.add(InstrumentalRegistry.TUBA_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.TUBA_SOUND.getId()))
				.with(sound(modLoc("instruments/tuba/tuba"))));
		this.add(InstrumentalRegistry.FRENCH_HORN_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.FRENCH_HORN_SOUND.getId()))
				.with(sound(modLoc("instruments/french_horn/frenchhorn"))));
		this.add(InstrumentalRegistry.DRUM_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.DRUM_SOUND.getId()))
				.with(sound(modLoc("instruments/drum/drum"))));
		this.add(InstrumentalRegistry.SINGLE_DRUM_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.SINGLE_DRUM_SOUND.getId()))
				.with(sound(modLoc("instruments/drum/singledrum"))));
		this.add(InstrumentalRegistry.CYMBALS_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.CYMBALS_SOUND.getId()))
				.with(sound(modLoc("instruments/cymbals/cymbals"))));
		this.add(InstrumentalRegistry.MARACA_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.MARACA_SOUND.getId()))
				.with(sound(modLoc("instruments/maraca/maraca"))));
		this.add(InstrumentalRegistry.TRUMPET_SOUND, definition()
				.subtitle(modSubtitle(InstrumentalRegistry.TRUMPET_SOUND.getId()))
				.with(sound(modLoc("instruments/trumpet/trumpet"))));
	}


	public String modSubtitle(ResourceLocation id) {
		return Reference.MOD_ID + ".subtitle." + id.getPath();
	}

	public ResourceLocation modLoc(String name) {
		return new ResourceLocation(Reference.MOD_ID, name);
	}
}
