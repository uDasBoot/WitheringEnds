package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ArmorMaterialInit;
import com.udasboot.witheringends.init.ItemGroupInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumChestplate extends ArmorItem{

	public ItemTitaniumChestplate() {
		super(ArmorMaterialInit.TITANIUM, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
