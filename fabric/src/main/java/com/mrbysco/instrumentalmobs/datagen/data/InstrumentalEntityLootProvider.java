package com.mrbysco.instrumentalmobs.datagen.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
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

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.CYMBAL_HUSK;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.DRUM_ZOMBIE;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.FRENCH_HORN_CREEPER;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.MARACA_SPIDER;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.MICROPHONE_GHAST;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.TRUMPET_SKELETON;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.TUBA_ENDERMAN;
import static com.mrbysco.instrumentalmobs.registration.InstrumentalEntities.XYLOPHONE_SKELETON;

public class InstrumentalEntityLootProvider extends SimpleFabricLootTableProvider {
	private final CompletableFuture<HolderLookup.Provider> registries;

	public InstrumentalEntityLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup, LootContextParamSets.ENTITY);
		this.registries = registryLookup;
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
		HolderLookup.Provider registryLookup = this.registries.join();
		HolderGetter<EntityType<?>> holdergetter = registryLookup.lookupOrThrow(Registries.ENTITY_TYPE);
		biConsumer.accept(CYMBAL_HUSK.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.IRON_INGOT))
						.add(LootItem.lootTableItem(Items.CARROT))
						.add(LootItem.lootTableItem(Items.POTATO))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registryLookup, 0.025F, 0.01F))));
		biConsumer.accept(DRUM_ZOMBIE.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.IRON_INGOT))
						.add(LootItem.lootTableItem(Items.CARROT))
						.add(LootItem.lootTableItem(Items.POTATO))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())
						.when(LootItemRandomChanceWithEnchantedBonusCondition.randomChanceAndLootingBoost(registryLookup, 0.025F, 0.01F))));
		biConsumer.accept(FRENCH_HORN_CREEPER.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.GUNPOWDER)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().add(TagEntry.expandTag(ItemTags.CREEPER_DROP_MUSIC_DISCS))
						.when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.ATTACKER, EntityPredicate.Builder.entity()
								.of(holdergetter, EntityTypeTags.SKELETONS)))));
		biConsumer.accept(MARACA_SPIDER.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.STRING)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.SPIDER_EYE)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(-1.0F, 1.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F))))
						.when(LootItemKilledByPlayerCondition.killedByPlayer())));
		biConsumer.accept(MICROPHONE_GHAST.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.GHAST_TEAR)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.GUNPOWDER)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F))))));
		biConsumer.accept(TUBA_ENDERMAN.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.ENDER_PEARL)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F))))));
		biConsumer.accept(XYLOPHONE_SKELETON.get().getDefaultLootTable().get(), LootTable.lootTable()

				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.ARROW)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.BONE)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F))))));
		biConsumer.accept(TRUMPET_SKELETON.get().getDefaultLootTable().get(), LootTable.lootTable()
				.withPool(LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.ARROW)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
						.add(LootItem.lootTableItem(Items.BONE)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
								.apply(EnchantedCountIncreaseFunction.lootingMultiplier(registryLookup, UniformGenerator.between(0.0F, 1.0F))))));
	}
}
