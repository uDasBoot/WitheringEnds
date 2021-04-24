package com.udasboot.witheringends.core.init;

import java.util.function.Supplier;

import com.udasboot.witheringends.common.item.GenericWEItem;
import com.udasboot.witheringends.common.item.ItemBossEmpoweredBoots;
import com.udasboot.witheringends.common.item.ItemBossEmpoweredChestplate;
import com.udasboot.witheringends.common.item.ItemBossEmpoweredHelmet;
import com.udasboot.witheringends.common.item.ItemBossEmpoweredLeggings;
import com.udasboot.witheringends.common.item.ItemTitaniumAxe;
import com.udasboot.witheringends.common.item.ItemTitaniumBoots;
import com.udasboot.witheringends.common.item.ItemTitaniumChestplate;
import com.udasboot.witheringends.common.item.ItemTitaniumHelmet;
import com.udasboot.witheringends.common.item.ItemTitaniumHoe;
import com.udasboot.witheringends.common.item.ItemTitaniumHorseArmor;
import com.udasboot.witheringends.common.item.ItemTitaniumLeggings;
import com.udasboot.witheringends.common.item.ItemTitaniumPickaxe;
import com.udasboot.witheringends.common.item.ItemTitaniumShovel;
import com.udasboot.witheringends.common.item.ItemTitaniumSword;
import com.udasboot.witheringends.core.init.util.Registration;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

public class ItemInit {
	public static final RegistryObject<Item> ENCASED_NETHER_STAR = register("encased_nether_star",
			() -> new Item(new Item.Properties().tab(ItemGroupInit.WITHERING_ENDS).stacksTo(16)));
	public static final RegistryObject<Item> ENCASEMENT = register("encasement", GenericWEItem::new);

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
	public static final RegistryObject<Item> TITANIUM_INGOT = register("titanium_ingot", GenericWEItem::new);
	public static final RegistryObject<Item> TITANIUM_NUGGET = register("titanium_nugget", GenericWEItem::new);
	public static final RegistryObject<Item> TITANIUM_DUST = register("titanium_dust", GenericWEItem::new);
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
	public static final RegistryObject<Item> TITANIUM_DOOR = register("titanium_door", GenericWEItem::new);
	public static final RegistryObject<Item> TITANIUM_HORSE_ARMOR = register("titanium_horse_armor", ItemTitaniumHorseArmor::new);
	
	public static final RegistryObject<Item> TUNGSTEN_CARBIDE_ROD = register("tungsten_carbide_rod", GenericWEItem::new);
	public static final RegistryObject<Item> TUNGSTEN_INGOT = register("tungsten_ingot", GenericWEItem::new);
	public static final RegistryObject<Item> TUNGSTEN_NUGGET = register("tungsten_nugget", GenericWEItem::new);
	public static final RegistryObject<Item> TUNGSTEN_DUST = register("tungsten_dust", GenericWEItem::new);
	
	public static final RegistryObject<Item> ALUMINUM_INGOT = register("aluminum_ingot", GenericWEItem::new);
	public static final RegistryObject<Item> ALUMINUM_NUGGET = register("aluminum_nugget", GenericWEItem::new);
	public static final RegistryObject<Item> ALUMINUM_DUST = register("aluminum_dust", GenericWEItem::new);

	private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> item) {
		return Registration.ITEMS.register(name, item);
	}

	public static void register() {
	}
}
