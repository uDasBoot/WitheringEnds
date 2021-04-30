package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ArmorMaterialInit;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumLeggings extends ArmorItem {

	public ItemTitaniumLeggings() {
		super(ArmorMaterialInit.TITANIUM, EquipmentSlotType.LEGS, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
