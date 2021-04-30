package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemTierInit;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class ItemTitaniumAxe extends AxeItem {

	public ItemTitaniumAxe() {
		super(ItemTierInit.TITANIUM, 5.0f, -3f, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
