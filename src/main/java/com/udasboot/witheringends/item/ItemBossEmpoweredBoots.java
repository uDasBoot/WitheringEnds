package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemGroupInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class ItemBossEmpoweredBoots extends ArmorItem{

	public ItemBossEmpoweredBoots() {
		super(ArmorMaterial.DIAMOND, EquipmentSlotType.FEET, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
