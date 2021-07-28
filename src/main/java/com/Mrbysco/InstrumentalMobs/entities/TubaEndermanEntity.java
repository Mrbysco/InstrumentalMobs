package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.InstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class TubaEndermanEntity extends EndermanEntity implements IInstrumentalMobs {

    public TubaEndermanEntity(EntityType<? extends TubaEndermanEntity> type, World worldIn) {
        super(type, worldIn);
		this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(InstrumentalRegistry.tuba.get()));
		this.setCombatTask();
	}

	private final InstrumentAttackGoal playOnCollideGoal = new InstrumentAttackGoal(this, 1.0D, false, () -> InstrumentalRegistry.tuba_sound.get());

    private void setCombatTask() {
    	if (this.level != null && !this.level.isClientSide) {
    		this.goalSelector.removeGoal(this.playOnCollideGoal);
            ItemStack itemstack = this.getMainHandItem();
            
            if(itemstack.getItem() == InstrumentalRegistry.tuba.get()) {
                this.goalSelector.addGoal(7, this.playOnCollideGoal);
            }
        }
    }

    @Override
    public void playStareSound() {
        if (this.tickCount >= this.lastStareSound + 400) {
            this.lastStareSound = this.tickCount;
            if (!this.isSilent()) {
                this.level.playLocalSound(this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENDERMAN_STARE, this.getSoundSource(), 2.5F, 1.0F, false);
                if(this.getRandom().nextFloat() < 0.3) {
                    this.level.playLocalSound(this.getX(), this.getEyeY(), this.getZ(), InstrumentalRegistry.tuba_sound.get(), this.getSoundSource(), 2.5F, 1.0F, false);
                }
            }
        }
    }

    @Override
	protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new TubaEndermanEntity.StareGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new TubaEndermanEntity.FindPlayerGoal(this, this::isAngryAt));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EndermiteEntity.class, 10, true, false, ENDERMITE_SELECTOR));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        private final TubaEndermanEntity enderman;
        /** The player */
        private PlayerEntity player;
        private int aggroTime;
        private int teleportTime;
        private final EntityPredicate startAggroTargetConditions;
        private final EntityPredicate continueAggroTargetConditions = (new EntityPredicate()).allowUnseeable();

        public FindPlayerGoal(TubaEndermanEntity p_i241912_1_, @Nullable java.util.function.Predicate<LivingEntity> p_i241912_2_) {
            super(p_i241912_1_, PlayerEntity.class, 10, false, false, p_i241912_2_);
            this.enderman = p_i241912_1_;
            this.startAggroTargetConditions = (new EntityPredicate()).range(this.getFollowDistance()).selector((p_220790_1_) -> {
                return p_i241912_1_.shouldAttackPlayer((PlayerEntity)p_220790_1_);
            });
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            this.player = this.enderman.level.getNearestPlayer(this.startAggroTargetConditions, this.enderman);
            return this.player != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.aggroTime = 5;
            this.teleportTime = 0;
            this.enderman.setBeingStaredAt();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            this.player = null;
            super.stop();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            if (this.player != null) {
                if (!this.enderman.shouldAttackPlayer(this.player)) {
                    return false;
                } else {
                    this.enderman.lookAt(this.player, 10.0F, 10.0F);
                    return true;
                }
            } else {
                return this.target != null && this.continueAggroTargetConditions.test(this.enderman, this.target) ? true : super.canContinueToUse();
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.enderman.getTarget() == null) {
                super.setTarget((LivingEntity)null);
            }

            if (this.player != null) {
                if (--this.aggroTime <= 0) {
                    this.target = this.player;
                    this.player = null;
                    super.start();
                }
            } else {
                if (this.target != null && !this.enderman.isPassenger()) {
                    if (this.enderman.shouldAttackPlayer((PlayerEntity)this.target)) {
                        if (this.target.distanceToSqr(this.enderman) < 16.0D) {
                            this.enderman.teleport();
                        }

                        this.teleportTime = 0;
                    } else if (this.target.distanceToSqr(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportTowards(this.target)) {
                        this.teleportTime = 0;
                    }
                }

                super.tick();
            }

        }
    }

    static class StareGoal extends Goal {
        private final TubaEndermanEntity enderman;
        private LivingEntity targetPlayer;

        public StareGoal(TubaEndermanEntity endermanIn) {
            this.enderman = endermanIn;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            this.targetPlayer = this.enderman.getTarget();
            if (!(this.targetPlayer instanceof PlayerEntity)) {
                return false;
            } else {
                double d0 = this.targetPlayer.distanceToSqr(this.enderman);
                return d0 > 256.0D ? false : this.enderman.shouldAttackPlayer((PlayerEntity)this.targetPlayer);
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.enderman.getNavigation().stop();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            this.enderman.getLookControl().setLookAt(this.targetPlayer.getX(), this.targetPlayer.getEyeY(), this.targetPlayer.getZ());
        }
    }

    private boolean shouldAttackPlayer(PlayerEntity player) {
        ItemStack itemstack = player.inventory.armor.get(3);
        if (itemstack.isEnderMask(player, this)) {
            return false;
        } else {
            Vector3d vector3d = player.getViewVector(1.0F).normalize();
            Vector3d vector3d1 = new Vector3d(this.getX() - player.getX(), this.getEyeY() - player.getEyeY(), this.getZ() - player.getZ());
            double d0 = vector3d1.length();
            vector3d1 = vector3d1.normalize();
            double d1 = vector3d.dot(vector3d1);
            return d1 > 1.0D - 0.025D / d0 ? player.canSee(this) : false;
        }
    }
}
