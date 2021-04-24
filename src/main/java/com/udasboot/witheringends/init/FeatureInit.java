package com.udasboot.witheringends.init;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureInit {

	public static void addOres(BiomeLoadingEvent event) {
		addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TITANIUM_ORE.get().defaultBlockState(),
				10, 0, 60, 20);
		addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.ALUMINUM_ORE.get().defaultBlockState(),
				10, 30, 70, 20);
		addOre(event, OreFeatureConfig.FillerBlockType.NATURAL_STONE, BlockInit.TUNGSTEN_ORE.get().defaultBlockState(),
				10, 0, 20, 20);
	}

	public static void addOre(BiomeLoadingEvent event, RuleTest rule, BlockState state, int veinSize, int minHeight,
			int maxHeight, int amount) {
		event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.configured(new OreFeatureConfig(rule, state, veinSize))
						.decorated(Placement.RANGE.configured(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
						.squared().count(amount));
	}
}
