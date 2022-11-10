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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InstrumentalRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Reference.MOD_ID);
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);

    public static final RegistryObject<Block> DRUM_BLOCK = BLOCKS.register("drum_block", () -> new DrumBlock(Block.Properties.of(Material.WOOL, MaterialColor.SNOW).strength(0.8F).sound(SoundType.WOOL).noOcclusion()));
    public static final RegistryObject<Item> DRUM_BLOCK_ITEM = ITEMS.register("drum_block", () -> new BlockItem(DRUM_BLOCK.get(), itemBuilder()));
    public static final RegistryObject<Item> DRUM_ITEM = ITEMS.register("drum", () -> new DrumInstrument(itemBuilder().stacksTo(1).durability(140), () -> InstrumentalRegistry.drum_sound.get(), 30, 40));

    public static final RegistryObject<Item> cymbal = ITEMS.register("cymbal", () -> (new Item(itemBuilder().stacksTo(1))));
    public static final RegistryObject<Item> cymbals = ITEMS.register("cymbals", () -> (new InstrumentItem(itemBuilder().stacksTo(1).durability(100), () -> InstrumentalRegistry.cymbals_sound.get(), 30, 40)));
    public static final RegistryObject<Item> french_horn = ITEMS.register("french_horn", () -> (new InstrumentItem(itemBuilder().stacksTo(1).durability(165), () -> InstrumentalRegistry.french_horn_sound.get(), 40, 50)));
    public static final RegistryObject<Item> maraca = ITEMS.register("maraca", () -> (new Item(itemBuilder().stacksTo(1))));
    public static final RegistryObject<Item> maracas = ITEMS.register("maracas", () -> (new InstrumentItem(itemBuilder().stacksTo(1).durability(125), () -> InstrumentalRegistry.maraca_sound.get(), 30, 20)));
    public static final RegistryObject<Item> microphone = ITEMS.register("microphone", () -> (new InstrumentMicrophone(itemBuilder().stacksTo(1).durability(110), () -> SoundEvents.GHAST_SCREAM, 40, 40)));
    public static final RegistryObject<Item> tuba = ITEMS.register("tuba", () -> (new InstrumentItem(itemBuilder().stacksTo(1).stacksTo(180), () -> InstrumentalRegistry.tuba_sound.get(), 20, 40)));
    public static final RegistryObject<Item> xylophone = ITEMS.register("xylophone", () -> (new InstrumentItem(itemBuilder().stacksTo(1).durability(160), () -> InstrumentalRegistry.xylophone_sound.get(), 30, 30)));

    public static final RegistryObject<Item> CYMBAL_HUSK_SPAWN_EGG = ITEMS.register("cymbal_husk_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.CYMBAL_HUSK.get(), 7958625, 15125652, itemBuilder()));
    public static final RegistryObject<Item> DRUM_ZOMBIE_SPAWN_EGG = ITEMS.register("drum_zombie_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.DRUM_ZOMBIE.get(), 44975, 7969893, itemBuilder()));
    public static final RegistryObject<Item> FRENCH_HORN_CREEPER_SPAWN_EGG = ITEMS.register("french_horn_creeper_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), 894731, 0, itemBuilder()));
    public static final RegistryObject<Item> MARACA_SPIDER_SPAWN_EGG = ITEMS.register("maraca_spider_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.MARACA_SPIDER.get(), 803406, 11013646, itemBuilder()));
    public static final RegistryObject<Item> MICROPHONE_GHAST_SPAWN_EGG = ITEMS.register("microphone_ghast_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.MICROPHONE_GHAST.get(), 16382457, 12369084, itemBuilder()));
    public static final RegistryObject<Item> TUBA_ENDERMAN_SPAWN_EGG = ITEMS.register("tuba_enderman_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.TUBA_ENDERMAN.get(), 1447446, 0, itemBuilder()));
    public static final RegistryObject<Item> XYLOPHONE_SKELETON_SPAWN_EGG = ITEMS.register("xylophone_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(() -> InstrumentalRegistry.XYLOPHONE_SKELETON.get(), 12698049, 4802889, itemBuilder()));

    public static final RegistryObject<EntityType<CymbalHuskEntity>> CYMBAL_HUSK = ENTITIES.register("cymbal_husk",
        () -> register("cymbal_husk", EntityType.Builder.<CymbalHuskEntity>of(CymbalHuskEntity::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F).clientTrackingRange(8)));
    public static final RegistryObject<EntityType<DrumZombieEntity>> DRUM_ZOMBIE = ENTITIES.register("drum_zombie",
        () -> register("drum_zombie", EntityType.Builder.<DrumZombieEntity>of(DrumZombieEntity::new, MobCategory.MONSTER)
                .sized(0.6F, 1.95F).clientTrackingRange(8)));
    public static final RegistryObject<EntityType<FrenchHornCreeperEntity>> FRENCH_HORN_CREEPER = ENTITIES.register("french_horn_creeper",
        () -> register("french_horn_creeper", EntityType.Builder.<FrenchHornCreeperEntity>of(FrenchHornCreeperEntity::new, MobCategory.MONSTER)
                .sized(0.6F, 1.7F).clientTrackingRange(8)));
    public static final RegistryObject<EntityType<MaracaSpiderEntity>> MARACA_SPIDER = ENTITIES.register("maraca_spider",
        () -> register("maraca_spider", EntityType.Builder.<MaracaSpiderEntity>of(MaracaSpiderEntity::new, MobCategory.MONSTER)
                .sized(1.4F, 0.9F).clientTrackingRange(8)));
    public static final RegistryObject<EntityType<MicrophoneGhastEntity>> MICROPHONE_GHAST = ENTITIES.register("microphone_ghast",
        () -> register("microphone_ghast", EntityType.Builder.<MicrophoneGhastEntity>of(MicrophoneGhastEntity::new, MobCategory.MONSTER)
                .sized(4.0F, 4.0F).fireImmune().clientTrackingRange(10)));
    public static final RegistryObject<EntityType<TubaEndermanEntity>> TUBA_ENDERMAN = ENTITIES.register("tuba_enderman",
        () -> register("tuba_enderman", EntityType.Builder.<TubaEndermanEntity>of(TubaEndermanEntity::new, MobCategory.MONSTER)
                .sized(0.6F, 2.9F).clientTrackingRange(8)));
    public static final RegistryObject<EntityType<XylophoneSkeletonEntity>> XYLOPHONE_SKELETON = ENTITIES.register("xylophone_skeleton",
        () -> register("xylophone_skeleton", EntityType.Builder.<XylophoneSkeletonEntity>of(XylophoneSkeletonEntity::new, MobCategory.MONSTER)
                .sized(0.6F, 1.99F).clientTrackingRange(8)));
    public static final RegistryObject<EntityType<EntityMicrophoneWave>> MICROPHONE_WAVE = ENTITIES.register("microphone_sound", () ->
            register("microphone_sound", EntityType.Builder.<EntityMicrophoneWave>of(EntityMicrophoneWave::new, MobCategory.MISC)
                .sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
                .setCustomClientFactory(EntityMicrophoneWave::new)));
    public static final RegistryObject<EntityType<EntitySoundWaves>> SOUND_WAVE = ENTITIES.register("sound_waves", () ->
            register("sound_waves", EntityType.Builder.<EntitySoundWaves>of(EntitySoundWaves::new, MobCategory.MISC)
                .sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
                .setCustomClientFactory(EntitySoundWaves::new)));

    public static final RegistryObject<SoundEvent> cymbals_sound = SOUND_EVENTS.register("cymbal.sound", () -> createSound("cymbal.sound"));
    public static final RegistryObject<SoundEvent> drum_sound = SOUND_EVENTS.register("drum.sound", () -> createSound("drum.sound"));
    public static final RegistryObject<SoundEvent> french_horn_sound = SOUND_EVENTS.register("frenchhorn.sound", () -> createSound("frenchhorn.sound"));
    public static final RegistryObject<SoundEvent> maraca_sound = SOUND_EVENTS.register("maraca.sound", () -> createSound("maraca.sound"));
    public static final RegistryObject<SoundEvent> single_drum_sound = SOUND_EVENTS.register("drum.single.sound", () -> createSound("drum.single.sound"));
    public static final RegistryObject<SoundEvent> tuba_sound = SOUND_EVENTS.register("tuba.sound", () -> createSound("tuba.sound"));
    public static final RegistryObject<SoundEvent> xylophone_sound = SOUND_EVENTS.register("xylophone.sound", () -> createSound("xylophone.sound"));

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder, boolean sendVelocityUpdates) {
        return builder.setShouldReceiveVelocityUpdates(sendVelocityUpdates).build(id);
    }

    public static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> builder) {
        return register(id, builder, true);
    }

    private static SoundEvent createSound(String name) {
        ResourceLocation resourceLocation = new ResourceLocation(Reference.MOD_ID, name);
        return new SoundEvent(resourceLocation);
    }

    private static Item.Properties itemBuilder() {
        return new Item.Properties().tab(InstrumentalTab.INSTRUMENTAL_TAB);
    }
}