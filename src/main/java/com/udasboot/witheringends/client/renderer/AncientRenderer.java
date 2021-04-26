package com.udasboot.witheringends.client.renderer;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.client.model.AncientModel;
import com.udasboot.witheringends.entities.AncientEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AncientRenderer extends MobRenderer<AncientEntity, AncientModel<AncientEntity>>{
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(WitheringEnds.MOD_ID, "textures/entity/ancient.png");

	public AncientRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AncientModel<>(), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(AncientEntity p_110775_1_) {
		return TEXTURE;
	}
	
}
