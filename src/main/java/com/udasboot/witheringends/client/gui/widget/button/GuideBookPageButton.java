package com.udasboot.witheringends.client.gui.widget.button;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.udasboot.witheringends.client.gui.screen.GuideBookScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuideBookPageButton extends Button {

	public GuideBookPageButton(final Screen gui, final int y, TranslationTextComponent text) {
		super(gui.width / 2 - 60, gui.height / 2 - y - 6, 120, 12, text, button -> Minecraft.getInstance().setScreen(new GuideBookScreen()));
	}
	
	@Override
	public void renderButton(MatrixStack matrixStack, int mouseX, int mouseY, float p_230431_4_) {
//		super.renderButton(matrixStack, mouseX, mouseY, p_230431_4_);
		Minecraft mc = Minecraft.getInstance();
		FontRenderer font = mc.font;
		int j = getFGColor();
//		drawCenteredString(p_230431_1_, font, this.getMessage(), this.x, this.y, j | MathHelper.ceil(this.alpha * 255.0F) << 24);
		drawString(matrixStack, font, getMessage(), this.x, this.y, j);
	}
	
	 @Override
	public boolean isMouseOver(double p_231047_1_, double p_231047_3_) {
		// TODO Auto-generated method stub
		return super.isMouseOver(p_231047_1_, p_231047_3_);
	}
}
