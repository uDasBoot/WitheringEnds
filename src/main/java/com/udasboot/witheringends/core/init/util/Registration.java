package com.udasboot.witheringends.core.init.util;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.core.init.BlockInit;
import com.udasboot.witheringends.core.init.ContainerTypeInit;
import com.udasboot.witheringends.core.init.ItemInit;
import com.udasboot.witheringends.core.init.RecipeInit;
import com.udasboot.witheringends.core.init.TileEntityTypeInit;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registration {
	public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);

	public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = create(ForgeRegistries.CONTAINERS);
	
	public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = create(ForgeRegistries.RECIPE_SERIALIZERS);

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = create(ForgeRegistries.TILE_ENTITIES);

	public static void register() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		BLOCKS.register(modEventBus);
		ITEMS.register(modEventBus);
		CONTAINER_TYPES.register(modEventBus);
		TILE_ENTITY_TYPES.register(modEventBus);
		
		BlockInit.register();
		ItemInit.register();
		ContainerTypeInit.register();
		TileEntityTypeInit.register();
		RecipeInit.register();
	}
	
	private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry){
		return DeferredRegister.create(registry, WitheringEnds.MOD_ID);
	}
}
