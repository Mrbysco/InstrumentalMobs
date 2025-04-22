package com.mrbysco.instrumentalmobs.items;

import com.mrbysco.instrumentalmobs.entities.projectiles.MicrophoneWave;
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

public class InstrumentMicrophone extends Item {
	private final Supplier<? extends SoundEvent> sound;
	private final int cooldown;
	private final int useDuration;

	public InstrumentMicrophone(Item.Properties properties, Supplier<? extends SoundEvent> soundIn, int cooldown, int duration) {
		super(properties);
		this.cooldown = cooldown;
		this.sound = soundIn;
		this.useDuration = duration;
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (this.cooldown != 0) {
			player.getCooldowns().addCooldown(stack, this.cooldown);
		}

		if (!level.isClientSide) {
			MicrophoneWave soundWave = new MicrophoneWave(level, player, sound.get());
			soundWave.shoot(player.getXRot(), player.getYRot(), 0.0F, 2.0F, 0.0F);
			soundWave.setOwner(player);
			level.addFreshEntity(soundWave);
		}

		stack.hurtAndBreak(1, player, hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
		return InteractionResult.SUCCESS;
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
