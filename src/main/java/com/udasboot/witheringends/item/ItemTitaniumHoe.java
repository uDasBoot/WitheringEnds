package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemTierInit;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;

public class ItemTitaniumHoe extends HoeItem {

	public ItemTitaniumHoe() {
		super(ItemTierInit.TITANIUM, -10, 0.0f, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
