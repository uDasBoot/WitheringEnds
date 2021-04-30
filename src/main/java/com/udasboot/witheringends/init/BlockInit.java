package com.udasboot.witheringends.init;

import java.util.function.Supplier;

import com.udasboot.dascore.block.GenericModBlockItem;
import com.udasboot.dascore.util.ToolTipsHandler;
import com.udasboot.witheringends.block.AluminumOre;
import com.udasboot.witheringends.block.ArcFurnace;
import com.udasboot.witheringends.block.Crusher;
import com.udasboot.witheringends.block.Generator;
import com.udasboot.witheringends.block.Injector;
import com.udasboot.witheringends.block.TitaniumOre;
import com.udasboot.witheringends.block.TungstenOre;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class BlockInit {
	
	private static final Item.Properties DEFAULT_PROPS = new Item.Properties().tab(ModItemGroup.INSTANCE);

	public static final RegistryObject<Block> INJECTOR = register("injector_machine", Injector::new, ToolTipsHandler.generateToolTip("Test"));
	public static final RegistryObject<Block> CRUSHER = register("crusher_machine", Crusher::new);
	public static final RegistryObject<Block> ARC_FURNACE = register("arc_furnace_machine", ArcFurnace::new);
	public static final RegistryObject<Block> GENERATOR = register("generator_machine", Generator::new);
	
	public static final RegistryObject<Block> TITANIUM_ORE = register("titanium_ore", TitaniumOre::new);
	public static final RegistryObject<Block> ALUMINUM_ORE = register("aluminum_ore", AluminumOre::new);
	public static final RegistryObject<Block> TUNGSTEN_ORE = register("tungsten_ore", TungstenOre::new);

	public static void register() {
	}

	private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
		return Registration.BLOCKS.register(name, block);
	}
	
	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, ToolTipsHandler toolTip){
		RegistryObject<T> ret = registerNoItem(name, block);
		Registration.ITEMS.register(name, () -> new GenericModBlockItem(ret.get(), DEFAULT_PROPS, toolTip));
		return ret;
	}
	
	private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block){
		return register(name, block, ToolTipsHandler.NONE);
	}

}
