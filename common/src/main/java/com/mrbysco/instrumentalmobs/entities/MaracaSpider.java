package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.InstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MaracaSpider extends Spider implements IInstrumentalMobs {
	private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.<Boolean>defineId(MaracaSpider.class, EntityDataSerializers.BOOLEAN);

	public MaracaSpider(EntityType<? extends MaracaSpider> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(4, new MaracaSpider.SpiderInstrumentAttack(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new MaracaSpider.TargetGoal<>(this, Player.class));
		this.targetSelector.addGoal(3, new MaracaSpider.TargetGoal<>(this, IronGolem.class));
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
		return (Boolean) this.getEntityData().get(ATTACKING);
	}

	static class SpiderInstrumentAttack extends InstrumentAttackGoal {
		public SpiderInstrumentAttack(MaracaSpider spider) {
			super(spider, 1.0D, true, InstrumentalSounds.MARACA_SOUND::get);
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
			MaracaSpider spider = (MaracaSpider) this.mob;
			spider.setAttacking(false);
			spider.swinging = false;
		}

		public void start() {
			super.start();
			MaracaSpider spider = (MaracaSpider) this.mob;
			spider.setAttacking(true);
			spider.swing(InteractionHand.MAIN_HAND);
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getBbWidth());
		}
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(MaracaSpider spider, Class<T> classTarget) {
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
		this.playSound(InstrumentalSounds.MARACA_SOUND.get(), 0.15F, 1.0F);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InstrumentalRegistry.MARACA.get()));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(InstrumentalRegistry.MARACA.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, getDropChance());
		this.setDropChance(EquipmentSlot.OFFHAND, getDropChance());
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
										MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData,
										@Nullable CompoundTag compoundTag) {
		RandomSource randomSource = serverLevelAccessor.getRandom();
		spawnGroupData = super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);

		this.populateDefaultEquipmentSlots(randomSource, difficultyInstance);

		return spawnGroupData;
	}
}
