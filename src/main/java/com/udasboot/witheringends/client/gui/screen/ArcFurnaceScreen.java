package com.udasboot.witheringends.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.udasboot.dascore.client.gui.screen.AbstractMachineScreen;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.ArcFurnaceContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArcFurnaceScreen extends AbstractMachineScreen<ArcFurnaceContainer> {

	private static final ResourceLocation ARC_FURNACE_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/arc_furnace_machine.png");

	public ArcFurnaceScreen(ArcFurnaceContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, ARC_FURNACE_GUI);
	}

	@Override
	protected void renderExtras(MatrixStack matrixStack, float i, int mouseX, int mouseY) {
		int arcProgress = (int) (24.0 * this.menu.getProgressPercent());
		this.blit(matrixStack, x + 79, y + 34, 190, 14, arcProgress, 16);
		if (this.menu.isArcing()) {
			this.blit(matrixStack, x + 56, y + 36, 190, 0, 14, 14);
		}
	}
}
