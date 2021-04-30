package com.udasboot.witheringends.tileentity;

import com.udasboot.dascore.tileentity.AbstractMachineTileEntity;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.block.Injector;
import com.udasboot.witheringends.container.InjectorContainer;
import com.udasboot.witheringends.init.ItemInit;
import com.udasboot.witheringends.init.TileEntityTypeInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class InjectorTileEntity extends AbstractMachineTileEntity implements ISidedInventory {

	private static final int[] SLOTS_FOR_UP = new int[] { 3 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 2, 4 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 0, 1 };

	protected boolean isFull;
	protected int breath;
	protected int maxBreath;
	protected int breathIn;
	protected int breathInTime;

	public InjectorTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType, 5);
		this.isFull = false;
		this.maxBreath = 10000;
		this.breath = 0;
		this.breathIn = 0;
		this.breathInTime = 20;
	}

	public InjectorTileEntity() {
		this(TileEntityTypeInit.INJECTOR_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		super.tick();
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

	@Override
	public void updateExData() {
		this.dataAccess.set(4, breath);
		this.dataAccess.set(5, maxBreath);
		this.dataAccess.set(6, breathIn);
		this.dataAccess.set(7, breathInTime);
		this.dataAccess.set(8, isFull ? 1 : 0);
	}

	private void checkCraftRecipe() {
		ItemStack encasement = items.get(0);
		ItemStack netherStar = items.get(1);
		ItemStack result = items.get(2);
		if ((encasement.getItem() == ItemInit.ENCASEMENT.get() && netherStar.getItem() == Items.NETHER_STAR
				&& this.breath >= 100 && !((result.getCount() + 1) > result.getMaxStackSize())) || isInUse()) {
			if (!this.level.isClientSide) {
				if (this.hasEnoughEnergy && progressTime == 0 && (!encasement.isEmpty() && !netherStar.isEmpty())) {
					this.breath -= 100;
					encasement.shrink(1);
					netherStar.shrink(1);
				}

				if (this.hasEnoughEnergy) {

					progressTime++;

					if (progressTime >= totalProgressTime) {
						this.progressTime = 0;
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
		for (int i : getSlotsForFace(direction)) {
			if (index == i) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack itemStack, Direction direction) {
		for (int i : getSlotsForFace(direction)) {
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
		compound.putInt("progressTime", this.progressTime);
		compound.putInt("totalProgressTime", this.totalProgressTime);
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
		this.progressTime = compound.getInt("progressTime");
		this.totalProgressTime = compound.getInt("totalProgressTime");
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
