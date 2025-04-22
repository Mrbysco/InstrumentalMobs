package com.mrbysco.instrumentalmobs.platform;

import com.mrbysco.instrumentalmobs.InstrumentalMobsFabric;
import com.mrbysco.instrumentalmobs.platform.services.IPlatformHelper;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
