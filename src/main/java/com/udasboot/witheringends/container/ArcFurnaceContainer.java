package com.udasboot.witheringends.container;

import com.udasboot.dascore.container.AbstractMachineContainer;
import com.udasboot.dascore.container.FuelSlot;
import com.udasboot.dascore.container.IngredientSlot;
import com.udasboot.dascore.container.ResultSlot;
import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.ContainerTypeInit;
import com.udasboot.witheringends.init.ItemInit;
import com.udasboot.witheringends.tileentity.ArcFurnaceTileEntity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class ArcFurnaceContainer extends AbstractMachineContainer {

	public ArcFurnaceContainer(int windowId, PlayerInventory playerInventory,
			ArcFurnaceTileEntity te, IIntArray data) {
		super(ContainerTypeInit.ARC_FURNACE_CONTAINER_TYPE.get(), windowId, playerInventory, te, data, BlockInit.ARC_FURNACE.get());
	}

	public ArcFurnaceContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, (ArcFurnaceTileEntity) getTileEntity(playerInventory, data), new IntArray(10));
	}

	@Override
	public void addInvSlots() {
		Ingredient fuels = Ingredient.of(ItemInit.ALUMINUM_DUST.get(), ItemInit.ALUMINUM_INGOT.get(), ItemInit.ALUMINUM_NUGGET.get());
		this.addSlot(new IngredientSlot(te, 0, 47, 17));
		this.addSlot(new IngredientSlot(te, 1, 65, 17));
		this.addSlot(new FuelSlot(fuels, te, 2, 56, 53));
		this.addSlot(new ResultSlot(te, 3, 116, 35));
	}
	
	public boolean isArcing() {
		return this.getProgress() > 0;
	}
}