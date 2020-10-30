package com.mrbysco.instrumentalmobs.entities.goals;

import com.mrbysco.instrumentalmobs.entities.DrumZombieEntity;
import net.minecraft.util.SoundEvent;

import java.util.function.Supplier;

public class ZombieInstrumentAttackGoal extends InstrumentAttackGoal {
    private final DrumZombieEntity zombie;
    private int raiseArmTicks;

    public ZombieInstrumentAttackGoal(DrumZombieEntity zombieIn, double speedIn, boolean longMemoryIn, Supplier<SoundEvent> sound) {
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

        this.zombie.setAggroed(this.raiseArmTicks >= 5 && this.field_234037_i_ < 10);
    }
}