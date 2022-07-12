package com.mrbysco.instrumentalmobs.datagen;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.JsonOps;
import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.modifier.AddRelativeSpawnBiomeModifier;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.data.loot.EntityLoot;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.CYMBAL_HUSK;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.DRUM_BLOCK;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.DRUM_ITEM;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.DRUM_ZOMBIE;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.FRENCH_HORN_CREEPER;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.MARACA_SPIDER;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.MICROPHONE_GHAST;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.TUBA_ENDERMAN;
import static com.mrbysco.instrumentalmobs.init.InstrumentalRegistry.XYLOPHONE_SKELETON;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class InstrumentalDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		final RegistryOps<JsonElement> ops = RegistryOps.create(JsonOps.INSTANCE, RegistryAccess.builtinCopy());
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();


		Map<ResourceLocation, BiomeModifier> biomeModifiers = new LinkedHashMap<>();
		biomeModifiers.putAll(addModifierForType(EntityType.HUSK, InstrumentalRegistry.CYMBAL_HUSK.get(), 5));
		biomeModifiers.putAll(addModifierForType(EntityType.ZOMBIE, InstrumentalRegistry.DRUM_ZOMBIE.get(), 5));
		biomeModifiers.putAll(addModifierForType(EntityType.CREEPER, InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), 5));
		biomeModifiers.putAll(addModifierForType(EntityType.SPIDER, InstrumentalRegistry.MARACA_SPIDER.get(), 5));
		biomeModifiers.putAll(addModifierForType(EntityType.GHAST, InstrumentalRegistry.MICROPHONE_GHAST.get(), 5));
		biomeModifiers.putAll(addModifierForType(EntityType.ENDERMAN, InstrumentalRegistry.TUBA_ENDERMAN.get(), 5));
		biomeModifiers.putAll(addModifierForType(EntityType.SKELETON, InstrumentalRegistry.XYLOPHONE_SKELETON.get(), 5));

		generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(
				generator, helper, Reference.MOD_ID, ops, ForgeRegistries.Keys.BIOME_MODIFIERS, biomeModifiers
		));

		generator.addProvider(event.includeServer(), new InstrumentalLoot(generator));
	}

	private static Map<ResourceLocation, BiomeModifier> addModifierForType(EntityType<?> originalType, EntityType<?> newType, int relativeWeight) {
		return Map.of(ForgeRegistries.ENTITY_TYPES.getKey(originalType), new AddRelativeSpawnBiomeModifier(
				originalType, newType, relativeWeight));
	}

	private static class InstrumentalLoot extends LootTableProvider {
		public InstrumentalLoot(DataGenerator gen) {
			super(gen);
		}

		@Override
		protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
			return ImmutableList.of(Pair.of(InstrumentalBlocks::new, LootContextParamSets.BLOCK), Pair.of(InstrumentalEntityLoot::new, LootContextParamSets.ENTITY));
		}

		private static class InstrumentalBlocks extends BlockLoot {

			@Override
			protected void addTables() {
				this.dropOther(DRUM_BLOCK.get(), DRUM_ITEM.get());
			}

			@Override
			protected Iterable<Block> getKnownBlocks() {
				return (Iterable<Block>) InstrumentalRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
			}
		}

		private static class InstrumentalEntityLoot extends EntityLoot {

			@Override
			protected void addTables() {
				this.add(CYMBAL_HUSK.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_INGOT)).add(LootItem.lootTableItem(Items.CARROT)).add(LootItem.lootTableItem(Items.POTATO)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))));
				this.add(DRUM_ZOMBIE.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.ROTTEN_FLESH).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_INGOT)).add(LootItem.lootTableItem(Items.CARROT)).add(LootItem.lootTableItem(Items.POTATO)).when(LootItemKilledByPlayerCondition.killedByPlayer()).when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.025F, 0.01F))));
				this.add(FRENCH_HORN_CREEPER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)).when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.KILLER, EntityPredicate.Builder.entity().of(EntityTypeTags.SKELETONS)))));
				this.add(MARACA_SPIDER.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.STRING).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.SPIDER_EYE).apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))).when(LootItemKilledByPlayerCondition.killedByPlayer())));
				this.add(MICROPHONE_GHAST.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.GHAST_TEAR).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.GUNPOWDER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
				this.add(TUBA_ENDERMAN.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.ENDER_PEARL).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
				this.add(XYLOPHONE_SKELETON.get(), LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.ARROW).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))).withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))).apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));
			}

			@Override
			protected Iterable<EntityType<?>> getKnownEntities() {
				Stream<EntityType<?>> entityTypeStream = InstrumentalRegistry.ENTITIES.getEntries().stream().map(RegistryObject::get);
				return (Iterable<EntityType<?>>) entityTypeStream::iterator;
			}
		}

		@Override
		protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
			map.forEach((name, table) -> LootTables.validate(validationtracker, name, table));
		}
	}
}
