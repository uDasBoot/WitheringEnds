package com.udasboot.witheringends.init;

import com.udasboot.witheringends.WitheringEnds;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup extends ItemGroup {
	
	public static ModItemGroup INSTANCE = new ModItemGroup(ItemGroup.getGroupCountSafe(), WitheringEnds.MOD_ID);

	public ModItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.ENCASED_NETHER_STAR.get());
	}

}
