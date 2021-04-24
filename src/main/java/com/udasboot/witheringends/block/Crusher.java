package com.udasboot.witheringends.block;

import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.tileentity.CrusherTileEntity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public class Crusher extends Block {

	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

	public Crusher() {
		super(AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_GRAY).strength(50f, 1200f)
				.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).noOcclusion().harvestLevel(4)
				.requiresCorrectToolForDrops());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	@Override
	public BlockState rotate(BlockState state, IWorld world, BlockPos pos, Rotation direction) {
		return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext useContext) {
		// TODO Auto-generated method stub
		return this.defaultBlockState().setValue(FACING,
				useContext.getHorizontalDirection().getOpposite());
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FACING);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		}
		this.interactWith(worldIn, pos, player);
		return ActionResultType.CONSUME;
	}

	private void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntity instanceof CrusherTileEntity && player instanceof ServerPlayerEntity) {
			CrusherTileEntity te = (CrusherTileEntity) tileEntity;
			NetworkHooks.openGui((ServerPlayerEntity) player, te, pos);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (!state.is(newState.getBlock())) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof IInventory) {
				InventoryHelper.dropContents(worldIn, pos, (IInventory) tileentity);
				worldIn.updateNeighbourForOutputSignal(pos, this);
			}

			super.onRemove(state, worldIn, pos, newState, isMoving);
		}
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		// TODO Auto-generated method stub
		return TileEntityTypeInit.CRUSHER_TILE_ENTITY.get().create();
	}

}
