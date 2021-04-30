package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemTierInit;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;

public class ItemTitaniumSword extends SwordItem {

	public ItemTitaniumSword() {
		super(ItemTierInit.TITANIUM, 3, -2.4f, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
