package com.udasboot.witheringends.init;

import java.util.function.Supplier;

import com.udasboot.witheringends.item.EncasedNetherStar;
import com.udasboot.witheringends.item.GenericWEItem;
import com.udasboot.witheringends.item.GuideBook;
import com.udasboot.witheringends.item.ItemBossEmpoweredBoots;
import com.udasboot.witheringends.item.ItemBossEmpoweredChestplate;
import com.udasboot.witheringends.item.ItemBossEmpoweredHelmet;
import com.udasboot.witheringends.item.ItemBossEmpoweredLeggings;
import com.udasboot.witheringends.item.ItemTitaniumAxe;
import com.udasboot.witheringends.item.ItemTitaniumBoots;
import com.udasboot.witheringends.item.ItemTitaniumChestplate;
import com.udasboot.witheringends.item.ItemTitaniumHelmet;
import com.udasboot.witheringends.item.ItemTitaniumHoe;
import com.udasboot.witheringends.item.ItemTitaniumHorseArmor;
import com.udasboot.witheringends.item.ItemTitaniumLeggings;
import com.udasboot.witheringends.item.ItemTitaniumPickaxe;
import com.udasboot.witheringends.item.ItemTitaniumShovel;
import com.udasboot.witheringends.item.ItemTitaniumSword;
import com.udasboot.witheringends.item.WEToolTips;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ItemInit {
	
	public static final RegistryObject<Item> GUIDE_BOOK = register("guide_book", GuideBook::new);
	public static final RegistryObject<Item> ENCASED_NETHER_STAR = register("encased_nether_star", EncasedNetherStar::new);
	public static final RegistryObject<Item> ENCASEMENT = register("encasement", () -> new GenericWEItem(WEToolTips.ENCASEMENT));

	// BOSS EMPOWERED SET
	public static final RegistryObject<Item> BOSS_EMPOWERED_HELM = register("boss_empowered_helmet",
			ItemBossEmpoweredHelmet::new);
	public static final RegistryObject<Item> BOSS_EMPOWERED_CHEST = register("boss_empowered_chestplate",
			ItemBossEmpoweredChestplate::new);
	public static final RegistryObject<Item> BOSS_EMPOWERED_LEGS = register("boss_empowered_leggings",
			ItemBossEmpoweredLeggings::new);
	public static final RegistryObject<Item> BOSS_EMPOWERED_BOOTS = register("boss_empowered_boots",
			ItemBossEmpoweredBoots::new);

	// TITANIUM SET
	public static final RegistryObject<Item> TITANIUM_INGOT = register("titanium_ingot", () -> new GenericWEItem(WEToolTips.TITANIUM));
	public static final RegistryObject<Item> TITANIUM_NUGGET = register("titanium_nugget", () -> new GenericWEItem(WEToolTips.TITANIUM));
	public static final RegistryObject<Item> TITANIUM_DUST = register("titanium_dust", () -> new GenericWEItem(WEToolTips.TITANIUM));
	public static final RegistryObject<Item> TITANIUM_HELM = register("titanium_helmet", ItemTitaniumHelmet::new);
	public static final RegistryObject<Item> TITANIUM_CHEST = register("titanium_chestplate",
			ItemTitaniumChestplate::new);
	public static final RegistryObject<Item> TITANIUM_LEGS = register("titanium_leggings", ItemTitaniumLeggings::new);
	public static final RegistryObject<Item> TITANIUM_BOOTS = register("titanium_boots", ItemTitaniumBoots::new);
	public static final RegistryObject<Item> TITANIUM_AXE = register("titanium_axe", ItemTitaniumAxe::new);
	public static final RegistryObject<Item> TITANIUM_PICKAXE = register("titanium_pickaxe", ItemTitaniumPickaxe::new);
	public static final RegistryObject<Item> TITANIUM_HOE = register("titanium_hoe", ItemTitaniumHoe::new);
	public static final RegistryObject<Item> TITANIUM_SHOVEL = register("titanium_shovel", ItemTitaniumShovel::new);
	public static final RegistryObject<Item> TITANIUM_SWORD = register("titanium_sword", ItemTitaniumSword::new);
	public static final RegistryObject<Item> TITANIUM_DOOR = register("titanium_door", () -> new GenericWEItem(WEToolTips.NONE));
	public static final RegistryObject<Item> TITANIUM_HORSE_ARMOR = register("titanium_horse_armor", ItemTitaniumHorseArmor::new);
	
	public static final RegistryObject<Item> TUNGSTEN_CARBIDE_ROD = register("tungsten_carbide_rod", () -> new GenericWEItem(WEToolTips.TUNGSTEN));
	public static final RegistryObject<Item> TUNGSTEN_INGOT = register("tungsten_ingot", () -> new GenericWEItem(WEToolTips.TUNGSTEN));
	public static final RegistryObject<Item> TUNGSTEN_NUGGET = register("tungsten_nugget", () -> new GenericWEItem(WEToolTips.TUNGSTEN));
	public static final RegistryObject<Item> TUNGSTEN_DUST = register("tungsten_dust", () -> new GenericWEItem(WEToolTips.TUNGSTEN));
	
	public static final RegistryObject<Item> ALUMINUM_INGOT = register("aluminum_ingot", () -> new GenericWEItem(WEToolTips.ALUMINUM));
	public static final RegistryObject<Item> ALUMINUM_NUGGET = register("aluminum_nugget", () -> new GenericWEItem(WEToolTips.ALUMINUM));
	public static final RegistryObject<Item> ALUMINUM_DUST = register("aluminum_dust", () -> new GenericWEItem(WEToolTips.ALUMINUM));

	private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
		return Registration.ITEMS.register(name, item);
	}

	public static void register() {
	}
}
