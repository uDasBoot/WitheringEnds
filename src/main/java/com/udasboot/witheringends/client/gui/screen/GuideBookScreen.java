package com.udasboot.witheringends.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.client.gui.widget.button.GuideBookPageButton;
import com.udasboot.witheringends.client.gui.widget.button.GuideBookWidgets;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class GuideBookScreen extends Screen{

	private static final ResourceLocation GUIDE_BOOK_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/guide_book.png");
	private int imgWidth;
	private int imgHeight;
	private GuideBookWidgets gbWidgets;

	public GuideBookScreen() {
		super(new TranslationTextComponent("screen.witheringends.guide_book"));
		this.imgWidth = 185;
		this.imgHeight = 182;
		gbWidgets = new GuideBookWidgets(this);
	}
	
	@Override
	protected void init() {
		super.init();
		gbWidgets.init();
		gbWidgets.addButton("Blocks");
		gbWidgets.addButton("Items");
		gbWidgets.addButton("Tools");
		gbWidgets.addButton("Machines");
		for(GuideBookPageButton button : gbWidgets.BUTTONS) {
			this.addButton(button);
		}
	}
	
	@Override
	public void render(MatrixStack matrixStack, int p_230430_2_, int p_230430_3_, float p_230430_4_) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, p_230430_2_, p_230430_3_, p_230430_4_);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void renderBackground(MatrixStack matrixStack) {
		super.renderBackground(matrixStack);
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(GUIDE_BOOK_GUI);
		int x = (this.width - this.imgWidth) / 2;
		int y = (this.height - this.imgHeight) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.imgWidth, this.imgHeight);
	}

}
