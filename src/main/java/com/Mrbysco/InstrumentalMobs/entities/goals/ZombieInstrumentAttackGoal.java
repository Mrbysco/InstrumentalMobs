package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.entities.DrumZombie;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ZombieInstrumentAttackGoal extends InstrumentAttackGoal {
	private final DrumZombie zombie;
	private int raiseArmTicks;

	public ZombieInstrumentAttackGoal(DrumZombie zombieIn, double speedIn, boolean longMemoryIn, Supplier<SoundEvent> sound) {
		super(zombieIn, speedIn, longMemoryIn, sound);
		this.zombie = zombieIn;
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void start() {
		super.start();
		this.raiseArmTicks = 0;
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by another one
	 */
	public void stop() {
		super.stop();
		this.zombie.setAggressive(false);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		super.tick();
		++this.raiseArmTicks;

		this.zombie.setAggressive(this.raiseArmTicks >= 5 && this.ticksUntilNextAttack < 10);
	}
}