package com.udasboot.witheringends.core.init;

import java.util.function.Supplier;

import com.udasboot.witheringends.common.tileentity.ArcFurnaceTileEntity;
import com.udasboot.witheringends.common.tileentity.CrusherTileEntity;
import com.udasboot.witheringends.common.tileentity.InjectorTileEntity;
import com.udasboot.witheringends.core.init.util.Registration;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class TileEntityTypeInit {
	
	public static final RegistryObject<TileEntityType<InjectorTileEntity>> INJECTOR_TILE_ENTITY = register("injector_machine", InjectorTileEntity::new, BlockInit.INJECTOR);
	public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TILE_ENTITY = register("crusher_machine", CrusherTileEntity::new, BlockInit.CRUSHER);
	public static final RegistryObject<TileEntityType<ArcFurnaceTileEntity>> ARC_FURNACE_TILE_ENTITY = register("arc_furnace_machine", ArcFurnaceTileEntity::new, BlockInit.ARC_FURNACE);

	public static void register() {}
	
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> tileEntityType, RegistryObject<? extends Block> block){
		return Registration.TILE_ENTITY_TYPES.register(name, () -> {
			return TileEntityType.Builder.of(tileEntityType, block.get()).build(null);
		});
	}
}