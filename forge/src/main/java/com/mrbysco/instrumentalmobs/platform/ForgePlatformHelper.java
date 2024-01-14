package com.mrbysco.instrumentalmobs.platform;

import com.mrbysco.instrumentalmobs.config.InstrumentalConfigForge;
import com.mrbysco.instrumentalmobs.entities.projectiles.MicrophoneWave;
import com.mrbysco.instrumentalmobs.entities.projectiles.MicrophoneWaveForge;
import com.mrbysco.instrumentalmobs.entities.projectiles.SoundWaves;
import com.mrbysco.instrumentalmobs.entities.projectiles.SoundWavesForge;
import com.mrbysco.instrumentalmobs.platform.services.IPlatformHelper;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.RegistryObject;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.List;

public class ForgePlatformHelper implements IPlatformHelper {


	@Override
	public CreativeModeTab buildCreativeTab() {
		return CreativeModeTab.builder()
				.icon(() -> new ItemStack(Blocks.NOTE_BLOCK))
				.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
				.title(Component.translatable("itemGroup.instrumentalmobs"))
				.displayItems((displayParameters, output) -> {
					List<ItemStack> stacks = InstrumentalRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
					output.acceptAll(stacks);
				}).build();
	}

	@Override
	public <T extends Mob> SpawnEggItem buildSpawnEgg(RegistryObject<EntityType<T>> type, int backgroundColor, int highlightColor, Item.Properties props) {
		return new ForgeSpawnEggItem(type, backgroundColor, highlightColor, props);
	}

	@Override
	public EntityType<? extends MicrophoneWave> buildMicrophoneWave() {
		return EntityType.Builder.<MicrophoneWaveForge>of(MicrophoneWaveForge::new, MobCategory.MISC)
				.sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
				.setCustomClientFactory(MicrophoneWaveForge::new).build("microphone_sound");
	}

	@Override
	public EntityType<? extends SoundWaves> buildSoundWaves() {
		return EntityType.Builder.<SoundWavesForge>of(SoundWavesForge::new, MobCategory.MISC)
				.sized(0.3125F, 0.3125F).clientTrackingRange(4).clientTrackingRange(10)
				.setCustomClientFactory(SoundWavesForge::new).build("sound_waves");
	}

	@Override
	public Level.ExplosionInteraction getExplosionInteraction(Entity entity) {
		return net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(entity.level(), entity) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan enderMan) {
		return stack.isEnderMask(player, enderMan);
	}

	@Override
	public boolean mobsReact() {
		return InstrumentalConfigForge.COMMON.mobsReact.get();
	}

	@Override
	public double instrumentRange() {
		return InstrumentalConfigForge.COMMON.instrumentRange.get();
	}

	@Override
	public double soundDamageChance() {
		return InstrumentalConfigForge.COMMON.soundDamageChance.get();
	}

	@Override
	public double instrumentDropChance() {
		return InstrumentalConfigForge.COMMON.instrumentDropChance.get();
	}

	@Override
	public double instrumentHurtChance() {
		return InstrumentalConfigForge.COMMON.instrumentHurtChance.get();
	}
}
