package com.udasboot.witheringends.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.udasboot.dascore.client.gui.screen.AbstractMachineScreen;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.CrusherContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrusherScreen extends AbstractMachineScreen<CrusherContainer> {

	private static final ResourceLocation CRUSHER_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/crusher_machine.png");

	public CrusherScreen(CrusherContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, CRUSHER_GUI);
	}

	@Override
	protected void renderExtras(MatrixStack matrixStack, float i, int mouseX, int mouseY) {
		int crushProgress = (int) (24.0 * this.menu.getProgressPercent());
	    this.blit(matrixStack, x + 79, y + 34, 190, 0, crushProgress, 16);
	}

}
