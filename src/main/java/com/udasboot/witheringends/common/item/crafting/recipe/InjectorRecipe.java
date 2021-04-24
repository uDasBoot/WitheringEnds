package com.udasboot.witheringends.common.item.crafting.recipe;

import com.google.gson.JsonObject;
import com.udasboot.witheringends.common.tileentity.InjectorTileEntity;
import com.udasboot.witheringends.core.init.RecipeInit;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class InjectorRecipe implements IRecipe<InjectorTileEntity> {
	
	protected Ingredient ingredient1;
	protected Ingredient ingredient2;
	protected ItemStack result;
	protected ResourceLocation recipeId;
	protected int breathUsage;
	
	protected IRecipeType<?> recipeType;
	protected IRecipeSerializer<?> recipeSerializer;

	public InjectorRecipe(ResourceLocation recipeId, Ingredient ingrediant1, Ingredient ingredient2, ItemStack result, int breathUsage) {
		this.ingredient1 = ingrediant1;
		this.ingredient2 = ingredient2;
		this.result = result;
		this.recipeId = recipeId;
		this.breathUsage = breathUsage;
		this.recipeType = RecipeInit.Types.INJECTING;
		this.recipeSerializer = RecipeInit.Serializers.INJECTING.get();
	}

	@Override
	public boolean matches(InjectorTileEntity te, World worldIn) {
		return this.ingredient1.test(te.getItem(0)) && this.ingredient2.test(te.getItem(1));
	}

	@Override
	public ItemStack assemble(InjectorTileEntity tileEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int i, int j) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ItemStack getResultItem() {
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public ResourceLocation getId() {
		// TODO Auto-generated method stub
		return recipeId;
	}

	@Override
	public IRecipeSerializer<?> getSerializer() {
		// TODO Auto-generated method stub
		return this.recipeSerializer;
	}

	@Override
	public IRecipeType<?> getType() {
		// TODO Auto-generated method stub
		return this.recipeType;
	}
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<InjectorRecipe>{

		@Override
		public InjectorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			Ingredient ingredient1 = Ingredient.fromJson(json.get("ingredient1"));
			Ingredient ingredient2 = Ingredient.fromJson(json.get("ingredient2"));
			ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(json, "result"));
			int count = JSONUtils.getAsInt(json, "count", 1);
			int breathUsage = JSONUtils.getAsInt(json, "breathUsage", 1000);
			ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);
			return new InjectorRecipe(recipeId, ingredient1, ingredient2, result, breathUsage);
		}

		@Override
		public InjectorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
			Ingredient ingredient2 = Ingredient.fromNetwork(buffer);
			ItemStack result = buffer.readItem();
			int breathUsage = buffer.readVarInt();
			return new InjectorRecipe(recipeId, ingredient1, ingredient2, result, breathUsage);
		}

		@Override
		public void toNetwork(PacketBuffer buffer, InjectorRecipe recipe) {
			recipe.ingredient1.toNetwork(buffer);
			recipe.ingredient2.toNetwork(buffer);
			buffer.writeItem(recipe.result);
			buffer.writeVarInt(recipe.breathUsage);
		}
		
	}

}
