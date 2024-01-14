package com.mrbysco.instrumentalmobs.registration;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.blocks.DrumBlock;
import com.mrbysco.instrumentalmobs.items.DrumInstrument;
import com.mrbysco.instrumentalmobs.items.InstrumentItem;
import com.mrbysco.instrumentalmobs.items.InstrumentMicrophone;
import com.mrbysco.instrumentalmobs.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class InstrumentalRegistry {

	public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, Constants.MOD_ID);
	public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, Constants.MOD_ID);
	public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

	public static final RegistryObject<Block> DRUM_BLOCK = BLOCKS.register("drum_block", () -> new DrumBlock(Block.Properties.of().mapColor(MapColor.SNOW).ignitedByLava().strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
	public static final RegistryObject<Item> DRUM_BLOCK_ITEM = ITEMS.register("drum_block", () -> new BlockItem(DRUM_BLOCK.get(), (new Item.Properties())));
	public static final RegistryObject<Item> DRUM_ITEM = ITEMS.register("drum", () -> new DrumInstrument((new Item.Properties()).stacksTo(1).durability(140), InstrumentalSounds.DRUM_SOUND, 30, 40));

	public static final RegistryObject<Item> CYMBAL = ITEMS.register("cymbal", () -> (new Item((new Item.Properties()).stacksTo(1))));
	public static final RegistryObject<Item> CYMBALS = ITEMS.register("cymbals", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(100), InstrumentalSounds.CYMBALS_SOUND, 30, 40)));
	public static final RegistryObject<Item> FRENCH_HORN = ITEMS.register("french_horn", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(165), InstrumentalSounds.FRENCH_HORN_SOUND, 40, 50)));
	public static final RegistryObject<Item> MARACA = ITEMS.register("maraca", () -> (new Item((new Item.Properties()).stacksTo(1))));
	public static final RegistryObject<Item> MARACAS = ITEMS.register("maracas", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(125), InstrumentalSounds.MARACA_SOUND, 30, 20)));
	public static final RegistryObject<Item> MICROPHONE = ITEMS.register("microphone", () -> (new InstrumentMicrophone((new Item.Properties()).stacksTo(1).durability(110), () -> SoundEvents.GHAST_SCREAM, 40, 40)));
	public static final RegistryObject<Item> TUBA = ITEMS.register("tuba", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).stacksTo(180), InstrumentalSounds.TUBA_SOUND, 20, 40)));
	public static final RegistryObject<Item> XYLOPHONE = ITEMS.register("xylophone", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(160), InstrumentalSounds.XYLOPHONE_SOUND, 30, 30)));
	public static final RegistryObject<Item> TRUMPET = ITEMS.register("trumpet", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(160), InstrumentalSounds.TRUMPET_SOUND, 30, 30)));

	public static final RegistryObject<Item> CYMBAL_HUSK_SPAWN_EGG = ITEMS.register("cymbal_husk_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.CYMBAL_HUSK, 7958625, 15125652, (new Item.Properties())));
	public static final RegistryObject<Item> DRUM_ZOMBIE_SPAWN_EGG = ITEMS.register("drum_zombie_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.DRUM_ZOMBIE, 44975, 7969893, (new Item.Properties())));
	public static final RegistryObject<Item> FRENCH_HORN_CREEPER_SPAWN_EGG = ITEMS.register("french_horn_creeper_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.FRENCH_HORN_CREEPER, 894731, 0, (new Item.Properties())));
	public static final RegistryObject<Item> MARACA_SPIDER_SPAWN_EGG = ITEMS.register("maraca_spider_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.MARACA_SPIDER, 803406, 11013646, (new Item.Properties())));
	public static final RegistryObject<Item> MICROPHONE_GHAST_SPAWN_EGG = ITEMS.register("microphone_ghast_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.MICROPHONE_GHAST, 16382457, 12369084, (new Item.Properties())));
	public static final RegistryObject<Item> TUBA_ENDERMAN_SPAWN_EGG = ITEMS.register("tuba_enderman_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.TUBA_ENDERMAN, 1447446, 0, (new Item.Properties())));
	public static final RegistryObject<Item> XYLOPHONE_SKELETON_SPAWN_EGG = ITEMS.register("xylophone_skeleton_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.XYLOPHONE_SKELETON, 12698049, 4802889, (new Item.Properties())));
	public static final RegistryObject<Item> TRUMPET_SKELETON_SPAWN_EGG = ITEMS.register("trumpet_skeleton_spawn_egg", () -> Services.PLATFORM.buildSpawnEgg(InstrumentalEntities.TRUMPET_SKELETON, 12698049, 4802889, (new Item.Properties())));

	public static final RegistryObject<CreativeModeTab> INSTRUMENTAL_TAB = CREATIVE_MODE_TABS.register("tab", () -> Services.PLATFORM.buildCreativeTab());


	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
