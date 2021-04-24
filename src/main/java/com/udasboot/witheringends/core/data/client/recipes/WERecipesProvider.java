package com.udasboot.witheringends.core.data.client.recipes;

import java.util.function.Consumer;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.core.init.BlockInit;
import com.udasboot.witheringends.core.init.ItemInit;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.util.ResourceLocation;

public class WERecipesProvider extends RecipeProvider{
	private static final int CRUSHING_ORE_TIME = 400;
	private static final int CRUSHING_INGOT_TIME = 200;

	public WERecipesProvider(DataGenerator gen) {
		super(gen);
	}
	
	@Override
	public String getName() {
		return "Withering Ends - Recipes";
	}
	
	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
		registerCrushingRecipes(consumer);
	}
	
	private void registerCrushingRecipes(Consumer<IFinishedRecipe> consumer) {
		CrushingRecipeBuilder.builder(BlockInit.TUNGSTEN_ORE.get(), CRUSHING_ORE_TIME).build(consumer);;
		CrushingRecipeBuilder.builder(ItemInit.TUNGSTEN_INGOT.get(), CRUSHING_INGOT_TIME).build(consumer, new ResourceLocation(WitheringEnds.MOD_ID, "crushing/tungsten_dust_from_ingot"));;
	}

}
