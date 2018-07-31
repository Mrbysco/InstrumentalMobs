package com.Mrbysco.InstrumentalMobs.entities;

import com.Mrbysco.InstrumentalMobs.entities.ai.EntityAiSkeletonAttackInstrument;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalLootTables;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalSounds;

import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityXylophoneSkeletal extends EntitySkeleton implements IInstrumentalMobs{

    private static final DataParameter<Boolean> PLAYING_RIBS = EntityDataManager.<Boolean>createKey(EntityXylophoneSkeletal.class, DataSerializers.BOOLEAN);
    
	public EntityXylophoneSkeletal(World worldIn) {
		super(worldIn);
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BONE));
        this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.BONE));
	}
    
    protected void initEntityAI()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIRestrictSun(this));
        this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
        this.tasks.addTask(3, new EntityAIAvoidEntity(this, EntityWolf.class, 6.0F, 1.0D, 1.2D));
        this.tasks.addTask(4, new EntityAiSkeletonAttackInstrument(this, 1.2D, false, InstrumentalSounds.xylophone_sound));
        this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }
    
    @Override
    public void setCombatTask()
    {
    }
    
    @Override
    protected ResourceLocation getLootTable() {
    	return InstrumentalLootTables.XYLOHPONE_SKELETAL_LOOT;
    }
    
    @Override
	protected void entityInit() {
		super.entityInit();
        this.getDataManager().register(PLAYING_RIBS, Boolean.valueOf(false));
	}

    public void setPlayingRibs(boolean armsRaised)
    {
        this.getDataManager().set(PLAYING_RIBS, Boolean.valueOf(armsRaised));
    }

    @SideOnly(Side.CLIENT)
    public boolean isPlayingRibs()
    {
        return ((Boolean)this.getDataManager().get(PLAYING_RIBS)).booleanValue();
    }
    
    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
    }
}