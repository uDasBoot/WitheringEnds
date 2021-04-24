package com.udasboot.witheringends.common.block;

import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class TitaniumOre extends WEOreBlock {

	public TitaniumOre() {
		super(OreBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(50f, 1200f)
				.harvestTool(ToolType.PICKAXE).sound(SoundType.STONE).harvestLevel(4).requiresCorrectToolForDrops());
	}

}
