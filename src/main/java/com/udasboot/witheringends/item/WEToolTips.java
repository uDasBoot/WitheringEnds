package com.udasboot.witheringends.item;

import net.minecraft.util.text.TranslationTextComponent;

public enum WEToolTips {
	
	NONE(toolTipCreator("NONE")),
	ENCASED_NETHER_STAR(toolTipCreator("A nether star encased in dragon's breath.")),
	ENCASEMENT(toolTipCreator("A glass encasement meant for holding nether stars.")),
	TITANIUM(toolTipCreator("A very robust resource, useful for armor and tools.")),
	ALUMINUM(toolTipCreator("A very soft, but useful resource.")),
	TUNGSTEN(toolTipCreator("One of the strongest elements, useful for making tools.")),
	GUIDE_BOOK(toolTipCreator("A guide to the Withering Ends mod."))
	;
	
	private TranslationTextComponent toolTip;
	
	WEToolTips(TranslationTextComponent toolTip){
		this.toolTip = toolTip;
	}
	
	public TranslationTextComponent getToolTip() {
		return this.toolTip;
	}
	
	private static TranslationTextComponent toolTipCreator(String toolTip) {
		return new TranslationTextComponent("\u00A77" + toolTip);
	}
}
