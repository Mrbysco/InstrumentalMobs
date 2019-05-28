package com.mrbysco.instrumentalmobs.entities.ai;

import com.mrbysco.instrumentalmobs.entities.EntityXylophoneSkeletal;

import net.minecraft.util.SoundEvent;

public class EntityAiSkeletonAttackInstrument extends EntityAIAttackInstrument
{
    private final EntityXylophoneSkeletal skeleton;
    private int raiseArmTicks;

    public EntityAiSkeletonAttackInstrument(EntityXylophoneSkeletal skeletonIn, double speedIn, boolean longMemoryIn, SoundEvent sound)
    {
        super(skeletonIn, speedIn, longMemoryIn, sound);
        this.skeleton = skeletonIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        super.resetTask();
        this.skeleton.setPlayingRibs(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void updateTask()
    {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10)
        {
            this.skeleton.setPlayingRibs(true);
        }
        else
        {
            this.skeleton.setPlayingRibs(false);
        }
    }
}