package com.udasboot.witheringends.block;

import com.udasboot.dascore.block.AbstractMachineBlock;
import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.tileentity.ArcFurnaceTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ArcFurnace extends AbstractMachineBlock {

	public static final BooleanProperty LIT = BooleanProperty.create("lit");

	public ArcFurnace() {
		super(ArcFurnaceTileEntity.class);
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.ARC_FURNACE_TILE_ENTITY.get().create();
	}

	@Override
	public void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntityClass.isInstance(tileEntity) && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (ArcFurnaceTileEntity) tileEntity, pos);
		}
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(LIT);
		super.createBlockStateDefinition(builder);
	}

}
