package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class InstrumentAttackGoal extends MeleeAttackGoal {
	public final Supplier<SoundEvent> instrumentSound;
	
	public InstrumentAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory, Supplier<SoundEvent> sound) {
		super(creature, speedIn, useLongMemory);
		this.instrumentSound = sound;
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity livingBase, double p_190102_2_) {
		double d0 = this.getAttackReachSqr(livingBase);

        if (p_190102_2_ <= d0 && this.ticksUntilNextAttack <= 0) {
            this.ticksUntilNextAttack = 20;
            this.mob.swing(Hand.MAIN_HAND);
            this.mob.playSound(instrumentSound.get(), 1F, 1F);
            InstrumentHelper.instrumentDamage(this.mob.level, this.mob);
        }
	}
}
