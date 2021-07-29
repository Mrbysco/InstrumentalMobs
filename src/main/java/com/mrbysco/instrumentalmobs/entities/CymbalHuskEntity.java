package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.HuskInstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BreakBlockGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class CymbalHuskEntity extends HuskEntity implements IInstrumentalMobs {
    private static final DataParameter<Boolean> CLAPPING = EntityDataManager.<Boolean>defineId(CymbalHuskEntity.class, DataSerializers.BOOLEAN);
    
	public CymbalHuskEntity(EntityType<? extends CymbalHuskEntity> type, World worldIn) {
		super(type, worldIn);
		this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(InstrumentalRegistry.cymbal.get()));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(InstrumentalRegistry.cymbal.get()));
        this.setDropChance(EquipmentSlotType.MAINHAND, getDropChance());
        this.setDropChance(EquipmentSlotType.OFFHAND, getDropChance());
	}
	
	@Override
	protected void registerGoals() {
        this.goalSelector.addGoal(4, new CymbalHuskEntity.AttackTurtleEggGoal(this, 1.0D, 3));
        this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.addBehaviourGoals();
    }

    @Override
    protected void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new HuskInstrumentAttackGoal(this, 1.0D, false, () -> InstrumentalRegistry.cymbals_sound.get()));
        this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
    }

    @Override
	protected void defineSynchedData() {
		super.defineSynchedData();
        this.getEntityData().define(CLAPPING, Boolean.valueOf(false));
	}

    public void setClapping(boolean isClapping)
    {
        this.getEntityData().set(CLAPPING, Boolean.valueOf(isClapping));
    }

    public boolean isClapping()
    {
        return ((Boolean)this.getEntityData().get(CLAPPING)).booleanValue();
    }
    
    @Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
    }

    class AttackTurtleEggGoal extends BreakBlockGoal {
        AttackTurtleEggGoal(CreatureEntity creatureIn, double speed, int yMax) {
            super(Blocks.TURTLE_EGG, creatureIn, speed, yMax);
        }

        public void playDestroyProgressSound(IWorld worldIn, BlockPos pos) {
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundCategory.HOSTILE, 0.5F, 0.9F + CymbalHuskEntity.this.random.nextFloat() * 0.2F);
        }

        public void playBreakSound(World worldIn, BlockPos pos) {
            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundCategory.BLOCKS, 0.7F, 0.9F + worldIn.random.nextFloat() * 0.2F);
        }

        public double acceptedDistance() {
            return 1.14D;
        }
    }

    public static boolean canSpawnHere(EntityType<CymbalHuskEntity> p_223334_0_, IServerWorld p_223334_1_, SpawnReason reason, BlockPos p_223334_3_, Random p_223334_4_) {
        return checkMonsterSpawnRules(p_223334_0_, p_223334_1_, reason, p_223334_3_, p_223334_4_) && (reason == SpawnReason.SPAWNER || p_223334_1_.canSeeSky(p_223334_3_));
    }
}
