package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ArmorMaterialInit;
import com.udasboot.witheringends.init.ItemGroupInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumBoots extends ArmorItem{

	public ItemTitaniumBoots() {
		super(ArmorMaterialInit.TITANIUM, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
