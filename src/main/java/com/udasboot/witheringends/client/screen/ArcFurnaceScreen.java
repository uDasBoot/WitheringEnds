package com.udasboot.witheringends.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.ArcFurnaceContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ArcFurnaceScreen extends ContainerScreen<ArcFurnaceContainer> {

	private static final ResourceLocation ARC_FURNACE_GUI = new ResourceLocation(WitheringEnds.MOD_ID,
			"textures/gui/arc_furnace_machine.png");

	public ArcFurnaceScreen(ArcFurnaceContainer container, PlayerInventory playerInventory, ITextComponent title) {
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
		this.minecraft.textureManager.bind(ARC_FURNACE_GUI);
		int x = (this.width - this.getXSize()) / 2;
		int y = (this.height - this.getYSize()) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.getXSize(), this.getYSize());
		int crushProgress = (int) (24.0 * this.menu.getArcProgress());
	    this.blit(matrixStack, x + 79, y + 34, 176, 14, crushProgress, 16);
	    if(this.menu.isLit()) {
	    	this.blit(matrixStack, x + 56, y + 36, 176, 0, 14, 14);
	    }
	}

}
