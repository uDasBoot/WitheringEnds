package com.udasboot.witheringends.block;

import com.udasboot.bootcore.block.AbstractMachineBlock;
import com.udasboot.bootcore.util.Constants;
import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.tileentity.InjectorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Injector extends AbstractMachineBlock {

	public static final BooleanProperty FULL = BooleanProperty.create("full");

	private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 20, 16);

	public Injector() {
		super(Constants.DEFAULT_PROPS.noOcclusion(), InjectorTileEntity.class);
		this.registerDefaultState(this.defaultBlockState().setValue(FULL, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos,
			ISelectionContext selectionContext) {
		return SHAPE;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext useContext) {
		return super.getStateForPlacement(useContext).setValue(FULL, false);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(FULL);
		super.createBlockStateDefinition(builder);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.INJECTOR_TILE_ENTITY.get().create();
	}

	@Override
	public void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntityClass.isInstance(tileEntity) && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (InjectorTileEntity) tileEntity, pos);
		}
	}

}
