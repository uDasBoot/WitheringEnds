package com.udasboot.witheringends.item.crafting.recipe;

import java.util.Objects;

import com.google.gson.JsonObject;
import com.udasboot.witheringends.tileentity.CrusherTileEntity;

import net.minecraft.item.Item;
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

public class CrusherRecipe implements IRecipe<CrusherTileEntity> {

	protected Ingredient ingredient;
	protected ItemStack result;
	protected ResourceLocation recipeId;
	protected int crushingTime;

	protected IRecipeType<?> recipeType;
	protected IRecipeSerializer<?> recipeSerializer;

	public CrusherRecipe(ResourceLocation recipeId) {
		this.recipeId = recipeId;
	}

	@Override
	public boolean matches(CrusherTileEntity te, World worldIn) {
		return ingredient.test(te.getItem(0));
	}

	@Override
	public ItemStack assemble(CrusherTileEntity tileEntity) {
		return this.result.copy();
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

	public int getCrushingTime() {
		return this.crushingTime;
	}

	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<CrusherRecipe> {

		@Override
		public CrusherRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			CrusherRecipe recipe = new CrusherRecipe(recipeId);
			recipe.crushingTime = JSONUtils.getAsInt(json, "process_time", 400);
			recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));
			JsonObject result = json.get("result").getAsJsonObject();
			String itemId = JSONUtils.getAsString(result, "name");
			Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemId));
			int count = JSONUtils.getAsInt(result, "count", 1);
			ItemStack stack = new ItemStack(item, count);
			recipe.result = stack;
			return recipe;
		}

		@Override
		public CrusherRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			CrusherRecipe recipe = new CrusherRecipe(recipeId);
			recipe.crushingTime = buffer.readVarInt();
			recipe.ingredient = Ingredient.fromNetwork(buffer);
			ResourceLocation itemId = buffer.readResourceLocation();
			int count = buffer.readVarInt();
			Item item = ForgeRegistries.ITEMS.getValue(itemId);
			recipe.result = new ItemStack(item, count);
			return recipe;
		}

		@Override
		public void toNetwork(PacketBuffer buffer, CrusherRecipe recipe) {
			buffer.writeVarInt(recipe.crushingTime);
			recipe.ingredient.toNetwork(buffer);
			ItemStack stack = recipe.getResultItem();
			buffer.writeResourceLocation(Objects.requireNonNull(stack.getItem().getRegistryName()));
			buffer.writeVarInt(stack.getCount());
		}
	}

}
