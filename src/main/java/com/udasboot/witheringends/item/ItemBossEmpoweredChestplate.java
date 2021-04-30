package com.udasboot.witheringends.item;

import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;

public class ItemBossEmpoweredChestplate extends ArmorItem {

	public ItemBossEmpoweredChestplate() {
		super(ArmorMaterial.DIAMOND, EquipmentSlotType.CHEST, new Item.Properties().tab(ModItemGroup.INSTANCE));
	}

}
