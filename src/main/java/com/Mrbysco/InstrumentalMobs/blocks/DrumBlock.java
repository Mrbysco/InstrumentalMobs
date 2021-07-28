package com.mrbysco.instrumentalmobs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.stream.Stream;

public class DrumBlock extends Block {
	private static final VoxelShape SHAPE = Stream.of(
			Block.box(14, 0, 2, 15, 1, 14),
			Block.box(2, 1, 2, 14, 11, 14),
			Block.box(14, 1, 14, 15, 11, 15),
			Block.box(1, 1, 14, 2, 11, 15),
			Block.box(14, 1, 1, 15, 11, 2),
			Block.box(1, 1, 1, 2, 11, 2),
			Block.box(2, 11, 14, 14, 12, 15),
			Block.box(2, 11, 1, 14, 12, 2),
			Block.box(1, 11, 2, 2, 12, 14),
			Block.box(14, 11, 2, 15, 12, 14),
			Block.box(2, 0, 14, 14, 1, 15),
			Block.box(2, 0, 1, 14, 1, 2),
			Block.box(1, 0, 2, 2, 1, 14)
			).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

	public DrumBlock(Block.Properties properties) {
		super(properties.noOcclusion());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public void attack(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		if (!worldIn.isClientSide) {
			worldIn.blockEvent(pos, Blocks.NOTE_BLOCK, 1, 0);
		}
	}
}
