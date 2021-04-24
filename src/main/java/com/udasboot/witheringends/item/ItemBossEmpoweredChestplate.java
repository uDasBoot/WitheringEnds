package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ItemGroupInit;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class ItemBossEmpoweredChestplate extends ArmorItem{

	public ItemBossEmpoweredChestplate() {
		super(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(1));
	}

}
