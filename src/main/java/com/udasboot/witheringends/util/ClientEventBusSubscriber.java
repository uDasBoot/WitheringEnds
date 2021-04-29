package com.udasboot.witheringends.util;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.client.gui.screen.ArcFurnaceScreen;
import com.udasboot.witheringends.client.gui.screen.CrusherScreen;
import com.udasboot.witheringends.client.gui.screen.GeneratorScreen;
import com.udasboot.witheringends.client.gui.screen.InjectorScreen;
import com.udasboot.witheringends.client.renderer.AncientRenderer;
import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.ContainerTypeInit;
import com.udasboot.witheringends.init.EntityTypeInit;
import com.udasboot.witheringends.init.KeyBindInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WitheringEnds.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(final FMLClientSetupEvent event) {
		KeyBindInit.register(event);
		RenderTypeLookup.setRenderLayer(BlockInit.INJECTOR.get(), RenderType.translucent());
		ScreenManager.register(ContainerTypeInit.INJECTOR_CONTAINER_TYPE.get(), InjectorScreen::new);
		ScreenManager.register(ContainerTypeInit.CRUSHER_CONTAINER_TYPE.get(), CrusherScreen::new);
		ScreenManager.register(ContainerTypeInit.ARC_FURNACE_CONTAINER_TYPE.get(), ArcFurnaceScreen::new);
		ScreenManager.register(ContainerTypeInit.GENERATOR_CONTAINER_TYPE.get(), GeneratorScreen::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityTypeInit.ANCIENT_ENTITY.get(), AncientRenderer::new);
	}
	
}
