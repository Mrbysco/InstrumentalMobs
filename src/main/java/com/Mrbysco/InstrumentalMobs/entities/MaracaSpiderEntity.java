package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.InstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MaracaSpiderEntity extends SpiderEntity implements IInstrumentalMobs {
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>defineId(MaracaSpiderEntity.class, DataSerializers.BOOLEAN);

    public MaracaSpiderEntity(EntityType<? extends MaracaSpiderEntity> type, World worldIn) {
        super(type, worldIn);
		this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(InstrumentalRegistry.maraca.get()));
		this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(InstrumentalRegistry.maraca.get()));
        this.setDropChance(EquipmentSlotType.MAINHAND, getDropChance());
        this.setDropChance(EquipmentSlotType.OFFHAND, getDropChance());
	}
	
	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(4, new MaracaSpiderEntity.SpiderInstrumentAttack(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new MaracaSpiderEntity.TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.addGoal(3, new MaracaSpiderEntity.TargetGoal<>(this, IronGolemEntity.class));
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
        return ((Boolean)this.getEntityData().get(ATTACKING)).booleanValue();
    }
    
    static class SpiderInstrumentAttack extends InstrumentAttackGoal {
        public SpiderInstrumentAttack(MaracaSpiderEntity spider)
        {
            super(spider, 1.0D, true, () -> InstrumentalRegistry.maraca_sound.get());
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            float f = this.mob.getBrightness();

            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.canContinueToUse();
            }
        }
        
        public void stop() {
            super.stop();
            MaracaSpiderEntity spider = (MaracaSpiderEntity)this.mob;
            spider.setAttacking(false);
            spider.swinging = false;
        }

        public void start() {
            super.start();
            MaracaSpiderEntity spider = (MaracaSpiderEntity)this.mob;
            spider.setAttacking(true);
            spider.swing(Hand.MAIN_HAND);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(4.0F + attackTarget.getBbWidth());
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
            float f = this.mob.getBrightness();
            return !(f >= 0.5F) && super.canUse();
        }
    }

    @Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.15F, 1.0F);
        this.playSound(InstrumentalRegistry.maraca_sound.get(), 0.15F, 1.0F);
	}
}
