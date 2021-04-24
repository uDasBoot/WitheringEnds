package com.udasboot.witheringends;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.udasboot.witheringends.init.FeatureInit;
import com.udasboot.witheringends.init.Registration;
import com.udasboot.witheringends.network.WitheringEndsNetwork;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("witheringends")
@Mod.EventBusSubscriber(modid = WitheringEnds.MOD_ID, bus = Bus.MOD)
public class WitheringEnds {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "witheringends";

	public WitheringEnds() {		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::commonSetup);
		Registration.register();
		
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void commonSetup(final FMLCommonSetupEvent event) {
		WitheringEndsNetwork.init();
	}
}
