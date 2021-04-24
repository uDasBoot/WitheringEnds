package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ItemGroupInit;
import com.udasboot.witheringends.core.init.ItemTierInit;

import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class ItemTitaniumShovel extends ShovelItem {

	public ItemTitaniumShovel() {
		super(ItemTierInit.TITANIUM, 1.5f, -3f, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
