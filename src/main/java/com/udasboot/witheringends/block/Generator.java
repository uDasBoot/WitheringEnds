package com.udasboot.witheringends.block;

import com.udasboot.dascore.block.AbstractGeneratorBlock;
import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.tileentity.GeneratorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class Generator extends AbstractGeneratorBlock {
	
	public static final BooleanProperty LIT = BooleanProperty.create("lit");
	
	public Generator() {
		super(GeneratorTileEntity.class);
		this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return TileEntityTypeInit.GENERATOR_TILE_ENTITY.get().create();
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext useContext) {
		return super.getStateForPlacement(useContext).setValue(LIT, false);
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(LIT);
		super.createBlockStateDefinition(builder);
	}

}
