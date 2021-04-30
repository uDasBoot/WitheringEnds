package com.udasboot.witheringends.tileentity;

import com.udasboot.dascore.tileentity.AbstractGeneratorTileEntity;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.block.Generator;
import com.udasboot.witheringends.container.GeneratorContainer;
import com.udasboot.witheringends.init.TileEntityTypeInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GeneratorTileEntity extends AbstractGeneratorTileEntity {

	public GeneratorTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType, 1);
	}

	public GeneratorTileEntity() {
		this(TileEntityTypeInit.GENERATOR_TILE_ENTITY.get());
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level.isClientSide) {
			if (this.getItem(0).getItem() == Items.COAL && !this.isGenerating()) {
				this.getItem(0).shrink(1);
				this.progressTime++;
			}
			this.level.setBlock(this.worldPosition,
					this.level.getBlockState(this.worldPosition).setValue(Generator.LIT, this.isGenerating()), 3);
		}
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + WitheringEnds.MOD_ID + ".generator_machine");
	}

	@Override
	protected Container createMenu(int windowId, PlayerInventory playerInventory) {
		return new GeneratorContainer(windowId, playerInventory, this, this.dataAccess);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, items);
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
		this.energy = compound.getInt("energy");
		this.maxEnergy = compound.getInt("maxEnergy");
		this.progressTime = compound.getInt("arcingProgress");
		this.totalProgressTime = compound.getInt("totalProgressTime");
	}

}
