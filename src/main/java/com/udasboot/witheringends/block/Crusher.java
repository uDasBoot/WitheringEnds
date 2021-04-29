package com.udasboot.witheringends.block;

import com.udasboot.bootcore.block.AbstractMachineBlock;
import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.tileentity.CrusherTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Crusher extends AbstractMachineBlock {


	public Crusher() {
		super(CrusherTileEntity.class);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.CRUSHER_TILE_ENTITY.get().create();
	}

	@Override
	public void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntityClass.isInstance(tileEntity) && player instanceof ServerPlayerEntity) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (CrusherTileEntity) tileEntity, pos);
		}
	}
}
