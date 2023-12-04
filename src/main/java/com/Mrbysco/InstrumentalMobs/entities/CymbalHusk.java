package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.HuskInstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RemoveBlockGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class CymbalHusk extends Husk implements IInstrumentalMobs {
	private static final EntityDataAccessor<Boolean> CLAPPING = SynchedEntityData.<Boolean>defineId(CymbalHusk.class, EntityDataSerializers.BOOLEAN);

	public CymbalHusk(EntityType<? extends CymbalHusk> type, Level level) {
		super(type, level);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InstrumentalRegistry.CYMBAL.get()));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(InstrumentalRegistry.CYMBAL.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, getDropChance());
		this.setDropChance(EquipmentSlot.OFFHAND, getDropChance());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new CymbalHusk.AttackTurtleEggGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.addBehaviourGoals();
	}

	@Override
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(2, new HuskInstrumentAttackGoal(this, 1.0D, false, InstrumentalRegistry.CYMBALS_SOUND::get));
		this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(CLAPPING, Boolean.FALSE);
	}

	public void setPlayingInstrument(boolean isClapping) {
		this.getEntityData().set(CLAPPING, isClapping);
	}

	public boolean isClapping() {
		return (Boolean) this.getEntityData().get(CLAPPING);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource p_219165_, DifficultyInstance p_219166_) {
	}

	class AttackTurtleEggGoal extends RemoveBlockGoal {
		AttackTurtleEggGoal(PathfinderMob creatureIn, double speed, int yMax) {
			super(Blocks.TURTLE_EGG, creatureIn, speed, yMax);
		}

		public void playDestroyProgressSound(LevelAccessor level, BlockPos pos) {
			level.playSound((Player) null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + CymbalHusk.this.random.nextFloat() * 0.2F);
		}

		public void playBreakSound(Level level, BlockPos pos) {
			level.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
		}

		public double acceptedDistance() {
			return 1.14D;
		}
	}

	public static boolean canSpawnHere(EntityType<CymbalHusk> p_223334_0_, ServerLevelAccessor p_223334_1_, MobSpawnType reason, BlockPos p_223334_3_, RandomSource p_223334_4_) {
		return checkMonsterSpawnRules(p_223334_0_, p_223334_1_, reason, p_223334_3_, p_223334_4_) && (reason == MobSpawnType.SPAWNER || p_223334_1_.canSeeSky(p_223334_3_));
	}
}
