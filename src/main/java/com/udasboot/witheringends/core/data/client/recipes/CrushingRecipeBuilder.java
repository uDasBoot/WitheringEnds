package com.udasboot.witheringends.core.data.client.recipes;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.core.init.RecipeInit;
import com.udasboot.witheringends.core.init.util.NameUtils;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

public class CrushingRecipeBuilder {
	private ItemStack result = ItemStack.EMPTY;
	private final Ingredient ingredient;
	private final int processTime;
	
	private CrushingRecipeBuilder(Ingredient ingredient, int processTime) {
		this.ingredient = ingredient;
		this.processTime = processTime;
	}
	
	public static CrushingRecipeBuilder builder (IItemProvider ingredient, int processTime) {
		return builder(Ingredient.of(ingredient), processTime);
	}
	
	public static CrushingRecipeBuilder builder (ITag<Item> ingredient, int processTime) {
		return builder(Ingredient.of(ingredient), processTime);
	}
	
	public static CrushingRecipeBuilder builder (Ingredient ingredient, int processTime) {
		return new CrushingRecipeBuilder(ingredient, processTime);
	}
	
	public static CrushingRecipeBuilder crushingIngot(ITag<Item> ingot, IItemProvider dust, int processTime) {
		return builder(ingot, processTime).result(dust, 1);
	}
	
	public static CrushingRecipeBuilder crushingOre(ITag<Item> ore, IItemProvider dust, int processTime) {
		return builder(ore, processTime).result(dust, 2);
	}
	
	public static CrushingRecipeBuilder crushingOre(IItemProvider ore, IItemProvider dust, int processTime) {
		return builder(ore, processTime).result(dust, 2);
	}
	
	public CrushingRecipeBuilder result(IItemProvider item, int count) {
		result = new ItemStack(item, count);
		return this;
	}
	
	public void build(Consumer<IFinishedRecipe> consumer) {
		ResourceLocation resultId = NameUtils.fromItem(result);
		ResourceLocation id = new ResourceLocation("minecraft".equals(resultId.getNamespace()) ? WitheringEnds.MOD_ID : resultId.getNamespace(), "crushing/" + resultId.getPath());
		build(consumer, id);
	}
	
	public void build(Consumer<IFinishedRecipe> consumer, ResourceLocation id) {
		consumer.accept(new Result(id, this));
	}
	
	public class Result implements IFinishedRecipe{
		
		private final ResourceLocation id;
		private final CrushingRecipeBuilder builder;
		
		public Result(ResourceLocation id, CrushingRecipeBuilder builder) {
			this.id = id;
			this.builder = builder;
		}

		@Override
		public void serializeRecipeData(JsonObject json) {
			json.addProperty("processTime", builder.processTime);
			json.add("ingredient", builder.ingredient.toJson());
			
			json.add("result", serializeResult(result));
		}

		private JsonObject serializeResult(ItemStack stack) {
			JsonObject json = new JsonObject();
			json.addProperty("item", NameUtils.fromItem(stack).toString());
			if(stack.getCount() > 1) {
				json.addProperty("count", stack.getCount());
			}
			return json;
		}

		@Override
		public ResourceLocation getId() {
			return id;
		}

		@Override
		public IRecipeSerializer<?> getType() {
			return RecipeInit.Serializers.CRUSHING.get();
		}

		@Override
		public JsonObject serializeAdvancement() {
			return null;
		}

		@Override
		public ResourceLocation getAdvancementId() {
			return null;
		}
		
	}
}
