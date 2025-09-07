package net.yyswys.estop.block;

import org.checkerframework.checker.units.qual.s;

import net.yyswys.estop.procedures.EStopRightClickProcedure;
import net.yyswys.estop.procedures.EStopRedstoneReversedProcedure;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class EStopReversedBlock extends Block implements SimpleWaterloggedBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 1);
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

	public EStopReversedBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.FIRE).sound(SoundType.METAL).strength(1f, 10f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				return 0;
			}
		}.getLightLevel())).noCollission().noOcclusion().isRedstoneConductor((bs, br, bp) -> false).dynamicShape());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(POWERED, false).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BLOCKSTATE) == 1) {
			return switch (state.getValue(FACING)) {
				default -> Shapes.or(box(5, 6, 0, 11, 10, 4), box(7, 7, 3, 9, 9, 5), box(9.5, 6.5, 5, 11, 9.5, 6), box(8.75, 9.63934, 5, 10.25, 10.68934, 6), box(6.5, 9.5, 5, 9.5, 11, 6), box(5.75, 9.63934, 5, 7.25, 10.68934, 6),
						box(5.31066, 9.2, 5, 6.81066, 10.25, 6), box(5, 6.5, 5, 6.5, 9.5, 6), box(5.75, 5.31066, 5, 6.8, 6.81066, 6), box(5.31066, 5.75, 5, 6.31066, 7.25, 6), box(6.5, 5, 5, 9.5, 6.5, 6), box(9.68934, 5.75, 5, 10.68934, 7.25, 6),
						box(6.5, 6.5, 5, 9.5, 9.5, 6), box(9.18934, 9.2, 5, 10.68934, 10.25, 6), box(9.2, 5.31066, 5, 10.25, 6.81066, 6), box(7.5, 7.5, 3, 8.5, 8.5, 5), box(11, 6, 0, 11.5, 10, 1), box(11.5, 6, 0, 12.5, 10, 5),
						box(6, 11.5, 0, 10, 12.5, 5), box(6, 3.5, 0, 10, 4.5, 5), box(3.5, 3.5, 0, 6, 4.5, 6), box(3.5, 4.5, 0, 4.5, 6, 6), box(3.5, 10, 0, 4.5, 11.5, 6), box(11.5, 4.5, 0, 12.5, 6, 6), box(11.5, 10, 0, 12.5, 11.5, 6),
						box(3.5, 11.5, 0, 6, 12.5, 6), box(10, 11.5, 0, 12.5, 12.5, 6), box(10, 3.5, 0, 12.5, 4.5, 6), box(3.5, 6, 0, 4.5, 10, 5), box(4.5, 6, 0, 5, 10, 1), box(4.5, 10, 0, 11.5, 11.5, 1), box(4.5, 4.5, 0, 11.5, 6, 1));
				case NORTH -> Shapes.or(box(5, 6, 12, 11, 10, 16), box(7, 7, 11, 9, 9, 13), box(5, 6.5, 10, 6.5, 9.5, 11), box(5.75, 9.63934, 10, 7.25, 10.68934, 11), box(6.5, 9.5, 10, 9.5, 11, 11), box(8.75, 9.63934, 10, 10.25, 10.68934, 11),
						box(9.18934, 9.2, 10, 10.68934, 10.25, 11), box(9.5, 6.5, 10, 11, 9.5, 11), box(9.2, 5.31066, 10, 10.25, 6.81066, 11), box(9.68934, 5.75, 10, 10.68934, 7.25, 11), box(6.5, 5, 10, 9.5, 6.5, 11),
						box(5.31066, 5.75, 10, 6.31066, 7.25, 11), box(6.5, 6.5, 10, 9.5, 9.5, 11), box(5.31066, 9.2, 10, 6.81066, 10.25, 11), box(5.75, 5.31066, 10, 6.8, 6.81066, 11), box(7.5, 7.5, 11, 8.5, 8.5, 13), box(4.5, 6, 15, 5, 10, 16),
						box(3.5, 6, 11, 4.5, 10, 16), box(6, 11.5, 11, 10, 12.5, 16), box(6, 3.5, 11, 10, 4.5, 16), box(10, 3.5, 10, 12.5, 4.5, 16), box(11.5, 4.5, 10, 12.5, 6, 16), box(11.5, 10, 10, 12.5, 11.5, 16), box(3.5, 4.5, 10, 4.5, 6, 16),
						box(3.5, 10, 10, 4.5, 11.5, 16), box(10, 11.5, 10, 12.5, 12.5, 16), box(3.5, 11.5, 10, 6, 12.5, 16), box(3.5, 3.5, 10, 6, 4.5, 16), box(11.5, 6, 11, 12.5, 10, 16), box(11, 6, 15, 11.5, 10, 16),
						box(4.5, 10, 15, 11.5, 11.5, 16), box(4.5, 4.5, 15, 11.5, 6, 16));
				case EAST -> Shapes.or(box(0, 6, 5, 4, 10, 11), box(3, 7, 7, 5, 9, 9), box(5, 6.5, 5, 6, 9.5, 6.5), box(5, 9.63934, 5.75, 6, 10.68934, 7.25), box(5, 9.5, 6.5, 6, 11, 9.5), box(5, 9.63934, 8.75, 6, 10.68934, 10.25),
						box(5, 9.2, 9.18934, 6, 10.25, 10.68934), box(5, 6.5, 9.5, 6, 9.5, 11), box(5, 5.31066, 9.2, 6, 6.81066, 10.25), box(5, 5.75, 9.68934, 6, 7.25, 10.68934), box(5, 5, 6.5, 6, 6.5, 9.5), box(5, 5.75, 5.31066, 6, 7.25, 6.31066),
						box(5, 6.5, 6.5, 6, 9.5, 9.5), box(5, 9.2, 5.31066, 6, 10.25, 6.81066), box(5, 5.31066, 5.75, 6, 6.81066, 6.8), box(3, 7.5, 7.5, 5, 8.5, 8.5), box(0, 6, 4.5, 1, 10, 5), box(0, 6, 3.5, 5, 10, 4.5), box(0, 11.5, 6, 5, 12.5, 10),
						box(0, 3.5, 6, 5, 4.5, 10), box(0, 3.5, 10, 6, 4.5, 12.5), box(0, 4.5, 11.5, 6, 6, 12.5), box(0, 10, 11.5, 6, 11.5, 12.5), box(0, 4.5, 3.5, 6, 6, 4.5), box(0, 10, 3.5, 6, 11.5, 4.5), box(0, 11.5, 10, 6, 12.5, 12.5),
						box(0, 11.5, 3.5, 6, 12.5, 6), box(0, 3.5, 3.5, 6, 4.5, 6), box(0, 6, 11.5, 5, 10, 12.5), box(0, 6, 11, 1, 10, 11.5), box(0, 10, 4.5, 1, 11.5, 11.5), box(0, 4.5, 4.5, 1, 6, 11.5));
				case WEST -> Shapes.or(box(12, 6, 5, 16, 10, 11), box(11, 7, 7, 13, 9, 9), box(10, 6.5, 9.5, 11, 9.5, 11), box(10, 9.63934, 8.75, 11, 10.68934, 10.25), box(10, 9.5, 6.5, 11, 11, 9.5), box(10, 9.63934, 5.75, 11, 10.68934, 7.25),
						box(10, 9.2, 5.31066, 11, 10.25, 6.81066), box(10, 6.5, 5, 11, 9.5, 6.5), box(10, 5.31066, 5.75, 11, 6.81066, 6.8), box(10, 5.75, 5.31066, 11, 7.25, 6.31066), box(10, 5, 6.5, 11, 6.5, 9.5),
						box(10, 5.75, 9.68934, 11, 7.25, 10.68934), box(10, 6.5, 6.5, 11, 9.5, 9.5), box(10, 9.2, 9.18934, 11, 10.25, 10.68934), box(10, 5.31066, 9.2, 11, 6.81066, 10.25), box(11, 7.5, 7.5, 13, 8.5, 8.5), box(15, 6, 11, 16, 10, 11.5),
						box(11, 6, 11.5, 16, 10, 12.5), box(11, 11.5, 6, 16, 12.5, 10), box(11, 3.5, 6, 16, 4.5, 10), box(10, 3.5, 3.5, 16, 4.5, 6), box(10, 4.5, 3.5, 16, 6, 4.5), box(10, 10, 3.5, 16, 11.5, 4.5), box(10, 4.5, 11.5, 16, 6, 12.5),
						box(10, 10, 11.5, 16, 11.5, 12.5), box(10, 11.5, 3.5, 16, 12.5, 6), box(10, 11.5, 10, 16, 12.5, 12.5), box(10, 3.5, 10, 16, 4.5, 12.5), box(11, 6, 3.5, 16, 10, 4.5), box(15, 6, 4.5, 16, 10, 5),
						box(15, 10, 4.5, 16, 11.5, 11.5), box(15, 4.5, 4.5, 16, 6, 11.5));
				case UP -> Shapes.or(box(5, 0, 6, 11, 4, 10), box(7, 3, 7, 9, 5, 9), box(5, 5, 6.5, 6.5, 6, 9.5), box(5.75, 5, 9.63934, 7.25, 6, 10.68934), box(6.5, 5, 9.5, 9.5, 6, 11), box(8.75, 5, 9.63934, 10.25, 6, 10.68934),
						box(9.18934, 5, 9.2, 10.68934, 6, 10.25), box(9.5, 5, 6.5, 11, 6, 9.5), box(9.2, 5, 5.31066, 10.25, 6, 6.81066), box(9.68934, 5, 5.75, 10.68934, 6, 7.25), box(6.5, 5, 5, 9.5, 6, 6.5), box(5.31066, 5, 5.75, 6.31066, 6, 7.25),
						box(6.5, 5, 6.5, 9.5, 6, 9.5), box(5.31066, 5, 9.2, 6.81066, 6, 10.25), box(5.75, 5, 5.31066, 6.8, 6, 6.81066), box(7.5, 3, 7.5, 8.5, 5, 8.5), box(4.5, 0, 6, 5, 1, 10), box(3.5, 0, 6, 4.5, 5, 10), box(6, 0, 11.5, 10, 5, 12.5),
						box(6, 0, 3.5, 10, 5, 4.5), box(10, 0, 3.5, 12.5, 6, 4.5), box(11.5, 0, 4.5, 12.5, 6, 6), box(11.5, 0, 10, 12.5, 6, 11.5), box(3.5, 0, 4.5, 4.5, 6, 6), box(3.5, 0, 10, 4.5, 6, 11.5), box(10, 0, 11.5, 12.5, 6, 12.5),
						box(3.5, 0, 11.5, 6, 6, 12.5), box(3.5, 0, 3.5, 6, 6, 4.5), box(11.5, 0, 6, 12.5, 5, 10), box(11, 0, 6, 11.5, 1, 10), box(4.5, 0, 10, 11.5, 1, 11.5), box(4.5, 0, 4.5, 11.5, 1, 6));
				case DOWN -> Shapes.or(box(5, 12, 6, 11, 16, 10), box(7, 11, 7, 9, 13, 9), box(5, 10, 6.5, 6.5, 11, 9.5), box(5.75, 10, 5.31066, 7.25, 11, 6.36066), box(6.5, 10, 5, 9.5, 11, 6.5), box(8.75, 10, 5.31066, 10.25, 11, 6.36066),
						box(9.18934, 10, 5.75, 10.68934, 11, 6.8), box(9.5, 10, 6.5, 11, 11, 9.5), box(9.2, 10, 9.18934, 10.25, 11, 10.68934), box(9.68934, 10, 8.75, 10.68934, 11, 10.25), box(6.5, 10, 9.5, 9.5, 11, 11),
						box(5.31066, 10, 8.75, 6.31066, 11, 10.25), box(6.5, 10, 6.5, 9.5, 11, 9.5), box(5.31066, 10, 5.75, 6.81066, 11, 6.8), box(5.75, 10, 9.18934, 6.8, 11, 10.68934), box(7.5, 11, 7.5, 8.5, 13, 8.5), box(4.5, 15, 6, 5, 16, 10),
						box(3.5, 11, 6, 4.5, 16, 10), box(6, 11, 3.5, 10, 16, 4.5), box(6, 11, 11.5, 10, 16, 12.5), box(10, 10, 11.5, 12.5, 16, 12.5), box(11.5, 10, 10, 12.5, 16, 11.5), box(11.5, 10, 4.5, 12.5, 16, 6),
						box(3.5, 10, 10, 4.5, 16, 11.5), box(3.5, 10, 4.5, 4.5, 16, 6), box(10, 10, 3.5, 12.5, 16, 4.5), box(3.5, 10, 3.5, 6, 16, 4.5), box(3.5, 10, 11.5, 6, 16, 12.5), box(11.5, 11, 6, 12.5, 16, 10), box(11, 15, 6, 11.5, 16, 10),
						box(4.5, 15, 4.5, 11.5, 16, 6), box(4.5, 15, 10, 11.5, 16, 11.5));
			};
		}
		return switch (state.getValue(FACING)) {
			default -> Shapes.or(box(5, 6, 0, 11, 10, 4), box(7, 7, 3, 9, 9, 5), box(9.5, 6.5, 6, 11, 9.5, 7), box(8.75, 9.63934, 6, 10.25, 10.68934, 7), box(6.5, 9.5, 6, 9.5, 11, 7), box(5.75, 9.63934, 6, 7.25, 10.68934, 7),
					box(5.31066, 9.2, 6, 6.81066, 10.25, 7), box(5, 6.5, 6, 6.5, 9.5, 7), box(5.75, 5.31066, 6, 6.8, 6.81066, 7), box(5.31066, 5.75, 6, 6.31066, 7.25, 7), box(6.5, 5, 6, 9.5, 6.5, 7), box(9.68934, 5.75, 6, 10.68934, 7.25, 7),
					box(6.5, 6.5, 6, 9.5, 9.5, 7), box(9.18934, 9.2, 6, 10.68934, 10.25, 7), box(9.2, 5.31066, 6, 10.25, 6.81066, 7), box(7.5, 7.5, 4, 8.5, 8.5, 6), box(11, 6, 0, 11.5, 10, 1), box(11.5, 6, 0, 12.5, 10, 5),
					box(6, 11.5, 0, 10, 12.5, 5), box(6, 3.5, 0, 10, 4.5, 5), box(3.5, 3.5, 0, 6, 4.5, 6), box(3.5, 4.5, 0, 4.5, 6, 6), box(3.5, 10, 0, 4.5, 11.5, 6), box(11.5, 4.5, 0, 12.5, 6, 6), box(11.5, 10, 0, 12.5, 11.5, 6),
					box(3.5, 11.5, 0, 6, 12.5, 6), box(10, 11.5, 0, 12.5, 12.5, 6), box(10, 3.5, 0, 12.5, 4.5, 6), box(3.5, 6, 0, 4.5, 10, 5), box(4.5, 6, 0, 5, 10, 1), box(4.5, 10, 0, 11.5, 11.5, 1), box(4.5, 4.5, 0, 11.5, 6, 1));
			case NORTH -> Shapes.or(box(5, 6, 12, 11, 10, 16), box(7, 7, 11, 9, 9, 13), box(5, 6.5, 9, 6.5, 9.5, 10), box(5.75, 9.63934, 9, 7.25, 10.68934, 10), box(6.5, 9.5, 9, 9.5, 11, 10), box(8.75, 9.63934, 9, 10.25, 10.68934, 10),
					box(9.18934, 9.2, 9, 10.68934, 10.25, 10), box(9.5, 6.5, 9, 11, 9.5, 10), box(9.2, 5.31066, 9, 10.25, 6.81066, 10), box(9.68934, 5.75, 9, 10.68934, 7.25, 10), box(6.5, 5, 9, 9.5, 6.5, 10), box(5.31066, 5.75, 9, 6.31066, 7.25, 10),
					box(6.5, 6.5, 9, 9.5, 9.5, 10), box(5.31066, 9.2, 9, 6.81066, 10.25, 10), box(5.75, 5.31066, 9, 6.8, 6.81066, 10), box(7.5, 7.5, 10, 8.5, 8.5, 12), box(4.5, 6, 15, 5, 10, 16), box(3.5, 6, 11, 4.5, 10, 16),
					box(6, 11.5, 11, 10, 12.5, 16), box(6, 3.5, 11, 10, 4.5, 16), box(10, 3.5, 10, 12.5, 4.5, 16), box(11.5, 4.5, 10, 12.5, 6, 16), box(11.5, 10, 10, 12.5, 11.5, 16), box(3.5, 4.5, 10, 4.5, 6, 16), box(3.5, 10, 10, 4.5, 11.5, 16),
					box(10, 11.5, 10, 12.5, 12.5, 16), box(3.5, 11.5, 10, 6, 12.5, 16), box(3.5, 3.5, 10, 6, 4.5, 16), box(11.5, 6, 11, 12.5, 10, 16), box(11, 6, 15, 11.5, 10, 16), box(4.5, 10, 15, 11.5, 11.5, 16), box(4.5, 4.5, 15, 11.5, 6, 16));
			case EAST -> Shapes.or(box(0, 6, 5, 4, 10, 11), box(3, 7, 7, 5, 9, 9), box(6, 6.5, 5, 7, 9.5, 6.5), box(6, 9.63934, 5.75, 7, 10.68934, 7.25), box(6, 9.5, 6.5, 7, 11, 9.5), box(6, 9.63934, 8.75, 7, 10.68934, 10.25),
					box(6, 9.2, 9.18934, 7, 10.25, 10.68934), box(6, 6.5, 9.5, 7, 9.5, 11), box(6, 5.31066, 9.2, 7, 6.81066, 10.25), box(6, 5.75, 9.68934, 7, 7.25, 10.68934), box(6, 5, 6.5, 7, 6.5, 9.5), box(6, 5.75, 5.31066, 7, 7.25, 6.31066),
					box(6, 6.5, 6.5, 7, 9.5, 9.5), box(6, 9.2, 5.31066, 7, 10.25, 6.81066), box(6, 5.31066, 5.75, 7, 6.81066, 6.8), box(4, 7.5, 7.5, 6, 8.5, 8.5), box(0, 6, 4.5, 1, 10, 5), box(0, 6, 3.5, 5, 10, 4.5), box(0, 11.5, 6, 5, 12.5, 10),
					box(0, 3.5, 6, 5, 4.5, 10), box(0, 3.5, 10, 6, 4.5, 12.5), box(0, 4.5, 11.5, 6, 6, 12.5), box(0, 10, 11.5, 6, 11.5, 12.5), box(0, 4.5, 3.5, 6, 6, 4.5), box(0, 10, 3.5, 6, 11.5, 4.5), box(0, 11.5, 10, 6, 12.5, 12.5),
					box(0, 11.5, 3.5, 6, 12.5, 6), box(0, 3.5, 3.5, 6, 4.5, 6), box(0, 6, 11.5, 5, 10, 12.5), box(0, 6, 11, 1, 10, 11.5), box(0, 10, 4.5, 1, 11.5, 11.5), box(0, 4.5, 4.5, 1, 6, 11.5));
			case WEST -> Shapes.or(box(12, 6, 5, 16, 10, 11), box(11, 7, 7, 13, 9, 9), box(9, 6.5, 9.5, 10, 9.5, 11), box(9, 9.63934, 8.75, 10, 10.68934, 10.25), box(9, 9.5, 6.5, 10, 11, 9.5), box(9, 9.63934, 5.75, 10, 10.68934, 7.25),
					box(9, 9.2, 5.31066, 10, 10.25, 6.81066), box(9, 6.5, 5, 10, 9.5, 6.5), box(9, 5.31066, 5.75, 10, 6.81066, 6.8), box(9, 5.75, 5.31066, 10, 7.25, 6.31066), box(9, 5, 6.5, 10, 6.5, 9.5), box(9, 5.75, 9.68934, 10, 7.25, 10.68934),
					box(9, 6.5, 6.5, 10, 9.5, 9.5), box(9, 9.2, 9.18934, 10, 10.25, 10.68934), box(9, 5.31066, 9.2, 10, 6.81066, 10.25), box(10, 7.5, 7.5, 12, 8.5, 8.5), box(15, 6, 11, 16, 10, 11.5), box(11, 6, 11.5, 16, 10, 12.5),
					box(11, 11.5, 6, 16, 12.5, 10), box(11, 3.5, 6, 16, 4.5, 10), box(10, 3.5, 3.5, 16, 4.5, 6), box(10, 4.5, 3.5, 16, 6, 4.5), box(10, 10, 3.5, 16, 11.5, 4.5), box(10, 4.5, 11.5, 16, 6, 12.5), box(10, 10, 11.5, 16, 11.5, 12.5),
					box(10, 11.5, 3.5, 16, 12.5, 6), box(10, 11.5, 10, 16, 12.5, 12.5), box(10, 3.5, 10, 16, 4.5, 12.5), box(11, 6, 3.5, 16, 10, 4.5), box(15, 6, 4.5, 16, 10, 5), box(15, 10, 4.5, 16, 11.5, 11.5), box(15, 4.5, 4.5, 16, 6, 11.5));
			case UP -> Shapes.or(box(5, 0, 6, 11, 4, 10), box(7, 3, 7, 9, 5, 9), box(5, 6, 6.5, 6.5, 7, 9.5), box(5.75, 6, 9.63934, 7.25, 7, 10.68934), box(6.5, 6, 9.5, 9.5, 7, 11), box(8.75, 6, 9.63934, 10.25, 7, 10.68934),
					box(9.18934, 6, 9.2, 10.68934, 7, 10.25), box(9.5, 6, 6.5, 11, 7, 9.5), box(9.2, 6, 5.31066, 10.25, 7, 6.81066), box(9.68934, 6, 5.75, 10.68934, 7, 7.25), box(6.5, 6, 5, 9.5, 7, 6.5), box(5.31066, 6, 5.75, 6.31066, 7, 7.25),
					box(6.5, 6, 6.5, 9.5, 7, 9.5), box(5.31066, 6, 9.2, 6.81066, 7, 10.25), box(5.75, 6, 5.31066, 6.8, 7, 6.81066), box(7.5, 4, 7.5, 8.5, 6, 8.5), box(4.5, 0, 6, 5, 1, 10), box(3.5, 0, 6, 4.5, 5, 10), box(6, 0, 11.5, 10, 5, 12.5),
					box(6, 0, 3.5, 10, 5, 4.5), box(10, 0, 3.5, 12.5, 6, 4.5), box(11.5, 0, 4.5, 12.5, 6, 6), box(11.5, 0, 10, 12.5, 6, 11.5), box(3.5, 0, 4.5, 4.5, 6, 6), box(3.5, 0, 10, 4.5, 6, 11.5), box(10, 0, 11.5, 12.5, 6, 12.5),
					box(3.5, 0, 11.5, 6, 6, 12.5), box(3.5, 0, 3.5, 6, 6, 4.5), box(11.5, 0, 6, 12.5, 5, 10), box(11, 0, 6, 11.5, 1, 10), box(4.5, 0, 10, 11.5, 1, 11.5), box(4.5, 0, 4.5, 11.5, 1, 6));
			case DOWN -> Shapes.or(box(5, 12, 6, 11, 16, 10), box(7, 11, 7, 9, 13, 9), box(5, 9, 6.5, 6.5, 10, 9.5), box(5.75, 9, 5.31066, 7.25, 10, 6.36066), box(6.5, 9, 5, 9.5, 10, 6.5), box(8.75, 9, 5.31066, 10.25, 10, 6.36066),
					box(9.18934, 9, 5.75, 10.68934, 10, 6.8), box(9.5, 9, 6.5, 11, 10, 9.5), box(9.2, 9, 9.18934, 10.25, 10, 10.68934), box(9.68934, 9, 8.75, 10.68934, 10, 10.25), box(6.5, 9, 9.5, 9.5, 10, 11),
					box(5.31066, 9, 8.75, 6.31066, 10, 10.25), box(6.5, 9, 6.5, 9.5, 10, 9.5), box(5.31066, 9, 5.75, 6.81066, 10, 6.8), box(5.75, 9, 9.18934, 6.8, 10, 10.68934), box(7.5, 10, 7.5, 8.5, 12, 8.5), box(4.5, 15, 6, 5, 16, 10),
					box(3.5, 11, 6, 4.5, 16, 10), box(6, 11, 3.5, 10, 16, 4.5), box(6, 11, 11.5, 10, 16, 12.5), box(10, 10, 11.5, 12.5, 16, 12.5), box(11.5, 10, 10, 12.5, 16, 11.5), box(11.5, 10, 4.5, 12.5, 16, 6), box(3.5, 10, 10, 4.5, 16, 11.5),
					box(3.5, 10, 4.5, 4.5, 16, 6), box(10, 10, 3.5, 12.5, 16, 4.5), box(3.5, 10, 3.5, 6, 16, 4.5), box(3.5, 10, 11.5, 6, 16, 12.5), box(11.5, 11, 6, 12.5, 16, 10), box(11, 15, 6, 11.5, 16, 10), box(4.5, 15, 4.5, 11.5, 16, 6),
					box(4.5, 15, 10, 11.5, 16, 11.5));
		};
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, POWERED, WATERLOGGED, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACING, context.getClickedFace()).setValue(POWERED, false).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public boolean isSignalSource(BlockState state) {
		return true;
	}

	@Override
	public int getSignal(BlockState blockstate, BlockGetter blockAccess, BlockPos pos, Direction direction) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		Level world = (Level) blockAccess;
		return (int) EStopRedstoneReversedProcedure.execute(blockstate);
	}

	@Override
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		EStopRightClickProcedure.execute(world, x, y, z, blockstate, entity);
		return InteractionResult.SUCCESS;
	}
}