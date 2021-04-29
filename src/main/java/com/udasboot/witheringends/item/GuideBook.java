package com.udasboot.witheringends.item;

import com.udasboot.witheringends.client.gui.screen.GuideBookScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GuideBook extends GenericWEItem{

	public GuideBook() {
		super(WEToolTips.GUIDE_BOOK);
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		if(player instanceof ServerPlayerEntity) {
			Minecraft.getInstance().setScreen(new GuideBookScreen());
		}
		return super.use(worldIn, player, hand);
	}
	
}
