package com.Mrbysco.InstrumentalMobs.entities;

import com.Mrbysco.InstrumentalMobs.entities.ai.EntityAiHuskAttackInstrument;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalItems;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalLootTables;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalSounds;

import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.player.EntityPlayer;
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

public class EntityCymbalHusk extends EntityHusk implements IInstrumentalMobs{

    private static final DataParameter<Boolean> CLAPPING = EntityDataManager.<Boolean>createKey(EntityCymbalHusk.class, DataSerializers.BOOLEAN);
    
	public EntityCymbalHusk(World worldIn) {
		super(worldIn);
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(InstrumentalItems.cymbal));
        this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(InstrumentalItems.cymbal));
	}
	
	@Override
	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAiHuskAttackInstrument(this, 1.0D, false, InstrumentalSounds.cymbals_sound));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
	
	@Override
	protected void entityInit() {
		super.entityInit();
        this.getDataManager().register(CLAPPING, Boolean.valueOf(false));
	}

    public void setClapping(boolean isClapping)
    {
        this.getDataManager().set(CLAPPING, Boolean.valueOf(isClapping));
    }

    @SideOnly(Side.CLIENT)
    public boolean isClapping()
    {
        return ((Boolean)this.getDataManager().get(CLAPPING)).booleanValue();
    }
    
    @Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
    }
    
    @Override
    protected ResourceLocation getLootTable() {
    	return InstrumentalLootTables.CYMBAL_HUSK_LOOT;
    }
}
