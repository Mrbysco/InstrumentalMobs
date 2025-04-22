package com.mrbysco.instrumentalmobs.platform;

import com.mrbysco.instrumentalmobs.config.InstrumentalConfigForge;
import com.mrbysco.instrumentalmobs.platform.services.IPlatformHelper;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.EventHooks;

import java.util.List;

public class NeoForgePlatformHelper implements IPlatformHelper {


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
	public Level.ExplosionInteraction getExplosionInteraction(Entity entity) {
		if (entity.level().isClientSide()) return ExplosionInteraction.NONE;
		return EventHooks.canEntityGrief((ServerLevel) entity.level(), entity) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
	}

	@Override
	public boolean isEnderMask(ItemStack stack, Player player, EnderMan enderMan) {
		return stack.getItem() == Blocks.CARVED_PUMPKIN.asItem();
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
