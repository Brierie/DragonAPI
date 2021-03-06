/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract;

import moze_intel.projecte.api.ProjectEAPI;
import net.minecraft.item.ItemStack;
import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Interfaces.RegistrationList;

public class ReikaEEHelper {

	public static void blacklistItemStack(ItemStack is) {
		if (!isLoaded())
			return;
		if (ModList.PROJECTE.isLoaded())
			ProjectEAPI.registerCustomEMC(is, 0);
	}

	public static void blacklistEntry(RegistrationList reg) {
		if (!isLoaded())
			return;
		for (int i = 0; i < reg.getNumberMetadatas(); i++) {
			blacklistItemStack(reg.getStackOfMetadata(i));
		}
	}

	/** Do not use until the EMC registry supports wildcards! */
	public static void blacklistRegistry(RegistrationList[] reg) {
		if (!isLoaded())
			return;
		for (int i = 0; i < reg.length; i++) {
			blacklistEntry(reg[i]);
		}
	}

	public static boolean isLoaded() {
		return ModList.PROJECTE.isLoaded();
	}

}
