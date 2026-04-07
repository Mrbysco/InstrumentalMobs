package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.entities.goals.SkeletonInstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FleeSunGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RestrictSunGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.entity.animal.turtle.Turtle;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TrumpetSkeleton extends Skeleton implements IInstrumentalMobs, IInstrumentalSkeleton {
	private static final EntityDataAccessor<Boolean> DOOTING = SynchedEntityData.<Boolean>defineId(TrumpetSkeleton.class, EntityDataSerializers.BOOLEAN);

	public TrumpetSkeleton(EntityType<? extends TrumpetSkeleton> type, Level level) {
		super(type, level);
	}

	@Override
	public void registerGoals() {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, net.minecraft.world.entity.animal.wolf.Wolf.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(4, new SkeletonInstrumentAttackGoal<>(this, 1.2D, false, InstrumentalSounds.TRUMPET_SOUND));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	@Override
	protected void dropCustomDeathLoot(ServerLevel serverLevel, DamageSource source, boolean recentlyHit) {
		super.dropCustomDeathLoot(serverLevel, source, recentlyHit);
		if (getDropChance() <= random.nextFloat()) {
			this.spawnAtLocation(serverLevel, InstrumentalRegistry.TRUMPET.get());
		}
	}

	@Override
	public void reassessWeaponGoal() {
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(DOOTING, Boolean.FALSE);
	}

	@Override
	public void setPlayingInstrument(boolean armsRaised) {
		this.getEntityData().set(DOOTING, armsRaised);
	}

	@Override
	public boolean isPlayingInstrument() {
		return (Boolean) this.getEntityData().get(DOOTING);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InstrumentalRegistry.TRUMPET.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, getDropChance());
		Constants.LOGGER.info("{}", getItemBySlot(EquipmentSlot.MAINHAND));
	}
}