package com.udasboot.witheringends.core.init;

import java.util.function.Supplier;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.common.item.crafting.recipe.CrusherRecipe;
import com.udasboot.witheringends.common.item.crafting.recipe.InjectorRecipe;
import com.udasboot.witheringends.core.init.util.Constants;
import com.udasboot.witheringends.core.init.util.Registration;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;

public class RecipeInit {
	public static class Types {
		public static final IRecipeType<InjectorRecipe> INJECTING = IRecipeType
				.register(WitheringEnds.MOD_ID + "injecting");
		public static final IRecipeType<CrusherRecipe> CRUSHING = registerType(Constants.modId("crushing"));
		
		private static <T extends IRecipe<?>> IRecipeType<T> registerType(ResourceLocation name) {
	        return Registry.register(Registry.RECIPE_TYPE, name, new IRecipeType<T>() {
	            @Override
	            public String toString() {
	                return name.toString();
	            }
	        });
	    }
	}

	public static class Serializers {
		public static final RegistryObject<IRecipeSerializer<?>> INJECTING = registerSerializer(Constants.INJECTING, InjectorRecipe.Serializer::new);

		public static final RegistryObject<IRecipeSerializer<?>> CRUSHING = registerSerializer(Constants.CRUSHING, CrusherRecipe.Serializer::new);
		
		private static RegistryObject<IRecipeSerializer<?>> registerSerializer(ResourceLocation name, Supplier<IRecipeSerializer<?>> serializer) {
	        return Registration.RECIPE_SERIALIZERS.register(name.getPath(), serializer);
	    }
	
	}
	

	public static void register() {

	}
}
