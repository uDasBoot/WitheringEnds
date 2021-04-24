package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ItemGroupInit;
import com.udasboot.witheringends.core.init.ItemTierInit;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class ItemTitaniumSword extends SwordItem {

	public ItemTitaniumSword() {
		super(ItemTierInit.TITANIUM, 3, -2.4f, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
