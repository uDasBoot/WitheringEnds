package com.udasboot.witheringends.init;

import com.udasboot.witheringends.WitheringEnds;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class KeyBindInit {
	
	public static void register(FMLClientSetupEvent event) {
	}
	
	private static KeyBinding create(String name, int key) {
		return new KeyBinding("key." + WitheringEnds.MOD_ID + "." + name, key, "key.category." + WitheringEnds.MOD_ID);
	}
}
