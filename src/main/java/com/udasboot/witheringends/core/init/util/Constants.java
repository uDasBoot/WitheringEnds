package com.udasboot.witheringends.core.init.util;

import com.udasboot.witheringends.WitheringEnds;

import net.minecraft.util.ResourceLocation;

public class Constants {
	
	public static final ResourceLocation CRUSHING = modId("crushing");
	public static final ResourceLocation INJECTING = modId("injecting");
	
	public static ResourceLocation modId(String path) {
		return new ResourceLocation(WitheringEnds.MOD_ID, path);
	}
}
