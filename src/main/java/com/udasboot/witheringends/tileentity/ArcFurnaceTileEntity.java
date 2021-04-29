package com.udasboot.witheringends.tileentity;

import com.udasboot.bootcore.tileentity.AbstractMachineTileEntity;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.ArcFurnaceContainer;
import com.udasboot.witheringends.init.TileEntityTypeInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class ArcFurnaceTileEntity extends AbstractMachineTileEntity implements ISidedInventory {

	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 3 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 1, 2 };

	protected boolean isLit;

	public ArcFurnaceTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType, 4);
		this.isLit = false;
		this.dataAccess.set(8, this.isLit ? 1 : 0);
	}

	public ArcFurnaceTileEntity() {
		this(TileEntityTypeInit.ARC_FURNACE_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		super.tick();
		boolean flag1 = false;
		if (!this.level.isClientSide) {
			ItemStack itemstack = this.items.get(0);
			if (!itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
					++this.progressTime;
					this.isLit = true;
					if (this.progressTime == this.totalProgressTime) {
						this.progressTime = 0;
						this.totalProgressTime = this.gettotalProgressTime();
						flag1 = true;
					}
			} else if (this.progressTime > 0) {
				this.progressTime = MathHelper.clamp(this.progressTime - 2, 0, this.totalProgressTime);
			} else {
				this.isLit = false;
			}
		}
		if (flag1) {
			this.setChanged();
		}
	}
	
	@Override
	public void updateExData() {
		this.dataAccess.set(8, this.isLit ? 1 : 0);
	}
	
	protected int gettotalProgressTime() {
		return 200;
	}

	public boolean isArcing() {
		return progressTime > 0;
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
		compound.putInt("arcingProgress", this.progressTime);
		compound.putInt("totalProgressTime", this.totalProgressTime);
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
		this.progressTime = compound.getInt("arcingProgress");
		this.totalProgressTime = compound.getInt("totalProgressTime");
	}

}
