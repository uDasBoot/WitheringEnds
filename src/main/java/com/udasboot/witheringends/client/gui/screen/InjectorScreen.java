package com.udasboot.witheringends.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.udasboot.dascore.client.gui.screen.AbstractMachineScreen;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.InjectorContainer;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InjectorScreen extends AbstractMachineScreen<InjectorContainer> {

	private static final ResourceLocation INJECTOR_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/injector_machine.png");

	public InjectorScreen(InjectorContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title, INJECTOR_GUI);
	}
	
	@Override
	protected void renderExtras(MatrixStack matrixStack, float i, int mouseX, int mouseY) {
		int fuelHeight = (int) (70.0 * this.menu.getBreathPercent());
		this.blit(matrixStack, x + 136, (y + 8) + (70 - fuelHeight), 176, 70 - fuelHeight, 14, fuelHeight);
		int injectProgress = (int) (17.0 * this.menu.getProgressPercent());
		this.blit(matrixStack, x + 71, y + 35, 204, 0, injectProgress, 16);
		int energyHeight = (int) (70.0 * this.menu.getEnergyPercent());
		this.blit(matrixStack, x + 154, (y + 8) + (70 - energyHeight), 190, 70 - energyHeight, 14, energyHeight);
		if (this.isOverBreathBar(mouseX, mouseY)) {
			TranslationTextComponent toolTip = new TranslationTextComponent("Breath: ");
			toolTip.append(this.menu.getBreath() + "/" + this.menu.getMaxBreath());
			this.renderTooltip(matrixStack, toolTip, mouseX, mouseY);
		}
	}

	public boolean isOverBreathBar(double mouseX, double mouseY) {
		return mouseX >= (double) this.x + 136 && mouseY >= (double) this.y + 8 && mouseX < (double) (this.x + 150)
				&& mouseY < (double) (this.y + 78);
	}

}
