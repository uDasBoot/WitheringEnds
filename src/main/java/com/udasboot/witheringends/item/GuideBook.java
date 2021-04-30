package com.udasboot.witheringends.item;

import com.udasboot.dascore.item.GenericModItem;
import com.udasboot.dascore.util.ToolTipsHandler;
import com.udasboot.witheringends.client.gui.screen.GuideBookScreen;
import com.udasboot.witheringends.init.ModItemGroup;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class GuideBook extends GenericModItem{

	public GuideBook() {
		super(new Item.Properties().tab(ModItemGroup.INSTANCE), ToolTipsHandler.generateToolTip("Guide book for Withering Ends mod."));
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand hand) {
		if(player instanceof ServerPlayerEntity) {
			Minecraft.getInstance().setScreen(new GuideBookScreen());
		}
		return super.use(worldIn, player, hand);
	}
	
}
