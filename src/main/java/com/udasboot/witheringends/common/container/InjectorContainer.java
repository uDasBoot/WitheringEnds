package com.udasboot.witheringends.common.container;

import java.util.Objects;

import com.udasboot.witheringends.common.tileentity.InjectorTileEntity;
import com.udasboot.witheringends.core.init.BlockInit;
import com.udasboot.witheringends.core.init.ContainerTypeInit;
import com.udasboot.witheringends.core.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;

public class InjectorContainer extends Container {

	public final InjectorTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	private final int invSize;

	public InjectorContainer(int windowId, PlayerInventory playerInventory,
			InjectorTileEntity te, IIntArray data) {
		super(ContainerTypeInit.INJECTOR_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());
		this.data = data;
		this.invSize = InjectorTileEntity.slots;

		this.addSlot(new InjectorContainer.IngredientSlot(te, 0, 8, 35));
		this.addSlot(new InjectorContainer.IngredientSlot(te, 1, 57, 35));
		this.addSlot(new InjectorContainer.ResultSlot(te, 2, 97, 35));
		this.addSlot(new InjectorContainer.FuelSlot(te, 3, 132, 8));
		this.addSlot(new InjectorContainer.ResultSlot(te, 4, 132, 62));

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				int index = col + row * 9 + 9;
				this.addSlot(new Slot(playerInventory, index, 8 + col * 18, 166 - (4 - row) * 18 - 10));
			}
		}

		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
		}
		this.addDataSlots(data);
	}

	public InjectorContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data), new IntArray(6));
	}

	private static InjectorTileEntity getTileEntity(final PlayerInventory playerInventory,
			final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Packet Buffer cannot be null!");
		final TileEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof InjectorTileEntity) {
			return (InjectorTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity is not correct!");
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		// TODO Auto-generated method stub
		return stillValid(canInteractWithCallable, playerIn, BlockInit.INJECTOR.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < invSize) {
				if (!this.moveItemStackTo(itemstack1, invSize, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, invSize, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}
	
	public double getFuelLevel() {
		return (double) this.data.get(0) / (double) this.data.get(1);
	}
	
	public double getInjectingProgress() {
		return (double) this.data.get(2) / (double) this.data.get(3);
	}
	
	public double getBreathInProgress() {
		return (double) this.data.get(4) / (double) this.data.get(5);
	}
	
	static class FuelSlot extends Slot{

		public FuelSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		public boolean mayPlace(ItemStack itemStack) {
	         return itemStack.getItem() == Items.DRAGON_BREATH;
	      }
		
	}
	
	static class ResultSlot extends Slot{

		public ResultSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean mayPlace(ItemStack itemStack) {
			return false;
		}
		
	}
	
	static class IngredientSlot extends Slot{

		public IngredientSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean mayPlace(ItemStack itemStack) {
			if(this.index == 0 && itemStack.getItem() == ItemInit.ENCASEMENT.get()) {
				return true;
			} else if (this.index == 1 && itemStack.getItem() == Items.NETHER_STAR) {
				return true;
			} else {
				return false;
			}
		}
		
	}

}
