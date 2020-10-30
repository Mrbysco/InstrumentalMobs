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
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(MaracaSpiderEntity.class, DataSerializers.BOOLEAN);

    public MaracaSpiderEntity(EntityType<? extends MaracaSpiderEntity> type, World worldIn) {
        super(type, worldIn);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(InstrumentalRegistry.maraca.get()));
		this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(InstrumentalRegistry.maraca.get()));
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
	protected void registerData() {
		super.registerData();
        this.getDataManager().register(ATTACKING, false);
	}

    public void setAttacking(boolean isAttacking) {
        this.getDataManager().set(ATTACKING, isAttacking);
    }

    public boolean isAttacking() {
        return ((Boolean)this.getDataManager().get(ATTACKING)).booleanValue();
    }
    
    static class SpiderInstrumentAttack extends InstrumentAttackGoal {
        public SpiderInstrumentAttack(MaracaSpiderEntity spider)
        {
            super(spider, 1.0D, true, () -> InstrumentalRegistry.maraca_sound.get());
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            float f = this.attacker.getBrightness();

            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0) {
                this.attacker.setAttackTarget((LivingEntity)null);
                return false;
            } else {
                return super.shouldContinueExecuting();
            }
        }
        
        public void resetTask() {
            super.resetTask();
            MaracaSpiderEntity spider = (MaracaSpiderEntity)this.attacker;
            spider.setAttacking(false);
            spider.isSwingInProgress = false;
        }

        public void startExecuting() {
            super.startExecuting();
            MaracaSpiderEntity spider = (MaracaSpiderEntity)this.attacker;
            spider.setAttacking(true);
            spider.swingArm(Hand.MAIN_HAND);
        }

        protected double getAttackReachSqr(LivingEntity attackTarget) {
            return (double)(4.0F + attackTarget.getWidth());
        }
    }

    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public TargetGoal(MaracaSpiderEntity spider, Class<T> classTarget) {
            super(spider, classTarget, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute() {
            float f = this.goalOwner.getBrightness();
            return !(f >= 0.5F) && super.shouldExecute();
        }
    }

    @Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
        this.playSound(InstrumentalRegistry.maraca_sound.get(), 0.15F, 1.0F);
	}
}
