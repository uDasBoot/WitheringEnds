package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ArmorMaterialInit;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumBoots extends ArmorItem {

	public ItemTitaniumBoots() {
		super(ArmorMaterialInit.TITANIUM, EquipmentSlotType.FEET, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
