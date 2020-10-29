package com.mrbysco.instrumentalmobs.entities.ai;

import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;

public class EntityAIAttackInstrument extends EntityAIAttackMelee {
	public final SoundEvent instrumentSound;
	
	public EntityAIAttackInstrument(EntityCreature creature, double speedIn, boolean useLongMemory, SoundEvent sound) {
		super(creature, speedIn, useLongMemory);
		this.instrumentSound = sound;
	}

	@Override
	protected void checkAndPerformAttack(EntityLivingBase livingBase, double p_190102_2_) {
		double d0 = this.getAttackReachSqr(livingBase);

        if (p_190102_2_ <= d0 && this.attackTick <= 0) {
            this.attackTick = 20;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.playSound(instrumentSound, 1F, 1F);
            InstrumentHelper.instrumentDamage(this.attacker.world, this.attacker);
        }
	}
}
