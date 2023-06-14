package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.blocks.DrumBlock;
import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import com.mrbysco.instrumentalmobs.entities.DrumZombieEntity;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeperEntity;
import com.mrbysco.instrumentalmobs.entities.MaracaSpiderEntity;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhastEntity;
import com.mrbysco.instrumentalmobs.entities.TubaEndermanEntity;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeletonEntity;
import com.mrbysco.instrumentalmobs.entities.projectiles.EntityMicrophoneWave;
import com.mrbysco.instrumentalmobs.entities.projectiles.EntitySoundWaves;
import com.mrbysco.instrumentalmobs.items.DrumInstrument;
import com.mrbysco.instrumentalmobs.items.InstrumentItem;
import com.mrbysco.instrumentalmobs.items.InstrumentMicrophone;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class InstrumentalRegistry {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MOD_ID);
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

	public static final RegistryObject<Block> DRUM_BLOCK = BLOCKS.register("drum_block", () -> new DrumBlock(Block.Properties.of().mapColor(MapColor.SNOW).ignitedByLava().strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
	public static final RegistryObject<Item> DRUM_BLOCK_ITEM = ITEMS.register("drum_block", () -> new BlockItem(DRUM_BLOCK.get(), (new Item.Properties())));
	public static final RegistryObject<Item> DRUM_ITEM = ITEMS.register("drum", () -> new DrumInstrument((new Item.Properties()).stacksTo(1).durability(140), () -> InstrumentalRegistry.DRUM_SOUND.get(), 30, 40));

	public static final RegistryObject<Item> CYMBAL = ITEMS.register("cymbal", () -> (new Item((new Item.Properties()).stacksTo(1))));
	public static final RegistryObject<Item> CYMBALS = ITEMS.register("cymbals", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(100), () -> InstrumentalRegistry.CYMBALS_SOUND.get(), 30, 40)));
	public static final RegistryObject<Item> FRENCH_HORN = ITEMS.register("french_horn", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(165), () -> InstrumentalRegistry.FRENCH_HORN_SOUND.get(), 40, 50)));
	public static final RegistryObject<Item> MARACA = ITEMS.register("maraca", () -> (new Item((new Item.Properties()).stacksTo(1))));
	public static final RegistryObject<Item> MARACAS = ITEMS.register("maracas", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(125), () -> InstrumentalRegistry.MARACA_SOUND.get(), 30, 20)));
	public static final RegistryObject<Item> MICROPHONE = ITEMS.register("microphone", () -> (new InstrumentMicrophone((new Item.Properties()).stacksTo(1).durability(110), () -> SoundEvents.GHAST_SCREAM, 40, 40)));
	public static final RegistryObject<Item> TUBA = ITEMS.register("tuba", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).stacksTo(180), () -> InstrumentalRegistry.TUBA_SOUND.get(), 20, 40)));
	public static final RegistryObject<Item> XYLOPHONE = ITEMS.register("xylophone", () -> (new InstrumentItem((new Item.Properties()).stacksTo(1).durability(160), () -> InstrumentalRegistry.XYLOPHONE_SOUND.get(), 30, 30)));

	public static final RegistryObject<Item> CYMBAL_HUSK_SPAWN_EGG = ITEMS.register("cymbal_husk_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.CYMBAL_HUSK.get(), 7958625, 15125652, (new Item.Properties())));
	public static final RegistryObject<Item> DRUM_ZOMBIE_SPAWN_EGG = ITEMS.register("drum_zombie_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.DRUM_ZOMBIE.get(), 44975, 7969893, (new Item.Properties())));
	public static final RegistryObject<Item> FRENCH_HORN_CREEPER_SPAWN_EGG = ITEMS.register("french_horn_creeper_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), 894731, 0, (new Item.Properties())));
	public static final RegistryObject<Item> MARACA_SPIDER_SPAWN_EGG = ITEMS.register("maraca_spider_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.MARACA_SPIDER.get(), 803406, 11013646, (new Item.Properties())));
	public static final RegistryObject<Item> MICROPHONE_GHAST_SPAWN_EGG = ITEMS.register("microphone_ghast_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.MICROPHONE_GHAST.get(), 16382457, 12369084, (new Item.Properties())));
	public static final RegistryObject<Item> TUBA_ENDERMAN_SPAWN_EGG = ITEMS.register("tuba_enderman_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.TUBA_ENDERMAN.get(), 1447446, 0, (new Item.Properties())));
	public static final RegistryObject<Item> XYLOPHONE_SKELETON_SPAWN_EGG = ITEMS.register("xylophone_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.XYLOPHONE_SKELETON.get(), 12698049, 4802889, (new Item.Properties())));
	public static final RegistryObject<CreativeModeTab> INSTRUMENTAL_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(Blocks.NOTE_BLOCK))
			.title(Component.translatable("itemGroup.instrumentalmobs"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = InstrumentalRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());

	public static final RegistryObject<EntityType<CymbalHuskEntity>> CYMBAL_HUSK = ENTITIES.register("cymbal_husk",
			() -> EntityType.Builder.<CymbalHuskEntity>of(CymbalHuskEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 1.95F).clientTrackingRange(8).build("cymbal_husk"));
	public static final RegistryObject<EntityType<DrumZombieEntity>> DRUM_ZOMBIE = ENTITIES.register("drum_zombie",
			() -> EntityType.Builder.<DrumZombieEntity>of(DrumZombieEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 1.95F).clientTrackingRange(8).build("drum_zombie"));
	public static final RegistryObject<EntityType<FrenchHornCreeperEntity>> FRENCH_HORN_CREEPER = ENTITIES.register("french_horn_creeper",
			() -> EntityType.Builder.<FrenchHornCreeperEntity>of(FrenchHornCreeperEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 1.7F).clientTrackingRange(8).build("french_horn_creeper"));
	public static final RegistryObject<EntityType<MaracaSpiderEntity>> MARACA_SPIDER = ENTITIES.register("maraca_spider",
			() -> EntityType.Builder.<MaracaSpiderEntity>of(MaracaSpiderEntity::new, MobCategory.MONSTER)
					.sized(1.4F, 0.9F).clientTrackingRange(8).build("maraca_spider"));
	public static final RegistryObject<EntityType<MicrophoneGhastEntity>> MICROPHONE_GHAST = ENTITIES.register("microphone_ghast",
			() -> EntityType.Builder.<MicrophoneGhastEntity>of(MicrophoneGhastEntity::new, MobCategory.MONSTER)
					.sized(4.0F, 4.0F).fireImmune().clientTrackingRange(10).build("microphone_ghast"));
	public static final RegistryObject<EntityType<TubaEndermanEntity>> TUBA_ENDERMAN = ENTITIES.register("tuba_enderman",
			() -> EntityType.Builder.<TubaEndermanEntity>of(TubaEndermanEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 2.9F).clientTrackingRange(8).build("tuba_enderman"));
	public static final RegistryObject<EntityType<XylophoneSkeletonEntity>> XYLOPHONE_SKELETON = ENTITIES.register("xylophone_skeleton",
			() -> EntityType.Builder.<XylophoneSkeletonEntity>of(XylophoneSkeletonEntity::new, MobCategory.MONSTER)
					.sized(0.6F, 1.99F).clientTrackingRange(8).build("xylophone_skeleton"));
	public static final RegistryObject<EntityType<EntityMicrophoneWave>> MICROPHONE_WAVE = ENTITIES.register("microphone_sound",
			() -> EntityType.Builder.<EntityMicrophoneWave>of(EntityMicrophoneWave::new, MobCategory.MISC)
					.sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
					.setCustomClientFactory(EntityMicrophoneWave::new).build("microphone_sound"));
	public static final RegistryObject<EntityType<EntitySoundWaves>> SOUND_WAVE = ENTITIES.register("sound_waves",
			() -> EntityType.Builder.<EntitySoundWaves>of(EntitySoundWaves::new, MobCategory.MISC)
					.sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
					.setCustomClientFactory(EntitySoundWaves::new).build("sound_waves"));
	public static final RegistryObject<SoundEvent> CYMBALS_SOUND = SOUND_EVENTS.register("cymbal.sound", () -> createSound("cymbal.sound"));
	public static final RegistryObject<SoundEvent> DRUM_SOUND = SOUND_EVENTS.register("drum.sound", () -> createSound("drum.sound"));
	public static final RegistryObject<SoundEvent> FRENCH_HORN_SOUND = SOUND_EVENTS.register("frenchhorn.sound", () -> createSound("frenchhorn.sound"));
	public static final RegistryObject<SoundEvent> MARACA_SOUND = SOUND_EVENTS.register("maraca.sound", () -> createSound("maraca.sound"));
	public static final RegistryObject<SoundEvent> SINGLE_DRUM_SOUND = SOUND_EVENTS.register("drum.single.sound", () -> createSound("drum.single.sound"));
	public static final RegistryObject<SoundEvent> TUBA_SOUND = SOUND_EVENTS.register("tuba.sound", () -> createSound("tuba.sound"));
	public static final RegistryObject<SoundEvent> XYLOPHONE_SOUND = SOUND_EVENTS.register("xylophone.sound", () -> createSound("xylophone.sound"));

	private static SoundEvent createSound(String name) {
		ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID, name);
		return SoundEvent.createVariableRangeEvent(resourceLocation);
	}
}