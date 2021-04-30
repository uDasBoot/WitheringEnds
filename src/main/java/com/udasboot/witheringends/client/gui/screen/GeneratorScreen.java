package com.udasboot.witheringends.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.udasboot.dascore.client.gui.screen.AbstractGeneratorScreen;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.GeneratorContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GeneratorScreen extends AbstractGeneratorScreen<GeneratorContainer> {

	private static final ResourceLocation GENERATOR_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/generator_machine.png");

	public GeneratorScreen(GeneratorContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, GENERATOR_GUI);
	}

	@Override
	protected void renderExtras(MatrixStack matrixStack, float i, int mouseX, int mouseY) {
		if(this.menu.isGenerating()) {
			int fuelHeight = (int) (14.0 * this.menu.getProgressPercent());
	    	this.blit(matrixStack, x + 81, (y + 50) - (14 - fuelHeight), 190, fuelHeight, 14, 14 - fuelHeight);
	    }
	}
}
