package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.SkeletonInstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class XylophoneSkeletonEntity extends SkeletonEntity implements IInstrumentalMobs{
    private static final DataParameter<Boolean> PLAYING_RIBS = EntityDataManager.<Boolean>defineId(XylophoneSkeletonEntity.class, DataSerializers.BOOLEAN);

    public XylophoneSkeletonEntity(EntityType<? extends XylophoneSkeletonEntity> type, World worldIn) {
        super(type, worldIn);
        this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BONE));
        this.setItemSlot(EquipmentSlotType.OFFHAND, new ItemStack(Items.BONE));
	}

	@Override
    public void registerGoals() {
        this.goalSelector.addGoal(2, new RestrictSunGoal(this));
        this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(4, new SkeletonInstrumentAttackGoal(this, 1.2D, false, () -> InstrumentalRegistry.xylophone_sound.get()));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int p_213333_2_, boolean p_213333_3_) {
        super.dropCustomDeathLoot(source, p_213333_2_, p_213333_3_);
        if(getDropChance() <= random.nextFloat()) {
            this.spawnAtLocation(InstrumentalRegistry.xylophone.get());
        }
    }

    @Override
    public void reassessWeaponGoal() {
    }

    @Override
	protected void defineSynchedData() {
		super.defineSynchedData();
        this.getEntityData().define(PLAYING_RIBS, Boolean.valueOf(false));
	}

    public void setPlayingRibs(boolean armsRaised) {
        this.getEntityData().set(PLAYING_RIBS, Boolean.valueOf(armsRaised));
    }

    public boolean isPlayingRibs()
    {
        return ((Boolean)this.getEntityData().get(PLAYING_RIBS)).booleanValue();
    }
    
    @Override
    protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty) {
    }
}