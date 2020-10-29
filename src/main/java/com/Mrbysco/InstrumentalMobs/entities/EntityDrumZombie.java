package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.entities.ai.EntityAiZombieAttackInstrument;
import com.mrbysco.instrumentalmobs.init.InstrumentalBlocks;
import com.mrbysco.instrumentalmobs.init.InstrumentalItems;
import com.mrbysco.instrumentalmobs.init.InstrumentalLootTables;
import com.mrbysco.instrumentalmobs.init.InstrumentalSounds;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityDrumZombie extends EntityZombie implements IInstrumentalMobs {
	public EntityDrumZombie(World worldIn) {
		super(worldIn);
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(InstrumentalBlocks.drum));
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STICK));
        this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.STICK));
	}
	
	@Override
	protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAiZombieAttackInstrument(this, 1.0D, false, InstrumentalSounds.single_drum_sound));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

	@Override
	public void onDeath(DamageSource cause) {
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(InstrumentalItems.drum));
		super.onDeath(cause);
	}
	
	@Override
	protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
    }
    
    @Override
    protected ResourceLocation getLootTable() {
    	return InstrumentalLootTables.DRUM_ZOMBIE_LOOT;
    }
}
