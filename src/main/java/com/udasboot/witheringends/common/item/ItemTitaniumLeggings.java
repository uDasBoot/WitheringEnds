package com.udasboot.witheringends.common.item;

import com.udasboot.witheringends.core.init.ArmorMaterialInit;
import com.udasboot.witheringends.core.init.ItemGroupInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;

public class ItemTitaniumLeggings extends ArmorItem{

	public ItemTitaniumLeggings() {
		super(ArmorMaterialInit.TITANIUM, EquipmentSlotType.LEGS, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
