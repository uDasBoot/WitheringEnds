package com.udasboot.witheringends.data;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.data.client.WEBlockStateProvider;
import com.udasboot.witheringends.data.client.WEItemModelProvider;
import com.udasboot.witheringends.data.client.loot.WELootTables;
import com.udasboot.witheringends.data.client.recipes.WERecipesProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = WitheringEnds.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
//		gen.addProvider(new WERecipesProvider(gen));
		gen.addProvider(new WEItemModelProvider(gen, existingFileHelper));
		gen.addProvider(new WEBlockStateProvider(gen, existingFileHelper));
		gen.addProvider(new WELootTables(gen));
	}
}
