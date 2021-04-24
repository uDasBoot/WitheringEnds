package com.udasboot.witheringends.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.InjectorContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class InjectorScreen extends ContainerScreen<InjectorContainer> {

	private static final ResourceLocation INJECTOR_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/injector_machine.png");

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
	protected void renderBg(MatrixStack matrixStack, float i, int j, int k) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(INJECTOR_GUI);
		int x = (this.width - this.getXSize()) / 2;
		int y = (this.height - this.getYSize()) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
		int fuelHeight = (int) (70.0 * this.menu.getFuelLevel());
		this.blit(matrixStack, x+152, (y+8) + (70 - fuelHeight), 176, 70 - fuelHeight, 16, fuelHeight);
		int injectProgress = (int) (16.0 * this.menu.getInjectingProgress());
		this.blit(matrixStack, x+77, y+35, 192, 0, injectProgress, 16);
	}

}
