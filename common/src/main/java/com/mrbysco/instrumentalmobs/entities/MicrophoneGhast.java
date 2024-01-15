package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.projectiles.SoundWaves;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class MicrophoneGhast extends Ghast implements IInstrumentalMobs {
	private static final EntityDataAccessor<Boolean> SINGING = SynchedEntityData.<Boolean>defineId(MicrophoneGhast.class, EntityDataSerializers.BOOLEAN);

	public MicrophoneGhast(EntityType<? extends MicrophoneGhast> type, Level level) {
		super(type, level);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new MicrophoneGhast.RandomFlyGoal(this));
		this.goalSelector.addGoal(7, new MicrophoneGhast.LookAroundGoal(this));
		this.goalSelector.addGoal(7, new MicrophoneGhast.VoiceAttackGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class,
				10, true, false, (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0D));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(SINGING, false);
	}

	public void setSinging(boolean isSinging) {
		this.getEntityData().set(SINGING, isSinging);
	}

	public boolean isSinging() {
		return (Boolean) this.getEntityData().get(SINGING);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
		this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(InstrumentalRegistry.MICROPHONE.get()));
		this.setDropChance(EquipmentSlot.HEAD, getDropChance());
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
										MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData,
										@Nullable CompoundTag compoundTag) {
		RandomSource randomSource = serverLevelAccessor.getRandom();
		spawnGroupData = super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);

		this.populateDefaultEquipmentSlots(randomSource, difficultyInstance);

		return spawnGroupData;
	}

	static class VoiceAttackGoal extends Goal {
		private final MicrophoneGhast parentEntity;
		public int attackTimer;

		public VoiceAttackGoal(MicrophoneGhast ghast) {
			this.parentEntity = ghast;
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			return this.parentEntity.getTarget() != null;
		}


		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			this.attackTimer = 0;
			this.parentEntity.setSinging(true);
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by another one
		 */
		public void stop() {
			this.parentEntity.setCharging(false);
			this.parentEntity.setSinging(false);
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			LivingEntity livingentity = this.parentEntity.getTarget();
			double d0 = 64.0D;
			if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.hasLineOfSight(livingentity)) {
				Level world = this.parentEntity.level();
				++this.attackTimer;
				if (this.attackTimer == 10 && !this.parentEntity.isSilent()) {
					world.levelEvent((Player) null, 1015, this.parentEntity.blockPosition(), 0);
				}

				if (this.attackTimer == 20) {
					double d1 = 4.0D;
					Vec3 vector3d = this.parentEntity.getViewVector(1.0F);
					double d2 = livingentity.getX() - (this.parentEntity.getX() + vector3d.x * 4.0D);
					double d3 = livingentity.getY(0.5D) - (0.5D + this.parentEntity.getY(0.5D));
					double d4 = livingentity.getZ() - (this.parentEntity.getZ() + vector3d.z * 4.0D);
					if (!this.parentEntity.isSilent()) {
						world.levelEvent((Player) null, 1016, this.parentEntity.blockPosition(), 0);
					}

					SoundWaves soundWaves = new SoundWaves(world, this.parentEntity, d2, d3, d4);
					soundWaves.setPos(this.parentEntity.getX() + vector3d.x * 4.0D, this.parentEntity.getY(0.5D) + 0.5D, soundWaves.getZ() + vector3d.z * 4.0D);
					world.addFreshEntity(soundWaves);
					this.attackTimer = -40;
				}
			} else if (this.attackTimer > 0) {
				--this.attackTimer;
			}

			this.parentEntity.setCharging(this.attackTimer > 10);
		}
	}

	static class LookAroundGoal extends Goal {
		private final MicrophoneGhast ghast;

		public LookAroundGoal(MicrophoneGhast ghast) {
			this.ghast = ghast;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			return true;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			if (this.ghast.getTarget() == null) {
				Vec3 vec3 = this.ghast.getDeltaMovement();
				this.ghast.setYRot(-((float) Mth.atan2(vec3.x, vec3.z)) * (180F / (float) Math.PI));
				this.ghast.yBodyRot = this.ghast.getYRot();
			} else {
				LivingEntity livingentity = this.ghast.getTarget();
				double d0 = 64.0D;
				if (livingentity.distanceToSqr(this.ghast) < 4096.0D) {
					double d1 = livingentity.getX() - this.ghast.getX();
					double d2 = livingentity.getZ() - this.ghast.getZ();
					this.ghast.setYRot(-((float) Mth.atan2(d1, d2)) * (180F / (float) Math.PI));
					this.ghast.yBodyRot = this.ghast.getYRot();
				}
			}

		}
	}

	static class RandomFlyGoal extends Goal {
		private final MicrophoneGhast parentEntity;

		public RandomFlyGoal(MicrophoneGhast ghast) {
			this.parentEntity = ghast;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
		 * method as well.
		 */
		public boolean canUse() {
			MoveControl movementcontroller = this.parentEntity.getMoveControl();
			if (!movementcontroller.hasWanted()) {
				return true;
			} else {
				double d0 = movementcontroller.getWantedX() - this.parentEntity.getX();
				double d1 = movementcontroller.getWantedY() - this.parentEntity.getY();
				double d2 = movementcontroller.getWantedZ() - this.parentEntity.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean canContinueToUse() {
			return false;
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void start() {
			RandomSource random = this.parentEntity.getRandom();
			double d0 = this.parentEntity.getX() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d1 = this.parentEntity.getY() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			double d2 = this.parentEntity.getZ() + (double) ((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
			this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
		}
	}

	public static boolean canSpawnHere(EntityType<MicrophoneGhast> p_223368_0_, LevelAccessor p_223368_1_, MobSpawnType reason, BlockPos p_223368_3_, RandomSource p_223368_4_) {
		return p_223368_1_.getDifficulty() != Difficulty.PEACEFUL && p_223368_4_.nextInt(20) == 0 && checkMobSpawnRules(p_223368_0_, p_223368_1_, reason, p_223368_3_, p_223368_4_);
	}
}
