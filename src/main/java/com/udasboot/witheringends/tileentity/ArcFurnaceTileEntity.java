package com.udasboot.witheringends.tileentity;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.ArcFurnaceContainer;
import com.udasboot.witheringends.init.TileEntityTypeInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ArcFurnaceTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {

	public static int slots = 4;
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 3 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 1, 2 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	protected boolean isLit;
	protected int energy;
	protected int maxEnergy;
	protected int arcProgress;
	protected int totalArcTime;

	public final IIntArray dataAccess = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return ArcFurnaceTileEntity.this.energy;
			case 1:
				return ArcFurnaceTileEntity.this.maxEnergy;
			case 2:
				return ArcFurnaceTileEntity.this.arcProgress;
			case 3:
				return ArcFurnaceTileEntity.this.totalArcTime;
			case 4:
				return ArcFurnaceTileEntity.this.isLit ? 1 : 0;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				ArcFurnaceTileEntity.this.energy = value;
				break;
			case 1:
				ArcFurnaceTileEntity.this.maxEnergy = value;
			case 2:
				ArcFurnaceTileEntity.this.arcProgress = value;
			case 3:
				ArcFurnaceTileEntity.this.totalArcTime = value;
			case 4:
				ArcFurnaceTileEntity.this.isLit = (value == 1);
			}
		}

		public int getCount() {
			return 5;
		}
	};

	public ArcFurnaceTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType);
		this.isLit = false;
		this.maxEnergy = 10000;
		this.energy = 0;
		this.arcProgress = 0;
		this.totalArcTime = 40;
	}

	public ArcFurnaceTileEntity() {
		this(TileEntityTypeInit.ARC_FURNACE_TILE_ENTITY.get());
	}

	@Override
	public int getContainerSize() {
		return slots;
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemStack : items) {
			if (!itemStack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		return items.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int amount) {
		return ItemStackHelper.removeItem(items, index, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ItemStackHelper.takeItem(items, index);
	}

	@Override
	public void setItem(int index, ItemStack itemStack) {
		this.items.set(index, itemStack);
		if (itemStack.getCount() > this.getMaxStackSize()) {
			itemStack.setCount(this.getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(PlayerEntity playerIn) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return playerIn.distanceToSqr((double) this.worldPosition.getX() + 0.5D,
					(double) this.worldPosition.getY() + 0.5D, (double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public void tick() {
		boolean flag1 = false;
		if (!this.level.isClientSide) {
			ItemStack itemstack = this.items.get(0);
			if (!itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
					++this.arcProgress;
					this.isLit = true;
					if (this.arcProgress == this.totalArcTime) {
						this.arcProgress = 0;
						this.totalArcTime = this.getTotalArcTime();
						flag1 = true;
					}
			} else if (this.arcProgress > 0) {
				this.arcProgress = MathHelper.clamp(this.arcProgress - 2, 0, this.totalArcTime);
			} else {
				this.isLit = false;
			}
		}
		if (flag1) {
			this.setChanged();
		}
	}
	
	protected int getTotalArcTime() {
		return 200;
	}

	public boolean isArcing() {
		return arcProgress > 0;
	}

	@Override
	public int[] getSlotsForFace(Direction direction) {
		if (direction == Direction.DOWN) {
			return SLOTS_FOR_DOWN;
		} else {
			return direction == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
		}
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, Direction direction) {
		return this.canPlaceItem(index, itemStack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack itemStack, Direction direction) {
		return true;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + WitheringEnds.MOD_ID + ".arc_furnace_machine");
	}

	@Override
	protected Container createMenu(int windowId, PlayerInventory playerInventory) {
		return new ArcFurnaceContainer(windowId, playerInventory, this, this.dataAccess);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, items);
		compound.putBoolean("lit", this.isLit);
		compound.putInt("energy", this.energy);
		compound.putInt("maxEnergy", this.maxEnergy);
		compound.putInt("arcingProgress", this.arcProgress);
		compound.putInt("totalArcTime", this.totalArcTime);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, items);
		this.isLit = compound.getBoolean("lit");
		this.energy = compound.getInt("energy");
		this.maxEnergy = compound.getInt("maxEnergy");
		this.arcProgress = compound.getInt("arcingProgress");
		this.totalArcTime = compound.getInt("totalArcTime");
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack itemStack) {
		return false;
	}

}
