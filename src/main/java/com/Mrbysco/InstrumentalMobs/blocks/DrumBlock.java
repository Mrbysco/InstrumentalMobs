package com.Mrbysco.InstrumentalMobs.blocks;

import java.util.Random;

import com.Mrbysco.InstrumentalMobs.InstrumentalMobs;
import com.Mrbysco.InstrumentalMobs.Reference;
import com.Mrbysco.InstrumentalMobs.config.InstrumentalConfigGen;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalItems;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalSounds;
import com.Mrbysco.InstrumentalMobs.utils.InstrumentHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class DrumBlock extends Block{
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.75D, 0.9375D);
    public static final PropertyInteger DAMAGE = PropertyInteger.create("damage", 0, 15);

	public DrumBlock(String registryName) {
		super(Material.CLOTH);
		this.blockSoundType = blockSoundType.CLOTH;
		this.setHardness(0.8F);
		
		this.setUnlocalizedName(Reference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
		this.setCreativeTab(InstrumentalMobs.instrumentalTab);
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(DAMAGE, 0));
	}
	
	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		playerIn.playSound(InstrumentalSounds.single_drum_sound, 1F, 1F);
		if(InstrumentalConfigGen.general.mobsReact)
		{
			InstrumentHelper.instrumentDamage(worldIn, playerIn);
		}
		
		super.onBlockClicked(worldIn, pos, playerIn);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return InstrumentalItems.drum;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return AABB;
	}
	
	@Override
	public boolean isTopSolid(IBlockState state) {
		return false;
	}
	
	@Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }
	
	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public int damageDropped(IBlockState state)
    {
        return ((Integer)state.getValue(DAMAGE)).intValue();
    }
	
	public IBlockState getStateFromMeta(int meta)
    {
		 return getDefaultState().withProperty(DAMAGE, meta);    
    }

    public int getMetaFromState(IBlockState state)
    {
    	 return state.getValue(DAMAGE);
    }
    
	@Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {DAMAGE});
    }
}
