/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract.DeepInteract;

import java.lang.reflect.Field;

import net.minecraft.item.ItemStack;
import Reika.DragonAPI.Base.DragonAPIMod;
import Reika.DragonAPI.Exception.MisuseException;
import Reika.DragonAPI.Interfaces.RegistrationList;
import Reika.DragonAPI.Libraries.ReikaRegistryHelper;
import Reika.DragonAPI.Libraries.Registry.ReikaItemHelper;

import com.creativemd.craftingmanager.api.utils.sorting.ItemSortingList;
import com.creativemd.craftingmanager.api.utils.sorting.SortingItem;

public class CraftingManagerBlacklisting {

	private static ItemSortingList blacklist;

	public static void registerItem(SortingItem sort) {
		blacklist.add(sort);
	}

	static {
		try {
			Class c = Class.forName("com.creativemd.craftingmanager.mod.CraftingManagerMod");
			Field f = c.getField("forbiddenOutputs");
			blacklist = (ItemSortingList)f.get(null);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class ModSorting extends SortingItem {

		public final String modID;

		public ModSorting(DragonAPIMod mod) {
			if (mod == null)
				throw new MisuseException("You cannot block items from a null mod ID!");
			String id = mod.getModContainer().getModId();
			modID = id;
		}

		@Override
		protected boolean isObject(ItemStack stack) {
			return ReikaItemHelper.isItemAddedByMod(stack.getItem(), modID);
		}

	}

	public static class RegistrySorting extends SortingItem {

		public final Class<? extends RegistrationList> registryEnum;

		public RegistrySorting(Class<? extends RegistrationList> reg) {
			if (reg == null)
				throw new MisuseException("You cannot block items from a null registry!");
			registryEnum = reg;
		}

		@Override
		protected boolean isObject(ItemStack stack) {
			RegistrationList reg = ReikaRegistryHelper.getRegistry(stack.getItem());
			return reg != null && reg.getClass() == registryEnum;
		}

	}

}
