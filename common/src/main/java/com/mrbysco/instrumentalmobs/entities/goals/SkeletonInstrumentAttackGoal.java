package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.entities.IInstrumentalSkeleton;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.monster.Skeleton;

import java.util.function.Supplier;

public class SkeletonInstrumentAttackGoal<T extends Skeleton & IInstrumentalSkeleton> extends InstrumentAttackGoal {
	private final T skeleton;
	private int raiseArmTicks;

	public SkeletonInstrumentAttackGoal(T skeletonIn, double speedIn, boolean longMemoryIn, Supplier<SoundEvent> sound) {
		super(skeletonIn, speedIn, longMemoryIn, sound);
		this.skeleton = skeletonIn;
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
		this.skeleton.setPlayingInstrument(false);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		super.tick();
		++this.raiseArmTicks;

		this.skeleton.setPlayingInstrument(this.raiseArmTicks >= 5 && this.ticksUntilNextAttack < 10);
	}
}