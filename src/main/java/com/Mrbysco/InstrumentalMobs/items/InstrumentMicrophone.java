package com.Mrbysco.InstrumentalMobs.items;

import com.Mrbysco.InstrumentalMobs.InstrumentalMobs;
import com.Mrbysco.InstrumentalMobs.Reference;
import com.Mrbysco.InstrumentalMobs.entities.projectiles.EntitySoundWaves;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class InstrumentMicrophone extends Item{
	private SoundEvent sound;
	private int cooldown;
	private int useDuration;

	public InstrumentMicrophone(String registryName, SoundEvent soundIn, int cooldown, int maxDamage, int duration) {
		setCreativeTab(InstrumentalMobs.instrumentalTab);
		this.setUnlocalizedName(Reference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
		maxStackSize = 1;
		this.setMaxDamage(maxDamage);
		
		this.cooldown = cooldown;
		sound = soundIn;
		useDuration = duration;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        if(this.cooldown != 0)
		{
			playerIn.getCooldownTracker().setCooldown(this, this.cooldown);
		}
        
        if (!worldIn.isRemote)
        {

            EntitySoundWaves soundWave = new EntitySoundWaves(worldIn, playerIn, sound);
            soundWave.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.spawnEntity(soundWave);
        }
        
		itemstack.damageItem(1, playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) 
	{
		return useDuration;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) 
	{
		return EnumAction.DRINK;
	}
}
