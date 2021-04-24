package com.udasboot.witheringends.core.init;

import com.udasboot.witheringends.common.container.ArcFurnaceContainer;
import com.udasboot.witheringends.common.container.CrusherContainer;
import com.udasboot.witheringends.common.container.InjectorContainer;
import com.udasboot.witheringends.core.init.util.Registration;

import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.network.IContainerFactory;

public class ContainerTypeInit {

	public static final RegistryObject<ContainerType<InjectorContainer>> INJECTOR_CONTAINER_TYPE = register(
			"injector_machine", InjectorContainer::new);
	public static final RegistryObject<ContainerType<CrusherContainer>> CRUSHER_CONTAINER_TYPE = register(
			"crusher_machine", CrusherContainer::new);
	public static final RegistryObject<ContainerType<ArcFurnaceContainer>> ARC_FURNACE_CONTAINER_TYPE = register(
			"arc_furnace_machine", ArcFurnaceContainer::new);

	public static void register() {
	}

	private static <T extends Container> RegistryObject<ContainerType<T>> register(String name,
			IContainerFactory<T> containerType) {
		return Registration.CONTAINER_TYPES.register(name, () -> {
			return IForgeContainerType.create(containerType);
		});
	}
}