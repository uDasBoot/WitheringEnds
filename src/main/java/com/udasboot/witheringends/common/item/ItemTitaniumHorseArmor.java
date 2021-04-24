package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ItemGroupInit;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumHorseArmor extends HorseArmorItem {

	public ItemTitaniumHorseArmor() {
		super(15, "titanium", new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
