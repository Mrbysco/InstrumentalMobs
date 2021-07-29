package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.projectiles.EntitySoundWaves;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;

public class MicrophoneGhastEntity extends GhastEntity implements IInstrumentalMobs {
    private static final DataParameter<Boolean> SINGING = EntityDataManager.<Boolean>defineId(MicrophoneGhastEntity.class, DataSerializers.BOOLEAN);

    public MicrophoneGhastEntity(EntityType<? extends MicrophoneGhastEntity> type, World worldIn) {
        super(type, worldIn);
        this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(InstrumentalRegistry.microphone.get()));
        this.setDropChance(EquipmentSlotType.HEAD, getDropChance());
	}

	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(5, new MicrophoneGhastEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(7, new MicrophoneGhastEntity.LookAroundGoal(this));
        this.goalSelector.addGoal(7, new MicrophoneGhastEntity.VoiceAttackGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213812_1_) -> {
            return Math.abs(p_213812_1_.getY() - this.getY()) <= 4.0D;
        }));
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
        return ((Boolean)this.getEntityData().get(SINGING)).booleanValue();
    }

    static class VoiceAttackGoal extends Goal {
        private final MicrophoneGhastEntity parentEntity;
        public int attackTimer;

        public VoiceAttackGoal(MicrophoneGhastEntity ghast) {
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
            if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D && this.parentEntity.canSee(livingentity)) {
                World world = this.parentEntity.level;
                ++this.attackTimer;
                if (this.attackTimer == 10 && !this.parentEntity.isSilent()) {
                    world.levelEvent((PlayerEntity)null, 1015, this.parentEntity.blockPosition(), 0);
                }

                if (this.attackTimer == 20) {
                    double d1 = 4.0D;
                    Vector3d vector3d = this.parentEntity.getViewVector(1.0F);
                    double d2 = livingentity.getX() - (this.parentEntity.getX() + vector3d.x * 4.0D);
                    double d3 = livingentity.getY(0.5D) - (0.5D + this.parentEntity.getY(0.5D));
                    double d4 = livingentity.getZ() - (this.parentEntity.getZ() + vector3d.z * 4.0D);
                    if (!this.parentEntity.isSilent()) {
                        world.levelEvent((PlayerEntity)null, 1016, this.parentEntity.blockPosition(), 0);
                    }

                    EntitySoundWaves entitysoundwave = new EntitySoundWaves(world, this.parentEntity, d2, d3, d4);
                    entitysoundwave.setPos(this.parentEntity.getX() + vector3d.x * 4.0D, this.parentEntity.getY(0.5D) + 0.5D, entitysoundwave.getZ() + vector3d.z * 4.0D);
                    world.addFreshEntity(entitysoundwave);
                    this.attackTimer = -40;
                }
            } else if (this.attackTimer > 0) {
                --this.attackTimer;
            }

            this.parentEntity.setCharging(this.attackTimer > 10);
        }
    }

    static class LookAroundGoal extends Goal {
        private final MicrophoneGhastEntity parentEntity;

        public LookAroundGoal(MicrophoneGhastEntity ghast) {
            this.parentEntity = ghast;
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
            if (this.parentEntity.getTarget() == null) {
                Vector3d vector3d = this.parentEntity.getDeltaMovement();
                this.parentEntity.yRot = -((float)MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
                this.parentEntity.yBodyRot = this.parentEntity.yRot;
            } else {
                LivingEntity livingentity = this.parentEntity.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.parentEntity) < 4096.0D) {
                    double d1 = livingentity.getX() - this.parentEntity.getX();
                    double d2 = livingentity.getZ() - this.parentEntity.getZ();
                    this.parentEntity.yRot = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.yBodyRot = this.parentEntity.yRot;
                }
            }

        }
    }

    static class RandomFlyGoal extends Goal {
        private final MicrophoneGhastEntity parentEntity;

        public RandomFlyGoal(MicrophoneGhastEntity ghast) {
            this.parentEntity = ghast;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            MovementController movementcontroller = this.parentEntity.getMoveControl();
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
            Random random = this.parentEntity.getRandom();
            double d0 = this.parentEntity.getX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.getY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.getZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    public static boolean canSpawnHere(EntityType<MicrophoneGhastEntity> p_223368_0_, IWorld p_223368_1_, SpawnReason reason, BlockPos p_223368_3_, Random p_223368_4_) {
        return p_223368_1_.getDifficulty() != Difficulty.PEACEFUL && p_223368_4_.nextInt(20) == 0 && checkMobSpawnRules(p_223368_0_, p_223368_1_, reason, p_223368_3_, p_223368_4_);
    }
}
