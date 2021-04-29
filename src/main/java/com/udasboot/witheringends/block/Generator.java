package com.udasboot.witheringends.block;

import com.udasboot.bootcore.block.AbstractMachineBlock;
import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.tileentity.GeneratorTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class Generator extends AbstractMachineBlock {
	public Generator() {
		super(GeneratorTileEntity.class);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.GENERATOR_TILE_ENTITY.get().create();
	}

	@Override
	public void interactWith(World worldIn, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = worldIn.getBlockEntity(pos);
		if (tileEntityClass.isInstance(tileEntity) && player instanceof ServerPlayerEntity) {
			if (player != null) {
				NetworkHooks.openGui((ServerPlayerEntity) player, (GeneratorTileEntity) tileEntity, pos);
			}
		}
	}

}
