package com.Mrbysco.InstrumentalMobs.items;

import com.Mrbysco.InstrumentalMobs.InstrumentalMobs;
import com.Mrbysco.InstrumentalMobs.Reference;
import com.Mrbysco.InstrumentalMobs.blocks.DrumBlock;
import com.Mrbysco.InstrumentalMobs.config.InstrumentalConfigGen;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalBlocks;
import com.Mrbysco.InstrumentalMobs.utils.InstrumentHelper;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DrumInstrument extends Item{
	
	private SoundEvent sound;
	private int cooldown;
	private int useDuration;
	private Block instrumentBlock = InstrumentalBlocks.drum;

	public DrumInstrument(String registryName, SoundEvent soundIn, int cooldown, int maxDamage, int duration) {
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
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(player.isSneaking())
		{
			IBlockState iblockstate = worldIn.getBlockState(pos);
	        Block block = iblockstate.getBlock();

	        if (!block.isReplaceable(worldIn, pos))
            {
                pos = pos.offset(facing);
            }

            ItemStack itemstack = player.getHeldItem(hand);

            if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(instrumentBlock, pos, false, facing, (Entity)null))
            {
                int i = this.getMetadata(itemstack.getMetadata());
                IBlockState newBlockState = instrumentBlock.getDefaultState().withProperty(DrumBlock.DAMAGE, Math.round(((this.getMaxDamage(itemstack) - this.getDamage(itemstack)) / 16)));

                if (placeBlockAt(itemstack, player, worldIn, pos, facing, hitX, hitY, hitZ, newBlockState))
                {
                	newBlockState = worldIn.getBlockState(pos);
                    SoundType soundtype = newBlockState.getBlock().getSoundType(newBlockState, worldIn, pos, player);
                    worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                    itemstack.shrink(1);
                }

                return EnumActionResult.SUCCESS;
            }
            else
            {
                return EnumActionResult.FAIL;
            }
        }
        else
        {
        	return EnumActionResult.FAIL;
        }
	}
	
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState)
    {
        if (!world.setBlockState(pos, newState, 11)) return false;

        IBlockState state = world.getBlockState(pos);
        if (state.getBlock() == this.instrumentBlock)
        {
            this.instrumentBlock.onBlockPlacedBy(world, pos, state, player, stack);

            if (player instanceof EntityPlayerMP)
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, stack);
        }

        return true;
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
        
        if(this.cooldown != 0)
		{
			playerIn.getCooldownTracker().setCooldown(this, this.cooldown);
		}
        
		playerIn.playSound(this.sound, 1F, 1F);
		if(InstrumentalConfigGen.general.mobsReact)
		{
			InstrumentHelper.instrumentDamage(worldIn, playerIn);
		}
		itemstack.damageItem(1, playerIn);
		return super.onItemRightClick(worldIn, playerIn, handIn);
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
