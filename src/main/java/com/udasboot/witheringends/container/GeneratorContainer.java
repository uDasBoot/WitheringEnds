package com.udasboot.witheringends.container;

import com.udasboot.bootcore.container.AbstractMachineContainer;
import com.udasboot.bootcore.container.FuelSlot;
import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.ContainerTypeInit;
import com.udasboot.witheringends.tileentity.GeneratorTileEntity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class GeneratorContainer extends AbstractMachineContainer {

	public GeneratorContainer(int windowId, PlayerInventory playerInventory, GeneratorTileEntity te, IIntArray data) {
		super(ContainerTypeInit.GENERATOR_CONTAINER_TYPE.get(), windowId, playerInventory, te, data,
				BlockInit.GENERATOR.get());
	}

	public GeneratorContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, (GeneratorTileEntity) getTileEntity(playerInventory, data), new IntArray(10));
	}

	@Override
	public void addInvSlots() {
		Ingredient fuels = Ingredient.of(Items.COAL);
		this.slots.add(new FuelSlot(fuels, te, 0, 80, 53));
	}

}
