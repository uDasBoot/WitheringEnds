package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemGroupInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class ItemBossEmpoweredHelmet extends ArmorItem{

	public ItemBossEmpoweredHelmet() {
		super(ArmorMaterial.DIAMOND, EquipmentSlotType.HEAD, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
