package com.mrbysco.instrumentalmobs.items;

import com.mrbysco.instrumentalmobs.entities.projectiles.EntityMicrophoneWave;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
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
	public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
        
        if(this.cooldown != 0) {
			playerIn.getCooldowns().addCooldown(this, this.cooldown);
		}
        
        if (!level.isClientSide) {
			EntityMicrophoneWave soundWave = new EntityMicrophoneWave(level, playerIn, sound.get());
            soundWave.shoot(playerIn.getXRot(), playerIn.getYRot(), 0.0F, 2.0F, 0.0F);
            soundWave.setOwner(playerIn);
            level.addFreshEntity(soundWave);
        }

		itemstack.hurtAndBreak(1, playerIn, (player) -> player.broadcastBreakEvent(handIn));
        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, itemstack);
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
