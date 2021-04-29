package com.udasboot.witheringends.client.gui.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.InjectorContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InjectorScreen extends ContainerScreen<InjectorContainer> {

	private static final ResourceLocation INJECTOR_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/injector_machine.png");

	private int x;
	private int y;

	public InjectorScreen(InjectorContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 175;
		this.imageHeight = 166;
	}

	@Override
	protected void init() {
		super.init();
		this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
	}

	@Override
	public void render(MatrixStack matrixStack, int i, int j, float k) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, i, j, k);
		this.renderTooltip(matrixStack, i, j);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack matrixStack, float i, int mouseX, int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(INJECTOR_GUI);
		this.x = (this.width - this.getXSize()) / 2;
		this.y = (this.height - this.getYSize()) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
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
		if (this.isOverEnergyBar(mouseX, mouseY)) {
			TranslationTextComponent toolTip = new TranslationTextComponent("Energy: ");
			toolTip.append(this.menu.getEnergy() + "/" + this.menu.getMaxEnergy());
			this.renderTooltip(matrixStack, toolTip, mouseX, mouseY);
		}
	}

	public boolean isOverBreathBar(double mouseX, double mouseY) {
		return mouseX >= (double) this.x + 136 && mouseY >= (double) this.y + 8 && mouseX < (double) (this.x + 150)
				&& mouseY < (double) (this.y + 78);
	}
	
	public boolean isOverEnergyBar(double mouseX, double mouseY) {
		return mouseX >= (double) this.x + 154 && mouseY >= (double) this.y + 8 && mouseX < (double) (this.x + 168)
				&& mouseY < (double) (this.y + 78);
	}

}
