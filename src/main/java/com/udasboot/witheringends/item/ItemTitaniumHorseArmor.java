package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumHorseArmor extends HorseArmorItem {

	public ItemTitaniumHorseArmor() {
		super(15, "titanium", new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
