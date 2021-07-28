package com.mrbysco.instrumentalmobs.items;

import com.mrbysco.instrumentalmobs.entities.projectiles.EntityMicrophoneWave;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

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
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getItemInHand(handIn);
        
        if(this.cooldown != 0) {
			playerIn.getCooldowns().addCooldown(this, this.cooldown);
		}
        
        if (!worldIn.isClientSide) {
			EntityMicrophoneWave soundWave = new EntityMicrophoneWave(worldIn, playerIn, sound.get());
            soundWave.shoot(playerIn.xRot, playerIn.yRot, 0.0F, 2.0F, 0.0F);
            soundWave.setOwner(playerIn);
            worldIn.addFreshEntity(soundWave);
        }

		itemstack.hurtAndBreak(1, playerIn, (p_220040_1_) -> p_220040_1_.broadcastBreakEvent(handIn));
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, itemstack);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return useDuration;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack) {
		return UseAction.DRINK;
	}
}
