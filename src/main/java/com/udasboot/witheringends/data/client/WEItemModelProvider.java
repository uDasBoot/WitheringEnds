package com.udasboot.witheringends.data.client;

import com.udasboot.witheringends.WitheringEnds;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class WEItemModelProvider extends ItemModelProvider {

	public WEItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, WitheringEnds.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		
		buildBlockItem("titanium_ore");
		buildBlockItem("aluminum_ore");
		buildBlockItem("tungsten_ore");

		builder(itemGenerated, "encased_nether_star");
		builder(itemGenerated, "encasement");
		builder(itemGenerated, "tungsten_carbide_rod");
		builder(itemGenerated, "guide_book");
		
		completeMetalSet("titanium");
		completeArmorSet("boss_empowered");
		
		metals("aluminum");
		metals("tungsten");
	}

	private void metals(String material) {
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		builder(itemGenerated, material + "_ingot");
		builder(itemGenerated, material + "_nugget");
		builder(itemGenerated, material + "_dust");
	}

	private void buildBlockItem(String block) {
		getBuilder(block).parent(new ModelFile.UncheckedModelFile(modLoc("block/" + block)));
	}

	private ItemModelBuilder builder(ModelFile parent, String name) {
		return getBuilder(name).parent(parent).texture("layer0", "item/" + name);
	}
	
	private void completeMetalSet(String material) {
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		completeToolArmorSet(material);
		metals(material);
		builder(itemGenerated, material + "_door");
		builder(itemGenerated, material + "_horse_armor");
	}
	
	private void completeToolArmorSet(String material) {
		completeArmorSet(material);
		completeToolSet(material);
	}

	private void completeToolSet(String material) {
		ModelFile parent = getExistingFile(mcLoc("item/handheld"));
		builder(parent, material + "_axe");
		builder(parent, material + "_hoe");
		builder(parent, material + "_pickaxe");
		builder(parent, material + "_shovel");
		builder(parent, material + "_sword");
	}

	private void completeArmorSet(String material) {
		ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
		builder(itemGenerated, material + "_helmet");
		builder(itemGenerated, material + "_chestplate");
		builder(itemGenerated, material + "_leggings");
		builder(itemGenerated, material + "_boots");
	}
	
}
