package com.udasboot.witheringends.init;

import java.util.function.Supplier;

import com.udasboot.witheringends.block.AluminumOre;
import com.udasboot.witheringends.block.ArcFurnace;
import com.udasboot.witheringends.block.Crusher;
import com.udasboot.witheringends.block.Injector;
import com.udasboot.witheringends.block.TitaniumOre;
import com.udasboot.witheringends.block.TungstenOre;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class BlockInit {

	public static final RegistryObject<Block> INJECTOR = register("injector_machine", Injector::new);
	public static final RegistryObject<Block> CRUSHER = register("crusher_machine", Crusher::new);
	public static final RegistryObject<Block> ARC_FURNACE = register("arc_furnace_machine", ArcFurnace::new);
	
	public static final RegistryObject<Block> TITANIUM_ORE = register("titanium_ore", TitaniumOre::new);
	public static final RegistryObject<Block> ALUMINUM_ORE = register("aluminum_ore", AluminumOre::new);
	public static final RegistryObject<Block> TUNGSTEN_ORE = register("tungsten_ore", TungstenOre::new);

	public static void register() {
	}

	private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
		return Registration.BLOCKS.register(name, block);
	}
	
	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block){
		RegistryObject<T> ret = registerNoItem(name, block);
		Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS)));
		return ret;
	}

}
