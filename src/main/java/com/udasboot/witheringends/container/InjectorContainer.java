package com.udasboot.witheringends.container;

import com.udasboot.dascore.container.AbstractMachineContainer;
import com.udasboot.dascore.container.FuelSlot;
import com.udasboot.dascore.container.IngredientSlot;
import com.udasboot.dascore.container.ResultSlot;
import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.ContainerTypeInit;
import com.udasboot.witheringends.tileentity.InjectorTileEntity;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class InjectorContainer extends AbstractMachineContainer {

	public InjectorContainer(int windowId, PlayerInventory playerInventory,
			InjectorTileEntity te, IIntArray data) {
		super(ContainerTypeInit.INJECTOR_CONTAINER_TYPE.get(), windowId, playerInventory, te, data, BlockInit.INJECTOR.get());

	}

	public InjectorContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, (InjectorTileEntity)getTileEntity(playerInventory, data), new IntArray(10));
	}

	@Override
	public void addInvSlots() {
		Ingredient fuels = Ingredient.of(Items.DRAGON_BREATH);
		this.addSlot(new IngredientSlot(te, 0, 8, 35));
		this.addSlot(new IngredientSlot(te, 1, 44, 35));
		this.addSlot(new ResultSlot(te, 2, 103, 35));
		this.addSlot(new FuelSlot(fuels, te, 3, 116, 8));
		this.addSlot(new ResultSlot(te, 4, 116, 62));
	}
	
	public int getBreath() {
		return this.data.get(4);
	}
	
	public int getMaxBreath() {
		return this.data.get(5);
	}
	
	public double getBreathPercent() {
		return (double) this.getBreath() / (double) this.getMaxBreath();
	}
	
	public int getBreathIn() {
		return this.data.get(6);
	}
	
	public int getBreathInTime() {
		return this.data.get(7);
	}
	
	public double getBreathInPercent() {
		return (double) this.getBreathIn() / (double) this.getBreathInTime();
		
	}

}
