package com.udasboot.witheringends.tileentity;

import com.udasboot.bootcore.tileentity.AbstractGeneratorTileEntity;
import com.udasboot.bootcore.tileentity.AbstractMachineTileEntity;
import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.container.GeneratorContainer;
import com.udasboot.witheringends.init.TileEntityTypeInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class GeneratorTileEntity extends AbstractMachineTileEntity{
	
	public boolean isLit;

	public GeneratorTileEntity(TileEntityType<?> tileEntityType) {
		super(tileEntityType, 1);
		this.isLit = false;
		this.dataAccess.set(8, this.isLit ? 1 : 0);
	}
	
	public GeneratorTileEntity() {
		this(TileEntityTypeInit.GENERATOR_TILE_ENTITY.get());
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!this.level.isClientSide) {
			TileEntity test = this.level.getBlockEntity(this.getBlockPos().east());
			if(test instanceof AbstractMachineTileEntity && !(test instanceof AbstractGeneratorTileEntity) && this.getItem(0).getItem() == Items.COAL) {
				((AbstractMachineTileEntity) test).receiveEnergy(20, false);
				this.isLit = true;
			}
		}
	}
	
	@Override
	public void updateExData() {
		this.dataAccess.set(8, this.isLit ? 1 : 0);
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
