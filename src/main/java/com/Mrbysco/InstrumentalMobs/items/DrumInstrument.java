package com.mrbysco.instrumentalmobs.items;

import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class DrumInstrument extends Item {
	private final Supplier<? extends SoundEvent> sound;
	private final int cooldown;
	private final int useDuration;

	public DrumInstrument(Item.Properties properties, Supplier<? extends SoundEvent> soundIn, int cooldown, int duration) {
		super(properties);

		this.cooldown = cooldown;
		this.sound = soundIn;
		this.useDuration = duration;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);

		if(this.cooldown != 0) {
			playerIn.getCooldowns().addCooldown(this, this.cooldown);
		}

		playerIn.playSound(sound.get(), 1F, 1F);
		if(InstrumentalConfig.COMMON.mobsReact.get()) {
			InstrumentHelper.instrumentDamage(level, playerIn);
		}
		itemstack.hurtAndBreak(1, playerIn, (player) -> player.broadcastBreakEvent(handIn));
		return super.use(level, playerIn, handIn);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return useDuration;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.DRINK;
	}
}
