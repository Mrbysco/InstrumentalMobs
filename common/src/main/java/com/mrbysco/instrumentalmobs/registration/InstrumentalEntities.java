package com.mrbysco.instrumentalmobs.registration;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.entities.CymbalHusk;
import com.mrbysco.instrumentalmobs.entities.DrumZombie;
import com.mrbysco.instrumentalmobs.entities.FrenchHornCreeper;
import com.mrbysco.instrumentalmobs.entities.MaracaSpider;
import com.mrbysco.instrumentalmobs.entities.MicrophoneGhast;
import com.mrbysco.instrumentalmobs.entities.TrumpetSkeleton;
import com.mrbysco.instrumentalmobs.entities.TubaEnderman;
import com.mrbysco.instrumentalmobs.entities.XylophoneSkeleton;
import com.mrbysco.instrumentalmobs.entities.projectiles.MicrophoneWave;
import com.mrbysco.instrumentalmobs.entities.projectiles.SoundWaves;
import com.mrbysco.instrumentalmobs.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class InstrumentalEntities {
	public static final RegistrationProvider<EntityType<?>> ENTITIES = RegistrationProvider.get(BuiltInRegistries.ENTITY_TYPE, Constants.MOD_ID);

	public static final RegistryObject<EntityType<CymbalHusk>> CYMBAL_HUSK = ENTITIES.register("cymbal_husk",
			() -> EntityType.Builder.<CymbalHusk>of(CymbalHusk::new, MobCategory.MONSTER)
					.sized(0.6F, 1.95F).clientTrackingRange(8).build("cymbal_husk"));
	public static final RegistryObject<EntityType<DrumZombie>> DRUM_ZOMBIE = ENTITIES.register("drum_zombie",
			() -> EntityType.Builder.<DrumZombie>of(DrumZombie::new, MobCategory.MONSTER)
					.sized(0.6F, 1.95F).clientTrackingRange(8).build("drum_zombie"));
	public static final RegistryObject<EntityType<FrenchHornCreeper>> FRENCH_HORN_CREEPER = ENTITIES.register("french_horn_creeper",
			() -> EntityType.Builder.<FrenchHornCreeper>of(FrenchHornCreeper::new, MobCategory.MONSTER)
					.sized(0.6F, 1.7F).clientTrackingRange(8).build("french_horn_creeper"));
	public static final RegistryObject<EntityType<MaracaSpider>> MARACA_SPIDER = ENTITIES.register("maraca_spider",
			() -> EntityType.Builder.<MaracaSpider>of(MaracaSpider::new, MobCategory.MONSTER)
					.sized(1.4F, 0.9F).clientTrackingRange(8).build("maraca_spider"));
	public static final RegistryObject<EntityType<MicrophoneGhast>> MICROPHONE_GHAST = ENTITIES.register("microphone_ghast",
			() -> EntityType.Builder.<MicrophoneGhast>of(MicrophoneGhast::new, MobCategory.MONSTER)
					.sized(4.0F, 4.0F).fireImmune().clientTrackingRange(10).build("microphone_ghast"));
	public static final RegistryObject<EntityType<TubaEnderman>> TUBA_ENDERMAN = ENTITIES.register("tuba_enderman",
			() -> EntityType.Builder.<TubaEnderman>of(TubaEnderman::new, MobCategory.MONSTER)
					.sized(0.6F, 2.9F).clientTrackingRange(8).build("tuba_enderman"));
	public static final RegistryObject<EntityType<XylophoneSkeleton>> XYLOPHONE_SKELETON = ENTITIES.register("xylophone_skeleton",
			() -> EntityType.Builder.<XylophoneSkeleton>of(XylophoneSkeleton::new, MobCategory.MONSTER)
					.sized(0.6F, 1.99F).clientTrackingRange(8).build("xylophone_skeleton"));
	public static final RegistryObject<EntityType<TrumpetSkeleton>> TRUMPET_SKELETON = ENTITIES.register("trumpet_skeleton",
			() -> EntityType.Builder.<TrumpetSkeleton>of(TrumpetSkeleton::new, MobCategory.MONSTER)
					.sized(0.6F, 1.99F).clientTrackingRange(8).build("trumpet_skeleton"));
	public static final RegistryObject<EntityType<? extends MicrophoneWave>> MICROPHONE_WAVE = ENTITIES.register("microphone_sound",
			Services.PLATFORM::buildMicrophoneWave);
	public static final RegistryObject<EntityType<? extends SoundWaves>> SOUND_WAVE = ENTITIES.register("sound_waves",
			Services.PLATFORM::buildSoundWaves);

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
