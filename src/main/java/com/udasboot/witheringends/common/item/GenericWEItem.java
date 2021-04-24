package com.udasboot.witheringends.common.item;

import java.util.List;

import com.udasboot.witheringends.core.init.ItemGroupInit;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class GenericWEItem extends Item {

	public GenericWEItem() {
		super(new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS));
	}
	
	@Override
	public void appendHoverText(ItemStack p_77624_1_, World p_77624_2_, List<ITextComponent> p_77624_3_,
			ITooltipFlag p_77624_4_) {
		p_77624_3_.add(new TranslationTextComponent("Press Shift for more"));
		super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	}

}
