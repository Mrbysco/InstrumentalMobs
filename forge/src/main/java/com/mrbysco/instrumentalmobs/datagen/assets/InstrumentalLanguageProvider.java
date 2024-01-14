package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;

public class InstrumentalLanguageProvider extends LanguageProvider {

	public InstrumentalLanguageProvider(PackOutput packOutput) {
		super(packOutput, Constants.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add("instrumentalmobs.config.title", "Instrumental Mobs config");

		add("itemGroup.instrumentalmobs", "Instrumental Mobs");

		addItem(InstrumentalRegistry.XYLOPHONE, "Xylophone");
		addItem(InstrumentalRegistry.TUBA, "Tuba");
		addItem(InstrumentalRegistry.FRENCH_HORN, "French Horn");
		addItem(InstrumentalRegistry.DRUM_ITEM, "Drum");
		addItem(InstrumentalRegistry.CYMBAL, "Cymbal");
		addItem(InstrumentalRegistry.CYMBALS, "Cymbals");
		addItem(InstrumentalRegistry.MARACA, "Maraca");
		addItem(InstrumentalRegistry.MARACAS, "Maracas");
		addItem(InstrumentalRegistry.MICROPHONE, "Microphone");
		addItem(InstrumentalRegistry.TRUMPET, "Trumpet");
		addItem(InstrumentalRegistry.XYLOPHONE_SKELETON_SPAWN_EGG, "Xylophone Skeleton Spawn Egg");
		addItem(InstrumentalRegistry.TRUMPET_SKELETON_SPAWN_EGG, "Trumpet Skeleton Spawn Egg");
		addItem(InstrumentalRegistry.FRENCH_HORN_CREEPER_SPAWN_EGG, "French Horn Creeper Spawn Egg");
		addItem(InstrumentalRegistry.TUBA_ENDERMAN_SPAWN_EGG, "Tuba Enderman Spawn Egg");
		addItem(InstrumentalRegistry.DRUM_ZOMBIE_SPAWN_EGG, "Drum Zombie Spawn Egg");
		addItem(InstrumentalRegistry.CYMBAL_HUSK_SPAWN_EGG, "Cymbal Husk Spawn Egg");
		addItem(InstrumentalRegistry.MARACA_SPIDER_SPAWN_EGG, "Maraca Spider Spawn Egg");
		addItem(InstrumentalRegistry.MICROPHONE_GHAST_SPAWN_EGG, "Microphone Ghast Spawn Egg");

		addBlock(InstrumentalRegistry.DRUM_BLOCK, "Drum");

		addEntityType(InstrumentalEntities.XYLOPHONE_SKELETON, "Xylophone Skeleton");
		addEntityType(InstrumentalEntities.FRENCH_HORN_CREEPER, "French Horn Creeper");
		addEntityType(InstrumentalEntities.TUBA_ENDERMAN, "Tuba Enderman");
		addEntityType(InstrumentalEntities.DRUM_ZOMBIE, "Drum Zombie");
		addEntityType(InstrumentalEntities.CYMBAL_HUSK, "Cymbal Husk");
		addEntityType(InstrumentalEntities.MARACA_SPIDER, "Maraca Spider");
		addEntityType(InstrumentalEntities.MICROPHONE_GHAST, "Microphone Ghast");
		addEntityType(InstrumentalEntities.SOUND_WAVE, "Soundwave");
		addEntityType(InstrumentalEntities.MICROPHONE_WAVE, "Soundwave");

		addSubtitle(InstrumentalSounds.XYLOPHONE_SOUND.get(), "Skeleton plays their ribs like a xylophone");
		addSubtitle(InstrumentalSounds.TUBA_SOUND.get(), "Tuba Plays");
		addSubtitle(InstrumentalSounds.FRENCH_HORN_SOUND.get(), "French Horn Plays");
		addSubtitle(InstrumentalSounds.DRUM_SOUND.get(), "Drum sounds");
		addSubtitle(InstrumentalSounds.CYMBALS_SOUND.get(), "A Cymbal Crash sounds");
		addSubtitle(InstrumentalSounds.MARACA_SOUND.get(), "Maraca sounds");
		addSubtitle(InstrumentalSounds.TRUMPET_SOUND.get(), "Doot Doot");

		add("death.attack.instrumentalmobs.sound", "%1$s was deafened");
		add("death.attack.instrumentalmobs.sound.player", "%1$s was deafened trying to escape %2$s");

		addAdvancement("root", "Instrumental Mobs", "Getting instrumental with mobs");
		addAdvancement("cymbals", "Cymbals", "*CRASH* What did you say? *CRASH* I can't hear you due to the *CRASH* noise...");
		addAdvancement("drum", "Drum", "You've heard of the Little Drummer Boy, well now you've met my Drummer Zombie(s) *Badumnn Tsss*");
		addAdvancement("french_horn", "French Horn", "I see you met my French Horn creeper(s)? Had fun??");
		addAdvancement("maracas", "Maracas", "*tssch* La Cucaracha *tssch*");
		addAdvancement("microphone", "Microphone", "Just scream...");
		addAdvancement("tuba", "Tuba", "Teleporting musicians... not fair *tuba noise*");
		addAdvancement("xylophone", "Xylophone", "Did you enjoy the skeletons?");
		addAdvancement("trumpet", "Trumpet", "Doot doot!");
	}

	public void addSubtitle(SoundEvent sound, String name) {
		String path = Constants.MOD_ID + ".subtitle." + sound.getLocation().getPath();
		this.add(path, name);
	}

	/**
	 * Add the translation of an advancement
	 *
	 * @param id          The advancement id
	 * @param name        The name of the advancement
	 * @param description The description of the advancement
	 */
	private void addAdvancement(String id, String name, String description) {
		String prefix = "advancement.instrumentalmobs.";
		add(prefix + id + ".title", name);
		add(prefix + id + ".desc", description);
	}
}
