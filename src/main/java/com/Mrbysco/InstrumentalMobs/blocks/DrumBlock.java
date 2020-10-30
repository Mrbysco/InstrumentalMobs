package com.mrbysco.instrumentalmobs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class DrumBlock extends Block {

	private static final VoxelShape shape = Block.makeCuboidShape(2, 1, 2, 14, 11, 14);
	private static final VoxelShape shape1 = Block.makeCuboidShape(14, 1, 10, 15, 11, 11);
	private static final VoxelShape shape2 = Block.makeCuboidShape(14, 1, 5, 15, 11, 6);
	private static final VoxelShape shape3 = Block.makeCuboidShape(14, 4, 6, 15, 8, 7);
	private static final VoxelShape shape4 = Block.makeCuboidShape(14, 4, 4, 15, 8, 5);
	private static final VoxelShape shape5 = Block.makeCuboidShape(14, 4, 11, 15, 8, 12);
	private static final VoxelShape shape6 = Block.makeCuboidShape(14, 4, 9, 15, 8, 10);
	private static final VoxelShape shape7 = Block.makeCuboidShape(1, 1, 10, 2, 11, 11);
	private static final VoxelShape shape8 = Block.makeCuboidShape(1, 1, 5, 2, 11, 6);
	private static final VoxelShape shape9 = Block.makeCuboidShape(1, 4, 6, 2, 8, 7);
	private static final VoxelShape shape10 = Block.makeCuboidShape(1, 4, 4, 2, 8, 5);
	private static final VoxelShape shape11 = Block.makeCuboidShape(1, 4, 11, 2, 8, 12);
	private static final VoxelShape shape12 = Block.makeCuboidShape(1, 4, 9, 2, 8, 10);
	private static final VoxelShape shape13 = Block.makeCuboidShape(5, 1, 14, 6, 11, 15);
	private static final VoxelShape shape14 = Block.makeCuboidShape(10, 1, 14, 11, 11, 15);
	private static final VoxelShape shape15 = Block.makeCuboidShape(9, 4, 14, 10, 8, 15);
	private static final VoxelShape shape16 = Block.makeCuboidShape(11, 4, 14, 12, 8, 15);
	private static final VoxelShape shape17 = Block.makeCuboidShape(4, 4, 14, 5, 8, 15);
	private static final VoxelShape shape18 = Block.makeCuboidShape(6, 4, 14, 7, 8, 15);
	private static final VoxelShape shape19 = Block.makeCuboidShape(5, 1, 1, 6, 11, 2);
	private static final VoxelShape shape20 = Block.makeCuboidShape(10, 1, 1, 11, 11, 2);
	private static final VoxelShape shape21 = Block.makeCuboidShape(9, 4, 1, 10, 8, 2);
	private static final VoxelShape shape22 = Block.makeCuboidShape(4, 4, 1, 5, 8, 2);
	private static final VoxelShape shape23 = Block.makeCuboidShape(6, 4, 1, 7, 8, 2);
	private static final VoxelShape shape24 = Block.makeCuboidShape(14, 1, 14, 15, 11, 15);
	private static final VoxelShape shape25 = Block.makeCuboidShape(1, 1, 14, 2, 11, 15);
	private static final VoxelShape shape26 = Block.makeCuboidShape(14, 1, 1, 15, 11, 2);
	private static final VoxelShape shape27 = Block.makeCuboidShape(1, 1, 1, 2, 11, 2);
	private static final VoxelShape shape28 = Block.makeCuboidShape(14, 4, 2, 15, 8, 3);
	private static final VoxelShape shape29 = Block.makeCuboidShape(13, 4, 1, 14, 8, 2);
	private static final VoxelShape shape30 = Block.makeCuboidShape(2, 4, 1, 3, 8, 2);
	private static final VoxelShape shape31 = Block.makeCuboidShape(1, 4, 2, 2, 8, 3);
	private static final VoxelShape shape32 = Block.makeCuboidShape(1, 4, 13, 2, 8, 14);
	private static final VoxelShape shape33 = Block.makeCuboidShape(2, 4, 14, 3, 8, 15);
	private static final VoxelShape shape34 = Block.makeCuboidShape(13, 4, 14, 14, 8, 15);
	private static final VoxelShape shape35 = Block.makeCuboidShape(14, 4, 13, 15, 8, 14);
	private static final VoxelShape shape36 = Block.makeCuboidShape(2, 11, 14, 14, 12, 15);
	private static final VoxelShape shape37 = Block.makeCuboidShape(2, 11, 1, 14, 12, 2);
	private static final VoxelShape shape38 = Block.makeCuboidShape(1, 11, 2, 2, 12, 14);
	private static final VoxelShape shape39 = Block.makeCuboidShape(14, 11, 2, 15, 12, 14);
	private static final VoxelShape shape40 = Block.makeCuboidShape(2, 0, 14, 14, 1, 15);
	private static final VoxelShape shape41 = Block.makeCuboidShape(2, 0, 1, 14, 1, 2);
	private static final VoxelShape shape42 = Block.makeCuboidShape(1, 0, 2, 2, 1, 14);
	private static final VoxelShape shape43 = Block.makeCuboidShape(14, 0, 2, 15, 1, 14);

	private static final VoxelShape SHAPE = VoxelShapes.or(shape, shape1, shape2, shape3, shape4, shape5, shape6, shape7, shape8, shape9, shape10, shape11, shape12, shape13, shape14, shape15, shape16, shape17, shape18, shape19, shape20, shape21, shape22, shape23, shape24, shape25, shape26, shape27, shape28, shape29, shape30, shape31, shape32, shape33, shape34, shape35, shape36, shape37, shape38, shape39, shape40, shape41, shape42, shape43).simplify();

	public DrumBlock(Block.Properties properties) {
		super(properties.notSolid());
	}

//	@Override
//	public Item getItemDropped(BlockState state, Random rand, int fortune) {
//		return InstrumentalItems.drum; TODO: Make it drop a drum item
//	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void onBlockClicked(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		if (!worldIn.isRemote) {
			worldIn.addBlockEvent(pos, Blocks.NOTE_BLOCK, 1, 0);
		}
	}
}
