package com.mrbysco.instrumentalmobs.datagen.assets;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.sounds.SoundEvent;

public class InstrumentalLanguageProvider extends FabricLanguageProvider {

	public InstrumentalLanguageProvider(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder builder) {
		builder.add("instrumentalmobs.config.title", "Instrumental Mobs config");

		builder.add("itemGroup.instrumentalmobs", "Instrumental Mobs");

		builder.add(InstrumentalRegistry.XYLOPHONE.get(), "Xylophone");
		builder.add(InstrumentalRegistry.TUBA.get(), "Tuba");
		builder.add(InstrumentalRegistry.FRENCH_HORN.get(), "French Horn");
		builder.add(InstrumentalRegistry.DRUM_ITEM.get(), "Drum");
		builder.add(InstrumentalRegistry.CYMBAL.get(), "Cymbal");
		builder.add(InstrumentalRegistry.CYMBALS.get(), "Cymbals");
		builder.add(InstrumentalRegistry.MARACA.get(), "Maraca");
		builder.add(InstrumentalRegistry.MARACAS.get(), "Maracas");
		builder.add(InstrumentalRegistry.MICROPHONE.get(), "Microphone");
		builder.add(InstrumentalRegistry.TRUMPET.get(), "Trumpet");
		builder.add(InstrumentalRegistry.XYLOPHONE_SKELETON_SPAWN_EGG.get(), "Xylophone Skeleton Spawn Egg");
		builder.add(InstrumentalRegistry.TRUMPET_SKELETON_SPAWN_EGG.get(), "Trumpet Skeleton Spawn Egg");
		builder.add(InstrumentalRegistry.FRENCH_HORN_CREEPER_SPAWN_EGG.get(), "French Horn Creeper Spawn Egg");
		builder.add(InstrumentalRegistry.TUBA_ENDERMAN_SPAWN_EGG.get(), "Tuba Enderman Spawn Egg");
		builder.add(InstrumentalRegistry.DRUM_ZOMBIE_SPAWN_EGG.get(), "Drum Zombie Spawn Egg");
		builder.add(InstrumentalRegistry.CYMBAL_HUSK_SPAWN_EGG.get(), "Cymbal Husk Spawn Egg");
		builder.add(InstrumentalRegistry.MARACA_SPIDER_SPAWN_EGG.get(), "Maraca Spider Spawn Egg");
		builder.add(InstrumentalRegistry.MICROPHONE_GHAST_SPAWN_EGG.get(), "Microphone Ghast Spawn Egg");

		builder.add(InstrumentalRegistry.DRUM_BLOCK.get(), "Drum");

		builder.add(InstrumentalEntities.XYLOPHONE_SKELETON.get(), "Xylophone Skeleton");
		builder.add(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), "French Horn Creeper");
		builder.add(InstrumentalEntities.TUBA_ENDERMAN.get(), "Tuba Enderman");
		builder.add(InstrumentalEntities.DRUM_ZOMBIE.get(), "Drum Zombie");
		builder.add(InstrumentalEntities.CYMBAL_HUSK.get(), "Cymbal Husk");
		builder.add(InstrumentalEntities.MARACA_SPIDER.get(), "Maraca Spider");
		builder.add(InstrumentalEntities.MICROPHONE_GHAST.get(), "Microphone Ghast");
		builder.add(InstrumentalEntities.SOUND_WAVE.get(), "Soundwave");
		builder.add(InstrumentalEntities.MICROPHONE_WAVE.get(), "Soundwave");

		addSubtitle(builder, InstrumentalSounds.XYLOPHONE_SOUND.get(), "Skeleton plays their ribs like a xylophone");
		addSubtitle(builder, InstrumentalSounds.TUBA_SOUND.get(), "Tuba Plays");
		addSubtitle(builder, InstrumentalSounds.FRENCH_HORN_SOUND.get(), "French Horn Plays");
		addSubtitle(builder, InstrumentalSounds.DRUM_SOUND.get(), "Drum sounds");
		addSubtitle(builder, InstrumentalSounds.CYMBALS_SOUND.get(), "A Cymbal Crash sounds");
		addSubtitle(builder, InstrumentalSounds.MARACA_SOUND.get(), "Maraca sounds");
		addSubtitle(builder, InstrumentalSounds.TRUMPET_SOUND.get(), "Doot Doot");

		builder.add("death.attack.instrumentalmobs.sound", "%1$s was deafened");
		builder.add("death.attack.instrumentalmobs.sound.player", "%1$s was deafened trying to escape %2$s");

		addAdvancement(builder, "root", "Instrumental Mobs", "Getting instrumental with mobs");
		addAdvancement(builder, "cymbals", "Cymbals", "*CRASH* What did you say? *CRASH* I can't hear you due to the *CRASH* noise...");
		addAdvancement(builder, "drum", "Drum", "You've heard of the Little Drummer Boy, well now you've met my Drummer Zombie(s) *Badumnn Tsss*");
		addAdvancement(builder, "french_horn", "French Horn", "I see you met my French Horn creeper(s)? Had fun??");
		addAdvancement(builder, "maracas", "Maracas", "*tssch* La Cucaracha *tssch*");
		addAdvancement(builder, "microphone", "Microphone", "Just scream...");
		addAdvancement(builder, "tuba", "Tuba", "Teleporting musicians... not fair *tuba noise*");
		addAdvancement(builder, "xylophone", "Xylophone", "Did you enjoy the skeletons?");
		addAdvancement(builder, "trumpet", "Trumpet", "Doot doot!");

		builder.add("text.autoconfig.instrumentalmobs.title", "Instrumental Mobs");
		builder.add("text.autoconfig.instrumentalmobs.option.general", "General");
		builder.add("text.autoconfig.instrumentalmobs.option.general.mobsReact", "Mobs React");
		builder.add("text.autoconfig.instrumentalmobs.option.general.instrumentRange", "Instrument Range");
		builder.add("text.autoconfig.instrumentalmobs.option.general.soundDamageChance", "Sound Damage Chance");
		builder.add("text.autoconfig.instrumentalmobs.option.general.instrumentDropChance", "Instrument Drop Chance");
		builder.add("text.autoconfig.instrumentalmobs.option.general.instrumentHurtChance", "Instrument Hurt Chance");
	}

	public void addSubtitle(TranslationBuilder builder, SoundEvent sound, String name) {
		String path = Constants.MOD_ID + ".subtitle." + sound.getLocation().getPath();
		builder.add(path, name);
	}

	/**
	 * Add the translation of an advancement
	 *
	 * @param id          The advancement id
	 * @param name        The name of the advancement
	 * @param description The description of the advancement
	 */
	private void addAdvancement(TranslationBuilder builder, String id, String name, String description) {
		String prefix = "advancement.instrumentalmobs.";
		builder.add(prefix + id + ".title", name);
		builder.add(prefix + id + ".desc", description);
	}
}
