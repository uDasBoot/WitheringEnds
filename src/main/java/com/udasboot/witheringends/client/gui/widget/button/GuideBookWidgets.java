package com.udasboot.witheringends.client.gui.widget.button;

import java.util.ArrayList;

import com.udasboot.witheringends.client.gui.screen.GuideBookScreen;

import net.minecraft.util.text.TranslationTextComponent;

public class GuideBookWidgets {
	public ArrayList<GuideBookPageButton> BUTTONS = new ArrayList<GuideBookPageButton>();
	private GuideBookScreen gui;
	private int size;
	
	public GuideBookWidgets(GuideBookScreen gui) {
		this.gui = gui;
	}
	
	public void init() {
		this.addButton("Welcome");
	}
	
	public void addButton(String text) {
		BUTTONS.add(new GuideBookPageButton(this.gui, ((-12*size) + 67), new TranslationTextComponent(text)));
		size++;
	}
	
	public int getSize() {
		return size;
	}
}
