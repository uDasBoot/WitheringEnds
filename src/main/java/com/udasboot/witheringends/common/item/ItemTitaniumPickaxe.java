package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ItemGroupInit;
import com.udasboot.witheringends.core.init.ItemTierInit;

import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;

public class ItemTitaniumPickaxe extends PickaxeItem {

	public ItemTitaniumPickaxe() {
		super(ItemTierInit.TITANIUM, 1, -2.8f, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
