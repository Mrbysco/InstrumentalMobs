package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.RegistryObject;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.CYMBAL_HUSK;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.DRUM_ZOMBIE;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.FRENCH_HORN_CREEPER;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.MARACA_SPIDER;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.MICROPHONE_GHAST;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.MICROPHONE_WAVE;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.SOUND_WAVE;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.TRUMPET_SKELETON;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.TUBA_ENDERMAN;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.XYLOPHONE_SKELETON;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry.DRUM_BLOCK;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry.DRUM_ITEM;

public class InstrumentalLoot extends LootTableProvider {
	public InstrumentalLoot(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, Set.of(), List.of(new SubProviderEntry(InstrumentalBlocks::new, LootContextParamSets.BLOCK),
						new SubProviderEntry(InstrumentalEntityLoot::new, LootContextParamSets.ENTITY)),
				lookupProvider);
	}

	private static class InstrumentalBlocks extends BlockLootSubProvider {

		protected InstrumentalBlocks(HolderLookup.Provider provider) {
			super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			this.dropOther(DRUM_BLOCK.get(), DRUM_ITEM.get());
		}

		@Override
		protected Iterable<Block> getKnownBlocks() {
			return (Iterable<Block>) InstrumentalRegistry.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
		}
	}

	private static class InstrumentalEntityLoot extends EntityLootSubProvider {
		protected InstrumentalEntityLoot(HolderLookup.Provider provider) {
			super(FeatureFlags.REGISTRY.allFlags(), provider);
		}

		@Override
		public void generate() {
			HolderGetter<EntityType<?>> holdergetter = this.registries.lookupOrThrow(Registries.ENTITY_TYPE);

			this.add(CYMBAL_HUSK.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.IRON_INGOT))
							.add(LootItem.lootTableItem(Items.CARROT))
							.add(LootItem.lootTableItem(Items.POTATO))
							.when(LootItemKilledByPlayerCondition.killedByPlayer())
							.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.025F, 0.01F))));
			this.add(DRUM_ZOMBIE.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.IRON_INGOT))
							.add(LootItem.lootTableItem(Items.CARROT))
							.add(LootItem.lootTableItem(Items.POTATO))
							.when(LootItemKilledByPlayerCondition.killedByPlayer())
							.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(this.registries, 0.025F, 0.01F))));
			this.add(FRENCH_HORN_CREEPER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.GUNPOWDER)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
							.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity()
									.of(holdergetter, EntityTypeTags.SKELETONS)))));
			this.add(MARACA_SPIDER.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.STRING)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.SPIDER_EYE)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
							.when(LootItemKilledByPlayerCondition.killedByPlayer())));
			this.add(MICROPHONE_GHAST.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.GHAST_TEAR)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.GUNPOWDER)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
			this.add(TUBA_ENDERMAN.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.ENDER_PEARL)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))))
			;
			this.add(XYLOPHONE_SKELETON.get(), LootTable.lootTable()

					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.ARROW)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.BONE)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));
			this.add(TRUMPET_SKELETON.get(), LootTable.lootTable()
					.withPool(LootPool.lootPool()
							.setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.ARROW)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
					.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
							.add(LootItem.lootTableItem(Items.BONE)
									.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
									.apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))));

			this.add(SOUND_WAVE.get(), LootTable.lootTable());
			this.add(MICROPHONE_WAVE.get(), LootTable.lootTable());
		}

		@Override
		protected Stream<EntityType<?>> getKnownEntityTypes() {
			return InstrumentalEntities.ENTITIES.getEntries().stream().map(RegistryObject::get);
		}
	}

	@Override
	protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
		super.validate(writableregistry, validationcontext, problemreporter$collector);
	}
}
