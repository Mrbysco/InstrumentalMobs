package com.mrbysco.instrumentalmobs.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

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
			).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

	public DrumBlock(Block.Properties properties) {
		super(properties.noOcclusion());
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	public void attack(BlockState state, Level worldIn, BlockPos pos, Player player) {
		if (!worldIn.isClientSide) {
			worldIn.blockEvent(pos, Blocks.NOTE_BLOCK, 1, 0);
		}
	}
}
