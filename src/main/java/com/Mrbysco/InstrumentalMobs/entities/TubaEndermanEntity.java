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
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(InstrumentalRegistry.tuba.get()));
		this.setCombatTask();
	}

	private final InstrumentAttackGoal playOnCollideGoal = new InstrumentAttackGoal(this, 1.0D, false, () -> InstrumentalRegistry.tuba_sound.get());

    private void setCombatTask() {
    	if (this.world != null && !this.world.isRemote) {
    		this.goalSelector.removeGoal(this.playOnCollideGoal);
            ItemStack itemstack = this.getHeldItemMainhand();
            
            if(itemstack.getItem() == InstrumentalRegistry.tuba.get()) {
                this.goalSelector.addGoal(7, this.playOnCollideGoal);
            }
        }
    }

    @Override
    public void func_226539_l_() {
        if (this.ticksExisted >= this.field_226536_bz_ + 400) {
            this.field_226536_bz_ = this.ticksExisted;
            if (!this.isSilent()) {
                this.world.playSound(this.getPosX(), this.getPosYEye(), this.getPosZ(), SoundEvents.ENTITY_ENDERMAN_STARE, this.getSoundCategory(), 2.5F, 1.0F, false);
                if(this.getRNG().nextFloat() < 0.3) {
                    this.world.playSound(this.getPosX(), this.getPosYEye(), this.getPosZ(), InstrumentalRegistry.tuba_sound.get(), this.getSoundCategory(), 2.5F, 1.0F, false);
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
        this.targetSelector.addGoal(1, new TubaEndermanEntity.FindPlayerGoal(this, this::func_233680_b_));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, EndermiteEntity.class, 10, true, false, field_213627_bA));
        this.targetSelector.addGoal(4, new ResetAngerGoal<>(this, false));
    }

    static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        private final TubaEndermanEntity enderman;
        /** The player */
        private PlayerEntity player;
        private int aggroTime;
        private int teleportTime;
        private final EntityPredicate field_220791_m;
        private final EntityPredicate field_220792_n = (new EntityPredicate()).setLineOfSiteRequired();

        public FindPlayerGoal(TubaEndermanEntity p_i241912_1_, @Nullable java.util.function.Predicate<LivingEntity> p_i241912_2_) {
            super(p_i241912_1_, PlayerEntity.class, 10, false, false, p_i241912_2_);
            this.enderman = p_i241912_1_;
            this.field_220791_m = (new EntityPredicate()).setDistance(this.getTargetDistance()).setCustomPredicate((p_220790_1_) -> {
                return p_i241912_1_.shouldAttackPlayer((PlayerEntity)p_220790_1_);
            });
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            this.player = this.enderman.world.getClosestPlayer(this.field_220791_m, this.enderman);
            return this.player != null;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.aggroTime = 5;
            this.teleportTime = 0;
            this.enderman.func_226538_eu_();
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            this.player = null;
            super.resetTask();
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            if (this.player != null) {
                if (!this.enderman.shouldAttackPlayer(this.player)) {
                    return false;
                } else {
                    this.enderman.faceEntity(this.player, 10.0F, 10.0F);
                    return true;
                }
            } else {
                return this.nearestTarget != null && this.field_220792_n.canTarget(this.enderman, this.nearestTarget) ? true : super.shouldContinueExecuting();
            }
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.enderman.getAttackTarget() == null) {
                super.setNearestTarget((LivingEntity)null);
            }

            if (this.player != null) {
                if (--this.aggroTime <= 0) {
                    this.nearestTarget = this.player;
                    this.player = null;
                    super.startExecuting();
                }
            } else {
                if (this.nearestTarget != null && !this.enderman.isPassenger()) {
                    if (this.enderman.shouldAttackPlayer((PlayerEntity)this.nearestTarget)) {
                        if (this.nearestTarget.getDistanceSq(this.enderman) < 16.0D) {
                            this.enderman.teleportRandomly();
                        }

                        this.teleportTime = 0;
                    } else if (this.nearestTarget.getDistanceSq(this.enderman) > 256.0D && this.teleportTime++ >= 30 && this.enderman.teleportToEntity(this.nearestTarget)) {
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
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            this.targetPlayer = this.enderman.getAttackTarget();
            if (!(this.targetPlayer instanceof PlayerEntity)) {
                return false;
            } else {
                double d0 = this.targetPlayer.getDistanceSq(this.enderman);
                return d0 > 256.0D ? false : this.enderman.shouldAttackPlayer((PlayerEntity)this.targetPlayer);
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            this.enderman.getNavigator().clearPath();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            this.enderman.getLookController().setLookPosition(this.targetPlayer.getPosX(), this.targetPlayer.getPosYEye(), this.targetPlayer.getPosZ());
        }
    }

    private boolean shouldAttackPlayer(PlayerEntity player) {
        ItemStack itemstack = player.inventory.armorInventory.get(3);
        if (itemstack.isEnderMask(player, this)) {
            return false;
        } else {
            Vector3d vector3d = player.getLook(1.0F).normalize();
            Vector3d vector3d1 = new Vector3d(this.getPosX() - player.getPosX(), this.getPosYEye() - player.getPosYEye(), this.getPosZ() - player.getPosZ());
            double d0 = vector3d1.length();
            vector3d1 = vector3d1.normalize();
            double d1 = vector3d.dotProduct(vector3d1);
            return d1 > 1.0D - 0.025D / d0 ? player.canEntityBeSeen(this) : false;
        }
    }
}
