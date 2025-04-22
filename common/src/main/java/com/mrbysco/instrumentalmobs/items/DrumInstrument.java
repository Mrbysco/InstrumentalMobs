package com.mrbysco.instrumentalmobs.items;

import com.mrbysco.instrumentalmobs.platform.Services;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class DrumInstrument extends Item {
	private final Supplier<? extends SoundEvent> sound;
	private final int cooldown;
	private final int useDuration;

	public DrumInstrument(Item.Properties properties, Supplier<? extends SoundEvent> soundSupplier, int cooldown, int duration) {
		super(properties);

		this.cooldown = cooldown;
		this.sound = soundSupplier;
		this.useDuration = duration;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (this.cooldown != 0) {
			player.getCooldowns().addCooldown(stack, this.cooldown);
		}

		player.playSound(sound.get(), 1F, 1F);
		if (Services.PLATFORM.mobsReact()) {
			InstrumentHelper.instrumentDamage(player);
		}
		stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
		return super.use(level, player, hand);
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity livingEntity) {
		return useDuration;
	}

	@Override
	public ItemUseAnimation getUseAnimation(ItemStack stack) {
		return ItemUseAnimation.DRINK;
	}
}
