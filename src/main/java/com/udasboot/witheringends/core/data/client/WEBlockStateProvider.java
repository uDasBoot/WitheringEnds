package com.udasboot.witheringends.core.data.client;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.core.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WEBlockStateProvider extends BlockStateProvider{

	public WEBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, WitheringEnds.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		simpleBlock(BlockInit.TITANIUM_ORE.get());
		simpleBlock(BlockInit.ALUMINUM_ORE.get());
		simpleBlock(BlockInit.TUNGSTEN_ORE.get());
	}

}
