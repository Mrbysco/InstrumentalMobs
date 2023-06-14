package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.goals.ZombieInstrumentAttackGoal;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
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
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class DrumZombieEntity extends Zombie implements IInstrumentalMobs {
	public DrumZombieEntity(EntityType<? extends DrumZombieEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(4, new DrumZombieEntity.AttackTurtleEggGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.addBehaviourGoals();
	}

	@Override
	protected void addBehaviourGoals() {
		this.goalSelector.addGoal(2, new ZombieInstrumentAttackGoal(this, 1.0D, false, () -> InstrumentalRegistry.SINGLE_DRUM_SOUND.get()));
		this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglin.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	@Override
	public void die(DamageSource cause) {
		this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(InstrumentalRegistry.DRUM_ITEM.get()));
		super.die(cause);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance instance) {
		this.setItemSlot(EquipmentSlot.CHEST, new ItemStack(InstrumentalRegistry.DRUM_BLOCK_ITEM.get()));
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STICK));
		this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.STICK));
		this.setDropChance(EquipmentSlot.CHEST, getDropChance());
	}


	class AttackTurtleEggGoal extends RemoveBlockGoal {
		AttackTurtleEggGoal(PathfinderMob creatureIn, double speed, int yMax) {
			super(Blocks.TURTLE_EGG, creatureIn, speed, yMax);
		}

		public void playDestroyProgressSound(LevelAccessor worldIn, BlockPos pos) {
			worldIn.playSound((Player) null, pos, SoundEvents.ZOMBIE_DESTROY_EGG, SoundSource.HOSTILE, 0.5F, 0.9F + DrumZombieEntity.this.random.nextFloat() * 0.2F);
		}

		public void playBreakSound(Level worldIn, BlockPos pos) {
			worldIn.playSound((Player) null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + worldIn.random.nextFloat() * 0.2F);
		}

		public double acceptedDistance() {
			return 1.14D;
		}
	}
}
