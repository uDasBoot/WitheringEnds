package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemGroupInit;
import com.udasboot.witheringends.init.ItemTierInit;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class ItemTitaniumShovel extends ShovelItem {

	public ItemTitaniumShovel() {
		super(ItemTierInit.TITANIUM, 1.5f, -3f, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
