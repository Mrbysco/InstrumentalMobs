package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public class InstrumentalLanguageProvider extends LanguageProvider {

	public InstrumentalLanguageProvider(PackOutput packOutput) {
		super(packOutput, Reference.MOD_ID, "en_us");
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
		addItem(InstrumentalRegistry.XYLOPHONE_SKELETON_SPAWN_EGG, "Xylophone Skeleton Spawn Egg");
		addItem(InstrumentalRegistry.TRUMPET_SKELETON_SPAWN_EGG, "Trumpet Skeleton Spawn Egg");
		addItem(InstrumentalRegistry.FRENCH_HORN_CREEPER_SPAWN_EGG, "French Horn Creeper Spawn Egg");
		addItem(InstrumentalRegistry.TUBA_ENDERMAN_SPAWN_EGG, "Tuba Enderman Spawn Egg");
		addItem(InstrumentalRegistry.DRUM_ZOMBIE_SPAWN_EGG, "Drum Zombie Spawn Egg");
		addItem(InstrumentalRegistry.CYMBAL_HUSK_SPAWN_EGG, "Cymbal Husk Spawn Egg");
		addItem(InstrumentalRegistry.MARACA_SPIDER_SPAWN_EGG, "Maraca Spider Spawn Egg");
		addItem(InstrumentalRegistry.MICROPHONE_GHAST_SPAWN_EGG, "Microphone Ghast Spawn Egg");

		addBlock(InstrumentalRegistry.DRUM_BLOCK, "Drum");

		addEntityType(InstrumentalRegistry.XYLOPHONE_SKELETON, "Xylophone Skeleton");
		addEntityType(InstrumentalRegistry.FRENCH_HORN_CREEPER, "French Horn Creeper");
		addEntityType(InstrumentalRegistry.TUBA_ENDERMAN, "Tuba Enderman");
		addEntityType(InstrumentalRegistry.DRUM_ZOMBIE, "Drum Zombie");
		addEntityType(InstrumentalRegistry.CYMBAL_HUSK, "Cymbal Husk");
		addEntityType(InstrumentalRegistry.MARACA_SPIDER, "Maraca Spider");
		addEntityType(InstrumentalRegistry.MICROPHONE_GHAST, "Microphone Ghast");
		addEntityType(InstrumentalRegistry.SOUND_WAVE, "Soundwave");
		addEntityType(InstrumentalRegistry.MICROPHONE_WAVE, "Soundwave");

		addSubtitle(InstrumentalRegistry.XYLOPHONE_SOUND, "Skeleton plays their ribs like a xylophone");
		addSubtitle(InstrumentalRegistry.TUBA_SOUND, "Tuba Plays");
		addSubtitle(InstrumentalRegistry.FRENCH_HORN_SOUND, "French Horn Plays");
		addSubtitle(InstrumentalRegistry.DRUM_SOUND, "Drum sounds");
		addSubtitle(InstrumentalRegistry.CYMBALS_SOUND, "A Cymbal Crash sounds");
		addSubtitle(InstrumentalRegistry.MARACA_SOUND, "Maraca sounds");
		addSubtitle(InstrumentalRegistry.TRUMPET_SOUND, "Doot Doot");

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

	public void addSubtitle(RegistryObject<SoundEvent> sound, String name) {
		this.addSubtitle(sound.get(), name);
	}

	public void addSubtitle(SoundEvent sound, String name) {
		String path = Reference.MOD_ID + ".subtitle." + sound.getLocation().getPath();
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
