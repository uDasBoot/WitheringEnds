package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemTierInit;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class ItemTitaniumPickaxe extends PickaxeItem {

	public ItemTitaniumPickaxe() {
		super(ItemTierInit.TITANIUM, 1, -2.8f, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
