package com.udasboot.witheringends.init;

import com.udasboot.witheringends.WitheringEnds;
import com.udasboot.witheringends.entities.AncientEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.IFactory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

public class EntityTypeInit {
	public static final RegistryObject<EntityType<AncientEntity>> ANCIENT_ENTITY = register("ancient",
			AncientEntity::new, EntityClassification.CREATURE);

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String name,
			IFactory<T> entity, EntityClassification classification) {
		return Registration.ENTITY_TYPES.register(name, () -> EntityType.Builder.of(entity, classification).sized(1.0f, 2.0f).build(new ResourceLocation(WitheringEnds.MOD_ID, name).toString()));
	}

	public static void register() {}
}
