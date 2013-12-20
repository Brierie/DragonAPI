/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI.ModInteract;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Base.ModHandlerBase;
import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;

public class TinkerOreHandler extends ModHandlerBase {

	private static final TinkerOreHandler instance = new TinkerOreHandler();

	public final int gravelOreID;
	public final int stoneOreID;

	private TinkerOreHandler() {
		super();
		int idgravel = -1;
		int idnether = -1;

		if (this.hasMod()) {
			try {
				Class tink = ModList.TINKERER.getBlockClass();
				Field gravel = tink.getField("oreGravel");
				idgravel = ((Block)gravel.get(null)).blockID;

				Field ore = tink.getField("oreSlag");
				idnether = ((Block)gravel.get(null)).blockID;
			}
			catch (NoSuchFieldException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: "+this.getMod()+" field not found! "+e.getMessage());
				e.printStackTrace();
			}
			catch (SecurityException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Cannot read "+this.getMod()+" (Security Exception)! "+e.getMessage());
				e.printStackTrace();
			}
			catch (IllegalArgumentException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Illegal argument for reading "+this.getMod()+"!");
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Illegal access exception for reading "+this.getMod()+"!");
				e.printStackTrace();
			}
			catch (NullPointerException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Null pointer exception for reading "+this.getMod()+"! Was the class loaded?");
				e.printStackTrace();
			}
		}
		else {
			this.noMod();
		}

		gravelOreID = idgravel;
		stoneOreID = idnether;
	}

	public static TinkerOreHandler getInstance() {
		return instance;
	}

	@Override
	public boolean initializedProperly() {
		return gravelOreID != -1 && stoneOreID != -1;
	}

	@Override
	public ModList getMod() {
		return ModList.TINKERER;
	}

	public boolean isGravelOre(ItemStack block) {
		if (!this.initializedProperly())
			return false;
		return block.itemID == gravelOreID;
	}

	public boolean isNetherOre(ItemStack block) {
		if (!this.initializedProperly())
			return false;
		return block.itemID == stoneOreID && block.getItemDamage() < 3;
	}

}
