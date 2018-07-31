package com.Mrbysco.InstrumentalMobs.entities;

import java.util.Random;

import com.Mrbysco.InstrumentalMobs.entities.ai.EntityAIAttackInstrument;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalItems;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalLootTables;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalSounds;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMaracaSpider extends EntitySpider implements IInstrumentalMobs{
    private static final DataParameter<Boolean> ATTACKING = EntityDataManager.<Boolean>createKey(EntityCymbalHusk.class, DataSerializers.BOOLEAN);

	public EntityMaracaSpider(World worldIn) {
		super(worldIn);
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(InstrumentalItems.maraca));
		this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(InstrumentalItems.maraca));
	}
	
	@Override
	protected void initEntityAI()
    {
		this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
        this.tasks.addTask(4, new EntityMaracaSpider.AISpiderAttack(this));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityMaracaSpider.AISpiderTarget(this, EntityPlayer.class));
        this.targetTasks.addTask(3, new EntityMaracaSpider.AISpiderTarget(this, EntityIronGolem.class));
    }

    @Override
    protected ResourceLocation getLootTable() {
    	return InstrumentalLootTables.MARACA_SPIDER_LOOT;
    }
    
    @Override
	protected void entityInit() {
		super.entityInit();
        this.getDataManager().register(ATTACKING, Boolean.valueOf(false));
	}

    public void setAttacking(boolean isAttacking)
    {
        this.getDataManager().set(ATTACKING, Boolean.valueOf(isAttacking));
    }

    @SideOnly(Side.CLIENT)
    public boolean isAttacking()
    {
        return ((Boolean)this.getDataManager().get(ATTACKING)).booleanValue();
    }
    
    static class AISpiderAttack extends EntityAIAttackInstrument
    {
        public AISpiderAttack(EntityMaracaSpider spider)
        {
            super(spider, 1.0D, true, InstrumentalSounds.maraca_sound);
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            float f = this.attacker.getBrightness();

            if (f >= 0.5F && this.attacker.getRNG().nextInt(100) == 0)
            {
                this.attacker.setAttackTarget((EntityLivingBase)null);
                return false;
            }
            else
            {
                return super.shouldContinueExecuting();
            }
        }
        
        public void resetTask()
        {
            super.resetTask();
            EntityMaracaSpider spider = (EntityMaracaSpider)this.attacker;
            spider.setAttacking(false);
            spider.isSwingInProgress = false;
        }

        public void startExecuting()
        {
            super.startExecuting();
            EntityMaracaSpider spider = (EntityMaracaSpider)this.attacker;
            spider.setAttacking(true);
            spider.swingArm(EnumHand.MAIN_HAND);
        }

        protected double getAttackReachSqr(EntityLivingBase attackTarget)
        {
            return (double)(4.0F + attackTarget.width);
        }
    }

    static class AISpiderTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T>
    {
        public AISpiderTarget(EntityMaracaSpider spider, Class<T> classTarget)
        {
            super(spider, classTarget, true);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            float f = this.taskOwner.getBrightness();
            return f >= 0.5F ? false : super.shouldExecute();
        }
    }

	public static class GroupData implements IEntityLivingData
    {
        public Potion effect;

        public void setRandomEffect(Random rand)
        {
            int i = rand.nextInt(5);

            if (i <= 1)
            {
                this.effect = MobEffects.SPEED;
            }
            else if (i <= 2)
            {
                this.effect = MobEffects.STRENGTH;
            }
            else if (i <= 3)
            {
                this.effect = MobEffects.REGENERATION;
            }
            else if (i <= 4)
            {
                this.effect = MobEffects.INVISIBILITY;
            }
        }
    }
	
	@Override
	protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
        this.playSound(InstrumentalSounds.maraca_sound, 0.15F, 1.0F);
	}
}
