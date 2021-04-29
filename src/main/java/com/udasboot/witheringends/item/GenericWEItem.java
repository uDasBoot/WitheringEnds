package com.udasboot.witheringends.item;

import java.util.List;

import com.udasboot.witheringends.init.ItemGroupInit;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class GenericWEItem extends Item {

	public WEToolTips toolTip;

	public GenericWEItem(WEToolTips toolTip) {
		super(new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS));
		this.toolTip = toolTip;
	}

	public GenericWEItem() {
		this(WEToolTips.NONE);
	}
	
	public GenericWEItem(Item.Properties props, WEToolTips toolTip) {
		super(props);
		this.toolTip = toolTip;
	}

	public GenericWEItem(Item.Properties props) {
		this(props, WEToolTips.NONE);
	}

	@Override
	public void appendHoverText(ItemStack itemStack, World worldIn, List<ITextComponent> textComponents,
			ITooltipFlag tooltipFlag) {
		if (this.toolTip != WEToolTips.NONE && worldIn != null) {
			if (!Screen.hasShiftDown()) {
				textComponents.add(new TranslationTextComponent(
						"\u00A77Press <\u00A74" + "Shift" +"\u00A77> for more"));
			} else {
				textComponents.add(this.toolTip.getToolTip());
			}
		}
		super.appendHoverText(itemStack, worldIn, textComponents, tooltipFlag);
	}

}
