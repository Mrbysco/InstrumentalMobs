package com.mrbysco.instrumentalmobs.registration;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.blocks.DrumBlock;
import com.mrbysco.instrumentalmobs.items.DrumInstrument;
import com.mrbysco.instrumentalmobs.items.InstrumentItem;
import com.mrbysco.instrumentalmobs.items.InstrumentMicrophone;
import com.mrbysco.instrumentalmobs.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class InstrumentalRegistry {

	public static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(BuiltInRegistries.BLOCK, Constants.MOD_ID);
	public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(BuiltInRegistries.ITEM, Constants.MOD_ID);
	public static final RegistrationProvider<CreativeModeTab> CREATIVE_MODE_TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

	public static final RegistryObject<Block> DRUM_BLOCK = BLOCKS.register("drum_block", () -> new DrumBlock(Block.Properties.of().setId(getBlockKey("drum_block")).mapColor(MapColor.SNOW).ignitedByLava().strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
	public static final RegistryObject<Item> DRUM_BLOCK_ITEM = ITEMS.register("drum_block", () -> new BlockItem(DRUM_BLOCK.get(), (new Item.Properties().setId(getKey("drum_block")).useBlockDescriptionPrefix())));
	public static final RegistryObject<Item> DRUM_ITEM = ITEMS.register("drum", () -> new DrumInstrument((new Item.Properties()).setId(getKey("drum")).stacksTo(1).durability(140), InstrumentalSounds.DRUM_SOUND, 30, 40));

	public static final RegistryObject<Item> CYMBAL = ITEMS.register("cymbal", () -> (new Item((new Item.Properties()).setId(getKey("cymbal")).stacksTo(1))));
	public static final RegistryObject<Item> CYMBALS = ITEMS.register("cymbals", () -> (new InstrumentItem((new Item.Properties()).setId(getKey("cymbals")).stacksTo(1).durability(100), InstrumentalSounds.CYMBALS_SOUND, 30, 40)));
	public static final RegistryObject<Item> FRENCH_HORN = ITEMS.register("french_horn", () -> (new InstrumentItem((new Item.Properties()).setId(getKey("french_horn")).stacksTo(1).durability(165), InstrumentalSounds.FRENCH_HORN_SOUND, 40, 50)));
	public static final RegistryObject<Item> MARACA = ITEMS.register("maraca", () -> (new Item((new Item.Properties()).setId(getKey("maraca")).stacksTo(1))));
	public static final RegistryObject<Item> MARACAS = ITEMS.register("maracas", () -> (new InstrumentItem((new Item.Properties()).setId(getKey("maracas")).stacksTo(1).durability(125), InstrumentalSounds.MARACA_SOUND, 30, 20)));
	public static final RegistryObject<Item> MICROPHONE = ITEMS.register("microphone", () -> (new InstrumentMicrophone((new Item.Properties()).setId(getKey("microphone")).stacksTo(1).durability(110), () -> SoundEvents.GHAST_SCREAM, 40, 40)));
	public static final RegistryObject<Item> TUBA = ITEMS.register("tuba", () -> (new InstrumentItem((new Item.Properties()).setId(getKey("tuba")).stacksTo(1).stacksTo(180), InstrumentalSounds.TUBA_SOUND, 20, 40)));
	public static final RegistryObject<Item> XYLOPHONE = ITEMS.register("xylophone", () -> (new InstrumentItem((new Item.Properties()).setId(getKey("xylophone")).stacksTo(1).durability(160), InstrumentalSounds.XYLOPHONE_SOUND, 30, 30)));
	public static final RegistryObject<Item> TRUMPET = ITEMS.register("trumpet", () -> (new InstrumentItem((new Item.Properties()).setId(getKey("trumpet")).stacksTo(1).durability(160), InstrumentalSounds.TRUMPET_SOUND, 30, 30)));

	public static final RegistryObject<Item> CYMBAL_HUSK_SPAWN_EGG = ITEMS.register("cymbal_husk_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.CYMBAL_HUSK.get(), (new Item.Properties().setId(getKey("cymbal_husk_spawn_egg")))));
	public static final RegistryObject<Item> DRUM_ZOMBIE_SPAWN_EGG = ITEMS.register("drum_zombie_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.DRUM_ZOMBIE.get(), (new Item.Properties().setId(getKey("drum_zombie_spawn_egg")))));
	public static final RegistryObject<Item> FRENCH_HORN_CREEPER_SPAWN_EGG = ITEMS.register("french_horn_creeper_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), (new Item.Properties().setId(getKey("french_horn_creeper_spawn_egg")))));
	public static final RegistryObject<Item> MARACA_SPIDER_SPAWN_EGG = ITEMS.register("maraca_spider_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.MARACA_SPIDER.get(), (new Item.Properties().setId(getKey("maraca_spider_spawn_egg")))));
	public static final RegistryObject<Item> MICROPHONE_GHAST_SPAWN_EGG = ITEMS.register("microphone_ghast_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.MICROPHONE_GHAST.get(), (new Item.Properties().setId(getKey("microphone_ghast_spawn_egg")))));
	public static final RegistryObject<Item> TUBA_ENDERMAN_SPAWN_EGG = ITEMS.register("tuba_enderman_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.TUBA_ENDERMAN.get(), (new Item.Properties().setId(getKey("tuba_enderman_spawn_egg")))));
	public static final RegistryObject<Item> XYLOPHONE_SKELETON_SPAWN_EGG = ITEMS.register("xylophone_skeleton_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.XYLOPHONE_SKELETON.get(), (new Item.Properties().setId(getKey("xylophone_skeleton_spawn_egg")))));
	public static final RegistryObject<Item> TRUMPET_SKELETON_SPAWN_EGG = ITEMS.register("trumpet_skeleton_spawn_egg", () -> new SpawnEggItem(InstrumentalEntities.TRUMPET_SKELETON.get(), (new Item.Properties().setId(getKey("trumpet_skeleton_spawn_egg")))));

	private static ResourceKey<Item> getKey(String name) {
		return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
	}

	private static ResourceKey<Block> getBlockKey(String name) {
		return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
	}

	public static final RegistryObject<CreativeModeTab> INSTRUMENTAL_TAB = CREATIVE_MODE_TABS.register("tab", () -> Services.PLATFORM.buildCreativeTab());


	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
