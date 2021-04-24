package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ItemGroupInit;
import com.udasboot.witheringends.core.init.ItemTierInit;

import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;

public class ItemTitaniumHoe extends HoeItem {

	public ItemTitaniumHoe() {
		super(ItemTierInit.TITANIUM, -10, 0.0f, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
