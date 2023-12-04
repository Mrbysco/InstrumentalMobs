package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

import java.util.function.Supplier;

public class InstrumentAttackGoal extends MeleeAttackGoal {
	public final Supplier<SoundEvent> instrumentSound;

	public InstrumentAttackGoal(PathfinderMob creature, double speedIn, boolean useLongMemory, Supplier<SoundEvent> sound) {
		super(creature, speedIn, useLongMemory);
		this.instrumentSound = sound;
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity livingEntity) {
		if (this.canPerformAttack(livingEntity)) {
			this.resetAttackCooldown();
			this.mob.swing(InteractionHand.MAIN_HAND);
			this.mob.doHurtTarget(livingEntity);
			InstrumentHelper.instrumentDamage(this.mob);
		}
	}
}
