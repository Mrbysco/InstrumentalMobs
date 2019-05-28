package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class InstrumentalLootTables {
	
	public static final ResourceLocation XYLOHPONE_SKELETAL_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/xylophone_skeleton");
	public static final ResourceLocation TUBA_ENDERMAN_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/tuba_enderman");
	public static final ResourceLocation FRENCH_HORN_CREEPER_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/french_horn_creeper");
	public static final ResourceLocation DRUM_ZOMBIE_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/drum_zombie");
	public static final ResourceLocation CYMBAL_HUSK_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/cymbal_husk");
	public static final ResourceLocation MARACA_SPIDER_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/maraca_spider");
	public static final ResourceLocation MICROPHONE_GHAST_LOOT = new ResourceLocation(Reference.MOD_ID, "entity/microphone_ghast");

	public static void registerLootTables()
	{
		LootTableList.register(XYLOHPONE_SKELETAL_LOOT);
		LootTableList.register(TUBA_ENDERMAN_LOOT);
		LootTableList.register(FRENCH_HORN_CREEPER_LOOT);
		LootTableList.register(DRUM_ZOMBIE_LOOT);
		LootTableList.register(CYMBAL_HUSK_LOOT);
		LootTableList.register(MARACA_SPIDER_LOOT);
		LootTableList.register(MICROPHONE_GHAST_LOOT);
	}
}