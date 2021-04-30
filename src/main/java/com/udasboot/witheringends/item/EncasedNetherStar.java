package com.udasboot.witheringends.item;

import com.udasboot.dascore.item.GenericModItem;
import com.udasboot.dascore.util.ToolTipsHandler;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.item.Item;

public class EncasedNetherStar extends GenericModItem {

	public EncasedNetherStar() {
		super(new Item.Properties().tab(ModItemGroup.INSTANCE),ToolTipsHandler.generateToolTip("A nether star encased in dragon's breath."));
	}

}
