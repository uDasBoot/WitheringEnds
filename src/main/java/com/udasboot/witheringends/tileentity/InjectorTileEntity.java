package com.udasboot.witheringends.tileentity;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.block.Injector;
import com.udasboot.witheringends.container.InjectorContainer;
import com.udasboot.witheringends.init.ItemInit;
import com.udasboot.witheringends.init.TileEntityTypeInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class InjectorTileEntity extends LockableTileEntity implements ISidedInventory, ITickableTileEntity {

	public static int slots = 5;
	private static final int[] SLOTS_FOR_UP = new int[] { 3 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2, 4 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 0, 1 };

	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	protected boolean isFull;
	protected int breath;
	protected int maxBreath;
	protected int injectingProgress;
	protected int totalInjectingTime;
	protected int breathIn;
	protected int breathInTime;

	public final IIntArray dataAccess = new IIntArray() {
		public int get(int index) {
			switch (index) {
			case 0:
				return InjectorTileEntity.this.breath;
			case 1:
				return InjectorTileEntity.this.maxBreath;
			case 2:
				return InjectorTileEntity.this.injectingProgress;
			case 3:
				return InjectorTileEntity.this.totalInjectingTime;
			case 4:
				return InjectorTileEntity.this.breathIn;
			case 5:
				return InjectorTileEntity.this.breathInTime;
			default:
				return 0;
			}
		}

		public void set(int index, int value) {
			switch (index) {
			case 0:
				InjectorTileEntity.this.breath = value;
				break;
			case 1:
				InjectorTileEntity.this.maxBreath = value;
			case 2:
				InjectorTileEntity.this.injectingProgress = value;
			case 3:
				InjectorTileEntity.this.totalInjectingTime = value;
			case 4:
				InjectorTileEntity.this.breathIn = value;
			case 5:
				InjectorTileEntity.this.breathInTime = value;
			}
		}

		public int getCount() {
			return 6;
		}
	};

	public InjectorTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType);
		this.isFull = false;
		this.maxBreath = 10000;
		this.breath = 0;
		this.injectingProgress = 0;
		this.totalInjectingTime = 40;
		this.breathIn = 0;
		this.breathInTime = 20;
	}

	public InjectorTileEntity() {
		this(TileEntityTypeInit.INJECTOR_TILE_ENTITY.get());
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
		if (!this.level.isClientSide) {
			if (!this.items.get(0).isEmpty() && !isFull) {
				this.isFull = true;
				this.level.setBlock(this.worldPosition,
						this.level.getBlockState(this.worldPosition).setValue(Injector.FULL, true), 3);
			} else if (this.items.get(0).isEmpty() && isFull) {
				this.isFull = false;
				this.level.setBlock(this.worldPosition,
						this.level.getBlockState(this.worldPosition).setValue(Injector.FULL, false), 3);
			}
		}

		checkBreathUpdate();
		checkCraftRecipe();
	}

	private void checkCraftRecipe() {
		ItemStack encasement = items.get(0);
		ItemStack netherStar = items.get(1);
		ItemStack result = items.get(2);
		if ((encasement.getItem() == ItemInit.ENCASEMENT.get() && netherStar.getItem() == Items.NETHER_STAR
				&& this.breath >= 100 && !((result.getCount() + 1) > result.getMaxStackSize())) || isInjecting()) {
			if (!this.level.isClientSide) {
				if (injectingProgress == 0 && (!encasement.isEmpty() && !netherStar.isEmpty())) {
					this.breath -= 100;
					encasement.shrink(1);
					netherStar.shrink(1);
				}

				injectingProgress++;

				if (injectingProgress >= totalInjectingTime) {
					this.injectingProgress = 0;
					if (result.isEmpty()) {
						this.setItem(2, new ItemStack(ItemInit.ENCASED_NETHER_STAR.get()));
					} else {
						result.grow(1);
					}
					this.setChanged();
				}
			}
		}
	}

	private void checkBreathUpdate() {
		ItemStack breath = items.get(3);
		ItemStack bottle = items.get(4);
		if (breath.getItem() == Items.DRAGON_BREATH) {
			if (breathIn >= breathInTime && !isBreathFull()) {
				breathIn = 0;
				if (!isBreathFull() && bottle.getCount() < bottle.getMaxStackSize()) {
					if (!this.level.isClientSide) {
						this.breath += 1000;
						breath.shrink(1);
						if (bottle.isEmpty()) {
							this.setItem(4, new ItemStack(Items.GLASS_BOTTLE));
						} else {
							bottle.grow(1);
						}
						this.setChanged();
					}
				}
			}
			if (!isBreathFull()) {
				breathIn++;
			}
		}
	}

	public boolean isBreathFull() {
		return (breath + 1000) > maxBreath;
	}

	public boolean isInjecting() {
		return injectingProgress > 0;
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
		for(int i : getSlotsForFace(direction)) {
			if (index == i) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack itemStack, Direction direction) {
		for(int i : getSlotsForFace(direction)) {
			if (index == i) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + WitheringEnds.MOD_ID + ".injector_machine");
	}

	@Override
	protected Container createMenu(int windowId, PlayerInventory playerInventory) {
		return new InjectorContainer(windowId, playerInventory, this, this.dataAccess);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, items);
		compound.putBoolean("full", this.isFull);
		compound.putInt("breath", this.breath);
		compound.putInt("injectingProgress", this.injectingProgress);
		compound.putInt("totalInjectingTime", this.totalInjectingTime);
		compound.putInt("breathIn", this.breathIn);
		compound.putInt("breathInTime", this.breathInTime);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, items);
		this.isFull = compound.getBoolean("full");
		this.breath = compound.getInt("breath");
		this.injectingProgress = compound.getInt("injectingProgress");
		this.totalInjectingTime = compound.getInt("totalInjectingTime");
		this.breathIn = compound.getInt("breathIn");
		this.breathInTime = compound.getInt("breathInTime");
	}

	public int getBreath() {
		return this.breath;
	}

	public int getMaxBreath() {
		return this.maxBreath;
	}

}
