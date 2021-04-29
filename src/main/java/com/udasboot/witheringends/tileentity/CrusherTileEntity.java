package com.udasboot.witheringends.tileentity;

import javax.annotation.Nullable;

import com.udasboot.bootcore.tileentity.AbstractMachineTileEntity;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.CrusherContainer;
import com.udasboot.witheringends.init.RecipeInit;
import com.udasboot.witheringends.init.TileEntityTypeInit;
import com.udasboot.witheringends.item.crafting.recipe.CrusherRecipe;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CrusherTileEntity extends AbstractMachineTileEntity
		implements ISidedInventory, IRecipeHolder {

	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 0 };

	protected final IRecipeType<CrusherRecipe> recipeType;
	private final Object2IntOpenHashMap<ResourceLocation> recipesUsed = new Object2IntOpenHashMap<>();
	
	public CrusherTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType, 2);
		this.recipeType = RecipeInit.Types.CRUSHING;
	}

	public CrusherTileEntity() {
		this(TileEntityTypeInit.CRUSHER_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		boolean flag1 = false;
		if (!this.level.isClientSide) {
			ItemStack itemstack = this.items.get(0);
			if (!itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
					++this.progressTime;
					if (this.progressTime == this.totalProgressTime) {
						this.progressTime = 0;
						this.totalProgressTime = this.getTotalCrushTime();
						flag1 = true;
					}
			} else if (this.progressTime > 0) {
				this.progressTime = MathHelper.clamp(this.progressTime - 2, 0, this.totalProgressTime);
			}
		}
		if (flag1) {
			this.setChanged();
		}
	}

	public boolean canItemBeCrushed(@Nullable IRecipe<?> irecipe) {
		if (!this.items.get(0).isEmpty() && irecipe != null) {
			ItemStack itemstack = irecipe.getResultItem();
			System.out.println("Can Item Be Crushed " + itemstack.getDisplayName());
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.sameItem(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getMaxStackSize()
						&& itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) {

					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();

				}
			}
		} else {
			return false;
		}
	}

	private void crush(@Nullable IRecipe<?> irecipe) {
		if (irecipe != null && this.canItemBeCrushed(irecipe)) {
			ItemStack itemstack = this.items.get(0);
			ItemStack itemstack1 = irecipe.getResultItem();
			ItemStack itemstack2 = this.items.get(2);
			if (itemstack2.isEmpty()) {
				this.items.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(irecipe);
			}

			itemstack.shrink(1);
		}
	}

	protected int getTotalCrushTime() {
		return 200;
	}

	public boolean isCrushing() {
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
		return new TranslationTextComponent("container." + WitheringEnds.MOD_ID + ".crusher_machine");
	}

	@Override
	protected Container createMenu(int windowId, PlayerInventory playerInventory) {
		return new CrusherContainer(windowId, playerInventory, this, this.dataAccess);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, items);
		compound.putInt("energy", this.energy);
		compound.putInt("maxEnergy", this.maxEnergy);
		compound.putInt("progressTime", this.progressTime);
		compound.putInt("totalProgressTime", this.totalProgressTime);
		CompoundNBT compoundnbt = new CompoundNBT();
		this.recipesUsed.forEach((p_235643_1_, p_235643_2_) -> {
			compoundnbt.putInt(p_235643_1_.toString(), p_235643_2_);
		});
		compound.put("RecipesUsed", compoundnbt);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, items);
		this.energy = compound.getInt("energy");
		this.maxEnergy = compound.getInt("maxEnergy");
		this.progressTime = compound.getInt("progressTime");
		this.totalProgressTime = compound.getInt("totalProgressTime");

		CompoundNBT compoundnbt = compound.getCompound("RecipesUsed");

		for (String s : compoundnbt.getAllKeys()) {
			this.recipesUsed.put(new ResourceLocation(s), compoundnbt.getInt(s));
		}
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack itemStack) {
		return false;
	}

	@Override
	public void setRecipeUsed(IRecipe<?> p_193056_1_) {
		if (p_193056_1_ != null) {
			ResourceLocation resourcelocation = p_193056_1_.getId();
			this.recipesUsed.addTo(resourcelocation, 1);
		}
	}

	@Override
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

}
