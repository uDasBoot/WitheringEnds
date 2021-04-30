package com.udasboot.witheringends.container;

import com.udasboot.dascore.container.AbstractMachineContainer;
import com.udasboot.dascore.container.IngredientSlot;
import com.udasboot.dascore.container.ResultSlot;
import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.ContainerTypeInit;
import com.udasboot.witheringends.tileentity.CrusherTileEntity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class CrusherContainer extends AbstractMachineContainer {


	public CrusherContainer(int windowId, PlayerInventory playerInventory,
			CrusherTileEntity te, IIntArray data) {
		super(ContainerTypeInit.CRUSHER_CONTAINER_TYPE.get(), windowId, playerInventory, te, data, BlockInit.CRUSHER.get());
	}

	public CrusherContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, (CrusherTileEntity) getTileEntity(playerInventory, data), new IntArray(10));
	}

	@Override
	public void addInvSlots() {
		this.addSlot(new IngredientSlot(te, 0, 56, 35));
		this.addSlot(new ResultSlot(te, 1, 116, 35));		
	}

}
