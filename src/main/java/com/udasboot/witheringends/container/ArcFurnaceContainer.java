package com.udasboot.witheringends.container;

import java.util.Objects;

import com.udasboot.witheringends.init.BlockInit;
import com.udasboot.witheringends.init.ContainerTypeInit;
import com.udasboot.witheringends.init.ItemInit;
import com.udasboot.witheringends.tileentity.ArcFurnaceTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.IntArray;

public class ArcFurnaceContainer extends Container {

	public final ArcFurnaceTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;
	private final IIntArray data;
	private final int invSize;

	public ArcFurnaceContainer(int windowId, PlayerInventory playerInventory,
			ArcFurnaceTileEntity te, IIntArray data) {
		super(ContainerTypeInit.ARC_FURNACE_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());
		this.data = data;
		this.invSize = ArcFurnaceTileEntity.slots;

		this.addSlot(new ArcFurnaceContainer.IngredientSlot(te, 0, 47, 17));
		this.addSlot(new ArcFurnaceContainer.IngredientSlot(te, 1, 65, 17));
		this.addSlot(new ArcFurnaceContainer.FuelSlot(te, 2, 56, 53));
		this.addSlot(new ArcFurnaceContainer.ResultSlot(te, 3, 116, 35));

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

	public ArcFurnaceContainer(int windowId, PlayerInventory playerInventory, PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data), new IntArray(5));
	}

	private static ArcFurnaceTileEntity getTileEntity(final PlayerInventory playerInventory,
			final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "Player Inventory cannot be null!");
		Objects.requireNonNull(data, "Packet Buffer cannot be null!");
		final TileEntity te = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof ArcFurnaceTileEntity) {
			return (ArcFurnaceTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity is not correct!");
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		// TODO Auto-generated method stub
		return stillValid(canInteractWithCallable, playerIn, BlockInit.ARC_FURNACE.get());
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
	
	public double getArcProgress() {
		return (double) this.data.get(2) / (double) this.data.get(3);
	}
	
	public boolean isLit() {
		return (this.data.get(4) == 1);
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
			return true;
		}
		
	}
	
	static class FuelSlot extends Slot{

		public FuelSlot(IInventory inventory, int index, int x, int y) {
			super(inventory, index, x, y);
		}
		
		@Override
		public boolean mayPlace(ItemStack itemStack) {
			if (isAluminum(itemStack)) {
				return true;
			}
			return false;
		}

		private boolean isAluminum(ItemStack itemStack) {
			if(itemStack.getItem() == ItemInit.ALUMINUM_INGOT.get() || itemStack.getItem() == ItemInit.ALUMINUM_NUGGET.get() || itemStack.getItem() == ItemInit.ALUMINUM_DUST.get()) {
				return true;
			}
			return false;
		}
		
	}

}
