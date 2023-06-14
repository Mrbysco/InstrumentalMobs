package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.InstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MaracaSpiderEntity extends Spider implements IInstrumentalMobs {
	private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.<Boolean>defineId(MaracaSpiderEntity.class, EntityDataSerializers.BOOLEAN);

	public MaracaSpiderEntity(EntityType<? extends MaracaSpiderEntity> type, Level worldIn) {
		super(type, worldIn);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InstrumentalRegistry.MARACA.get()));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(InstrumentalRegistry.MARACA.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, getDropChance());
		this.setDropChance(EquipmentSlot.OFFHAND, getDropChance());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(4, new MaracaSpiderEntity.SpiderInstrumentAttack(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new MaracaSpiderEntity.TargetGoal<>(this, Player.class));
		this.targetSelector.addGoal(3, new MaracaSpiderEntity.TargetGoal<>(this, IronGolem.class));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(ATTACKING, false);
	}

	public void setAttacking(boolean isAttacking) {
		this.getEntityData().set(ATTACKING, isAttacking);
	}

	public boolean isAttacking() {
		return ((Boolean) this.getEntityData().get(ATTACKING)).booleanValue();
	}

	static class SpiderInstrumentAttack extends InstrumentAttackGoal {
		public SpiderInstrumentAttack(MaracaSpiderEntity spider) {
			super(spider, 1.0D, true, () -> InstrumentalRegistry.MARACA_SOUND.get());
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			float f = this.mob.getLightLevelDependentMagicValue();

			if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
				this.mob.setTarget((LivingEntity) null);
				return false;
			} else {
				return super.canContinueToUse();
			}
		}

		public void stop() {
			super.stop();
			MaracaSpiderEntity spider = (MaracaSpiderEntity) this.mob;
			spider.setAttacking(false);
			spider.swinging = false;
		}

		public void start() {
			super.start();
			MaracaSpiderEntity spider = (MaracaSpiderEntity) this.mob;
			spider.setAttacking(true);
			spider.swing(InteractionHand.MAIN_HAND);
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getBbWidth());
		}
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(MaracaSpiderEntity spider, Class<T> classTarget) {
			super(spider, classTarget, true);
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean canUse() {
			float f = this.mob.getLightLevelDependentMagicValue();
			return !(f >= 0.5F) && super.canUse();
		}
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
		this.playSound(InstrumentalRegistry.MARACA_SOUND.get(), 0.15F, 1.0F);
	}
}
