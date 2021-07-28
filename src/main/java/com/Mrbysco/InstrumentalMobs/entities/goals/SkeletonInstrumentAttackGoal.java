package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.entities.XylophoneSkeletonEntity;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class SkeletonInstrumentAttackGoal extends InstrumentAttackGoal {
    private final XylophoneSkeletonEntity skeleton;
    private int raiseArmTicks;

    public SkeletonInstrumentAttackGoal(XylophoneSkeletonEntity skeletonIn, double speedIn, boolean longMemoryIn, Supplier<SoundEvent> sound) {
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
        this.skeleton.setPlayingRibs(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;

        this.skeleton.setPlayingRibs(this.raiseArmTicks >= 5 && this.ticksUntilNextAttack < 10);
    }
}