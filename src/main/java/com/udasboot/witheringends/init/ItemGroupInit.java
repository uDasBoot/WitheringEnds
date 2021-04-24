package com.udasboot.witheringends.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupInit extends ItemGroup {
	
	public static final ItemGroupInit WITHERING_ENDS = new ItemGroupInit(ItemGroup.getGroupCountSafe(), "withering_ends");

	public ItemGroupInit(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.ENCASED_NETHER_STAR.get());
	}

}
