package com.mrbysco.instrumentalmobs.datagen.data;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.RegistryObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class InstrumentalAdvancementProvider extends AdvancementProvider {

	public InstrumentalAdvancementProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
		super(packOutput, completableFuture, List.of(new InstrumentalAdvancements()));
	}

	public static class InstrumentalAdvancements implements AdvancementSubProvider {
		public InstrumentalAdvancements() {
		}

		@Override
		public void generate(HolderLookup.Provider provider, Consumer<Advancement> consumer) {
			//Root advancement
			Advancement root = Advancement.Builder.advancement()
					.display(rootDisplay(Items.NOTE_BLOCK, advancementPrefix("root" + ".title"),
							advancementPrefix("root" + ".desc"), new ResourceLocation("textures/block/yellow_wool.png")))
					.addCriterion("french_horn_creeper", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.FRENCH_HORN_CREEPER.get())
					))
					.addCriterion("tuba_enderman", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.TUBA_ENDERMAN.get())
					))
					.addCriterion("drum_zombie", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.DRUM_ZOMBIE.get())
					))
					.addCriterion("cymbal_husk", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.CYMBAL_HUSK.get())
					))
					.addCriterion("xylophone_skeleton", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.XYLOPHONE_SKELETON.get())
					))
					.addCriterion("maraca_spider", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.MARACA_SPIDER.get())
					))
					.addCriterion("microphone_ghast", KilledTrigger.TriggerInstance.playerKilledEntity(
							EntityPredicate.Builder.entity().of(InstrumentalEntities.MICROPHONE_GHAST.get())
					))
					.requirements(RequirementsStrategy.OR)
					.save(consumer, rootID("root"));

			//Generate an advancement for every instrument
			addInstrumentAdvancement(consumer, InstrumentalRegistry.CYMBALS, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.DRUM_ITEM, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.FRENCH_HORN, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.MARACAS, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.MICROPHONE, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.TUBA, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.XYLOPHONE, root);
			addInstrumentAdvancement(consumer, InstrumentalRegistry.TRUMPET, root);
		}

		/**
		 * Generate an advancement for picking up an instrument
		 *
		 * @param consumer       The consumer to add the advancement to
		 * @param registryObject The registry object of the instrument
		 * @param root           The root advancement
		 */
		public static void addInstrumentAdvancement(Consumer<Advancement> consumer, RegistryObject<Item> registryObject, Advancement root) {
			ResourceLocation registryLocation = registryObject.getId();
			Item item = registryObject.get();
			if (registryLocation != null) {
				Advancement advancement = Advancement.Builder.advancement()
						.display(simpleDisplay(item, registryLocation.getPath()))
						.parent(root)
						.addCriterion("instrument", InventoryChangeTrigger.TriggerInstance.hasItems(item))
						.save(consumer, rootID(registryLocation.getPath()));
			}
		}

		/**
		 * Generate a root DisplayInfo object.
		 *
		 * @param icon       The icon to use.
		 * @param titleKey   The title key.
		 * @param descKey    The description key.
		 * @param background The background texture.
		 * @return The DisplayInfo object.
		 */
		protected static DisplayInfo rootDisplay(ItemLike icon, String titleKey, String descKey, ResourceLocation background) {
			return new DisplayInfo(new ItemStack(icon.asItem()),
					Component.translatable(titleKey),
					Component.translatable(descKey),
					background, FrameType.TASK, true, true, false);
		}

		/**
		 * Generate a simple DisplayInfo object.
		 *
		 * @param icon The icon to use.
		 * @param name The name of the advancement.
		 * @return The DisplayInfo object.
		 */
		protected static DisplayInfo simpleDisplay(ItemLike icon, String name) {
			return new DisplayInfo(new ItemStack(icon.asItem()),
					Component.translatable(advancementPrefix(name + ".title")),
					Component.translatable(advancementPrefix(name + ".desc")),
					null, FrameType.TASK, true, false, false);
		}

		/**
		 * Get a trigger instance for killing an entity.
		 *
		 * @param entityType The entity type.
		 * @return The trigger instance.
		 */
		protected static KilledTrigger.TriggerInstance onKill(EntityType<?> entityType) {
			return KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityType));
		}

		/**
		 * Generate a ResourceLocation that has the mod ID as the namespace.
		 *
		 * @param path The path.
		 * @return The ResourceLocation.
		 */
		private static ResourceLocation modLoc(String path) {
			return new ResourceLocation(Constants.MOD_ID, path);
		}

		/**
		 * Generate an advancement prefix.
		 *
		 * @param name The name of the advancement.
		 * @return The prefix.
		 */
		private static String advancementPrefix(String name) {
			return "advancement." + Constants.MOD_ID + "." + name;
		}

		/**
		 * Generate a root advancement ID.
		 *
		 * @param name The name of the advancement.
		 * @return The advancement ID.
		 */
		private static String rootID(String name) {
			return modLoc("main/" + name).toString();
		}
	}
}
