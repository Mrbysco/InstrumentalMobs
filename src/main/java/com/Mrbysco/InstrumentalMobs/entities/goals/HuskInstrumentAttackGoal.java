package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.entities.CymbalHuskEntity;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class HuskInstrumentAttackGoal extends InstrumentAttackGoal {
    private final CymbalHuskEntity zombie;
    private int raiseArmTicks;

    public HuskInstrumentAttackGoal(CymbalHuskEntity zombieIn, double speedIn, boolean longMemoryIn, Supplier<SoundEvent> sound) {
        super(zombieIn, speedIn, longMemoryIn, sound);
        this.zombie = zombieIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.zombie.setAggroed(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        ++this.raiseArmTicks;

        this.zombie.setClapping(this.raiseArmTicks >= 5 && this.field_234037_i_ < 10);
    }
}