/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract.ItemHandlers;

import java.lang.reflect.Field;

import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Base.ModHandlerBase;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;

public class ThaumBiomeHandler extends ModHandlerBase {

	private static final ThaumBiomeHandler instance = new ThaumBiomeHandler();

	public final int taintBiomeID;
	public final int eerieBiomeID;
	public final int magicBiomeID;

	private ThaumBiomeHandler() {
		super();
		int idtaint = -1;
		int ideerie = -1;
		int idmagic = -1;

		if (this.hasMod()) {
			Class thaum = ModList.THAUMCRAFT.getBlockClass();

			try {
				Class config = Class.forName("thaumcraft.common.config.Config");
				Field biome = config.getField("biomeTaintID");
				idtaint = biome.getInt(null);

				biome = config.getField("biomeEerieID");
				ideerie = biome.getInt(null);

				biome = config.getField("biomeMagicalForestID");
				idmagic = biome.getInt(null);
			}
			catch (ClassNotFoundException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not load ThaumCraft config class!");
				e.printStackTrace();
			}
			catch (NoSuchFieldException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not load field from ThaumCraft config class!");
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not read field from ThaumCraft config class!");
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not read field from ThaumCraft config class!");
				e.printStackTrace();
			}
		}
		else {
			this.noMod();
		}
		taintBiomeID = idtaint;
		eerieBiomeID = ideerie;
		magicBiomeID = idmagic;
	}

	public static ThaumBiomeHandler getInstance() {
		return instance;
	}

	@Override
	public boolean initializedProperly() {
		return taintBiomeID != -1 && eerieBiomeID != -1 && magicBiomeID != -1;
	}

	@Override
	public ModList getMod() {
		return ModList.THAUMCRAFT;
	}

}
