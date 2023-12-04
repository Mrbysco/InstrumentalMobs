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

public class InstrumentItem extends Item {
	private final Supplier<? extends SoundEvent> sound;
	private final int cooldown;
	private final int useDuration;

	public InstrumentItem(Item.Properties properties, Supplier<? extends SoundEvent> soundSupplier, int cooldown, int duration) {
		super(properties);

		this.cooldown = cooldown;
		this.sound = soundSupplier;
		this.useDuration = duration;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);

		if (this.cooldown != 0) {
			player.getCooldowns().addCooldown(this, this.cooldown);
		}

		player.playSound(sound.get(), 1F, 1F);
		if (InstrumentalConfig.COMMON.mobsReact.get()) {
			InstrumentHelper.instrumentDamage(player);
		}
		itemstack.hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(hand));
		return super.use(level, player, hand);
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
