package com.udasboot.witheringends.init;

import java.util.function.Supplier;

import com.udasboot.witheringends.tileentity.ArcFurnaceTileEntity;
import com.udasboot.witheringends.tileentity.CrusherTileEntity;
import com.udasboot.witheringends.tileentity.GeneratorTileEntity;
import com.udasboot.witheringends.tileentity.InjectorTileEntity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

public class TileEntityTypeInit {
	
	public static final RegistryObject<TileEntityType<InjectorTileEntity>> INJECTOR_TILE_ENTITY = register("injector_machine", InjectorTileEntity::new, BlockInit.INJECTOR);
	public static final RegistryObject<TileEntityType<CrusherTileEntity>> CRUSHER_TILE_ENTITY = register("crusher_machine", CrusherTileEntity::new, BlockInit.CRUSHER);
	public static final RegistryObject<TileEntityType<ArcFurnaceTileEntity>> ARC_FURNACE_TILE_ENTITY = register("arc_furnace_machine", ArcFurnaceTileEntity::new, BlockInit.ARC_FURNACE);
	public static final RegistryObject<TileEntityType<GeneratorTileEntity>> GENERATOR_TILE_ENTITY = register("generator_machine", GeneratorTileEntity::new, BlockInit.GENERATOR);

	public static void register() {}
	
	private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> tileEntityType, RegistryObject<? extends Block> block){
		return Registration.TILE_ENTITY_TYPES.register(name, () -> {
			return TileEntityType.Builder.of(tileEntityType, block.get()).build(null);
		});
	}
}