package com.mrbysco.instrumentalmobs.platform;

import com.mrbysco.instrumentalmobs.InstrumentalMobsFabric;
import com.mrbysco.instrumentalmobs.entities.projectiles.MicrophoneWave;
import com.mrbysco.instrumentalmobs.entities.projectiles.SoundWaves;
import com.mrbysco.instrumentalmobs.init.SupplierSpawnEggItem;
import com.mrbysco.instrumentalmobs.platform.services.IPlatformHelper;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.RegistryObject;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class FabricPlatformHelper implements IPlatformHelper {

	@Override
	public CreativeModeTab buildCreativeTab() {
		return new FabricItemGroupBuilderImpl()
				.icon(() -> new ItemStack(Blocks.NOTE_BLOCK))
				.title(Component.translatable("itemGroup.instrumentalmobs"))
				.displayItems((displayParameters, output) -> {
					List<ItemStack> stacks = InstrumentalRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
					output.acceptAll(stacks);
				}).build();
	}

	@Override
	public <T extends Mob> SpawnEggItem buildSpawnEgg(RegistryObject<EntityType<T>> type, int backgroundColor, int highlightColor, Item.Properties props) {
		return new SupplierSpawnEggItem(type, backgroundColor, highlightColor, props);
	}

	@Override
	public EntityType<? extends MicrophoneWave> buildMicrophoneWave() {
		return EntityType.Builder.<MicrophoneWave>of(MicrophoneWave::new, MobCategory.MISC)
				.sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
				.build("microphone_sound");
	}

	@Override
	public EntityType<? extends SoundWaves> buildSoundWaves() {
		return EntityType.Builder.<SoundWaves>of(SoundWaves::new, MobCategory.MISC)
				.sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
				.build("sound_waves");
	}

	@Override
	public Level.ExplosionInteraction getExplosionInteraction(Entity entity) {
		return Level.ExplosionInteraction.MOB;
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan enderMan) {
		return stack.getItem() == Blocks.CARVED_PUMPKIN.asItem();
	}

	@Override
	public boolean mobsReact() {
		return InstrumentalMobsFabric.config.get().general.mobsReact;
	}

	@Override
	public double instrumentRange() {
		return InstrumentalMobsFabric.config.get().general.instrumentRange;
	}

	@Override
	public double soundDamageChance() {
		return InstrumentalMobsFabric.config.get().general.soundDamageChance;
	}

	@Override
	public double instrumentDropChance() {
		return InstrumentalMobsFabric.config.get().general.instrumentDropChance;
	}

	@Override
	public double instrumentHurtChance() {
		return InstrumentalMobsFabric.config.get().general.instrumentHurtChance;
	}
}
