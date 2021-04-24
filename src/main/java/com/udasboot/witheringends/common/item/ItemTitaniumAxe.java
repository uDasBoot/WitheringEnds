package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ItemGroupInit;
import com.udasboot.witheringends.core.init.ItemTierInit;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class ItemTitaniumAxe extends AxeItem {

	public ItemTitaniumAxe() {
		super(ItemTierInit.TITANIUM, 5.0f, -3f, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
