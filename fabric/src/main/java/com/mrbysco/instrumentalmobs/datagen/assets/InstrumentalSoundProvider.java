/*
 * Copyright (c) Forge Development LLC and contributors
 * SPDX-License-Identifier: LGPL-2.1-only
 */

package com.mrbysco.instrumentalmobs.datagen.assets;

import com.google.gson.JsonObject;
import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class InstrumentalSoundProvider implements DataProvider {
	private static final Logger LOGGER = LogManager.getLogger();

	private final Map<String, SoundDefinition> sounds = new LinkedHashMap<>();

	protected final FabricDataOutput dataOutput;

	public InstrumentalSoundProvider(FabricDataOutput dataOutput) {
		this.dataOutput = dataOutput;
	}

	/**
	 * Registers the sound definitions that should be generated via one of the {@code add} methods.
	 */
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

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		this.sounds.clear();
		this.registerSounds();
		if (!this.sounds.isEmpty()) {
			return this.save(cache, this.dataOutput.getOutputFolder(PackOutput.Target.RESOURCE_PACK).resolve(this.dataOutput.getModId()).resolve("sounds.json"));
		}

		return CompletableFuture.allOf();
	}

	@Override
	public String getName() {
		return "Sound Definitions";
	}

	// Quick helpers

	/**
	 * Creates a new {@link SoundDefinition}, which will host a set of
	 * {@link SoundDefinition.Sound}s and the necessary parameters.
	 */
	protected static SoundDefinition definition() {
		return SoundDefinition.definition();
	}

	/**
	 * Creates a new sound with the given name and type.
	 *
	 * @param name The name of the sound to create.
	 * @param type The type of sound to create.
	 */
	protected static SoundDefinition.Sound sound(final ResourceLocation name, final SoundDefinition.SoundType type) {
		return SoundDefinition.Sound.sound(name, type);
	}

	/**
	 * Creates a new sound with the given name and {@link SoundDefinition.SoundType#SOUND} as
	 * sound type.
	 *
	 * @param name The name of the sound to create.
	 */
	protected static SoundDefinition.Sound sound(final ResourceLocation name) {
		return sound(name, SoundDefinition.SoundType.SOUND);
	}

	/**
	 * Creates a new sound with the given name and type.
	 *
	 * @param name The name of the sound to create.
	 * @param type The type of sound to create.
	 */
	protected static SoundDefinition.Sound sound(final String name, final SoundDefinition.SoundType type) {
		return sound(new ResourceLocation(name), type);
	}

	/**
	 * Creates a new sound with the given name and {@link SoundDefinition.SoundType#SOUND} as
	 * sound type.
	 *
	 * @param name The name of the sound to create.
	 */
	protected static SoundDefinition.Sound sound(final String name) {
		return sound(new ResourceLocation(name));
	}

	// Addition methods

	/**
	 * Adds the entry name associated with the supplied {@link SoundEvent} with the given
	 * {@link SoundDefinition} to the list.
	 *
	 * <p>This method should be preferred when dealing with a {@code RegistryObject} or
	 * {@code RegistryDelegate}.</p>
	 *
	 * @param soundEvent A {@code Supplier} for the given {@link SoundEvent}.
	 * @param definition A {@link SoundDefinition} that defines the given sound.
	 */
	protected void add(final Supplier<SoundEvent> soundEvent, final SoundDefinition definition) {
		this.add(soundEvent.get(), definition);
	}

	/**
	 * Adds the entry name associated with the given {@link SoundEvent} with the
	 * {@link SoundDefinition} to the list.
	 *
	 * <p>This method should be preferred when a {@code SoundEvent} is already
	 * available in the method context. If you already have a {@code Supplier} for
	 * it, refer to {@link #add(Supplier, SoundDefinition)}.</p>
	 *
	 * @param soundEvent A {@link SoundEvent}.
	 * @param definition The {@link SoundDefinition} that defines the given event.
	 */
	protected void add(final SoundEvent soundEvent, final SoundDefinition definition) {
		this.add(soundEvent.getLocation(), definition);
	}

	/**
	 * Adds the {@link SoundEvent} referenced by the given {@link ResourceLocation} with the
	 * {@link SoundDefinition} to the list.
	 *
	 * @param soundEvent The {@link ResourceLocation} that identifies the event.
	 * @param definition The {@link SoundDefinition} that defines the given event.
	 */
	protected void add(final ResourceLocation soundEvent, final SoundDefinition definition) {
		this.addSounds(soundEvent.getPath(), definition);
	}

	/**
	 * Adds the {@link SoundEvent} with the specified name along with its {@link SoundDefinition}
	 * to the list.
	 *
	 * <p>The given sound event must NOT contain the namespace the name is a part of, since
	 * the sound definition specification doesn't allow sounds to be defined outside the
	 * namespace they're in. For this reason, any namespace will automatically be stripped
	 * from the name.</p>
	 *
	 * @param soundEvent The name of the {@link SoundEvent}.
	 * @param definition The {@link SoundDefinition} that defines the given event.
	 */
	protected void add(final String soundEvent, final SoundDefinition definition) {
		this.add(new ResourceLocation(soundEvent), definition);
	}

	private void addSounds(final String soundEvent, final SoundDefinition definition) {
		if (this.sounds.put(soundEvent, definition) != null) {
			throw new IllegalStateException("Sound event '" + this.dataOutput.getModId() + ":" + soundEvent + "' already exists");
		}
	}

	private CompletableFuture<?> save(final CachedOutput cache, final Path targetFile) {
		return DataProvider.saveStable(cache, this.mapToJson(this.sounds), targetFile);
	}

	private JsonObject mapToJson(final Map<String, SoundDefinition> map) {
		final JsonObject obj = new JsonObject();
		// namespaces are ignored when serializing
		map.forEach((k, v) -> obj.add(k, v.serialize()));
		return obj;
	}
}
