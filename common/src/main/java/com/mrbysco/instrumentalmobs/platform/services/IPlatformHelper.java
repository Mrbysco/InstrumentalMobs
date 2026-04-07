package com.mrbysco.instrumentalmobs.platform.services;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IPlatformHelper {

	/**
	 * Build the creative tab for the mod
	 *
	 * @return The creative tab
	 */
	CreativeModeTab buildCreativeTab();

	/**
	 * Gets the explosion interaction mode for the given entity
	 *
	 * @return The explosion interaction mode
	 */
	Level.ExplosionInteraction getExplosionInteraction(Entity entity);

	/**
	 * If the given stack is a wearable item that stops endermen from targeting the player
	 *
	 * @param stack    The stack to check
	 * @param player   The player to check
	 * @param enderMan The enderman to check
	 * @return If it stops endermen from targeting the player
	 */
	boolean isEnderMask(ItemStack stack, Player player, EnderMan enderMan);
}
