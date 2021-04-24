package com.udasboot.witheringends.data.client.loot;

import java.util.stream.Collectors;

import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.Registration;

import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraftforge.fml.RegistryObject;

public class WEBlockLootTables extends BlockLootTables{
	
	@Override
	protected void addTables() {
		this.dropSelf(BlockInit.ALUMINUM_ORE.get());
		this.dropSelf(BlockInit.ARC_FURNACE.get());
		this.dropSelf(BlockInit.CRUSHER.get());
		this.dropSelf(BlockInit.INJECTOR.get());
		this.dropSelf(BlockInit.TITANIUM_ORE.get());
		this.dropSelf(BlockInit.TUNGSTEN_ORE.get());
	}
	
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList());
	}

}
