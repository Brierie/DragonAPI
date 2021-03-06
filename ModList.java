/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2015
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.DragonAPI;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import Reika.DragonAPI.Libraries.Java.ReikaJavaLibrary;
import Reika.DragonAPI.Libraries.Java.ReikaStringParser;
import cpw.mods.fml.common.Loader;

public enum ModList {

	ROTARYCRAFT("RotaryCraft", "Reika.RotaryCraft.RotaryCraft"),
	REACTORCRAFT("ReactorCraft"),
	EXPANDEDREDSTONE("ExpandedRedstone"),
	GEOSTRATA("GeoStrata"),
	FURRYKINGDOMS("FurryKingdoms"),
	CRITTERPET("CritterPet"),
	VOIDMONSTER("VoidMonster"),
	USEFULTNT("UsefulTNT"),
	METEORCRAFT("MeteorCraft"),
	JETPLANE("JetPlane"),
	CAVECONTROL("CaveControl"),
	LEGACYCRAFT("LegacyCraft"),
	ELECTRICRAFT("ElectriCraft"),
	CHROMATICRAFT("ChromatiCraft"),
	BCENERGY("BuildCraft|Energy", "buildcraft.BuildCraftEnergy"),
	BCFACTORY("BuildCraft|Factory", "buildcraft.BuildCraftFactory"),
	BCTRANSPORT("BuildCraft|Transport", "buildcraft.BuildCraftTransport"),
	BCSILICON("BuildCraft|Silicon", "buildcraft.BuildCraftSilicon"),
	THAUMCRAFT("Thaumcraft", "thaumcraft.common.config.ConfigBlocks", "thaumcraft.common.config.ConfigItems"),
	IC2("IC2", "ic2.core.Ic2Items"), //ensure still here
	GREGTECH("gregtech"),
	FORESTRY("Forestry", "forestry.core.config.ForestryBlock", "forestry.core.config.ForestryItem"),
	APPENG("appliedenergistics2"), //appeng.api.definitions
	MFFS("MFFS", "mffs.ModularForceFieldSystem"), //ensure still here
	REDPOWER("RedPower"),
	TWILIGHT("TwilightForest", "twilightforest.block.TFBlocks", "twilightforest.item.TFItems"),
	NATURA("Natura", "mods.natura.common.NContent"),
	BOP("BiomesOPlenty", "biomesoplenty.api.content.BOPCBlocks", "biomesoplenty.api.content.BOPCItems"),
	BXL("ExtraBiomesXL"),
	MINEFACTORY("MineFactoryReloaded", "powercrystals.minefactoryreloaded.setup.MFRThings"),
	DARTCRAFT("DartCraft", "bluedart.Blocks.DartBlock", "bluedart.Items.DartItem"), //ensure still here
	TINKERER("TConstruct", "tconstruct.world.TinkerWorld"), //tconstruct.library.TConstructRegistry.getBlock/Item
	THERMALEXPANSION("ThermalExpansion", "thermalexpansion.block.TEBlocks", "thermalexpansion.item.TEItems"),
	THERMALFOUNDATION("ThermalFoundation", "thermalfoundation.block.TFBlocks", "thermalfoundation.item.TFItems"),
	MEKANISM("Mekanism", "mekanism.common.Mekanism"),
	MEKTOOLS("MekanismTools", "mekanism.tools.common.MekanismTools"), //ensure still here
	RAILCRAFT("Railcraft", "mods.railcraft.common.blocks.RailcraftBlocks", null), //items spread over half a dozen classes
	ICBM("ICBM|Explosion"),
	ARSMAGICA("arsmagica2", "am2.blocks.BlocksCommonProxy", "am2.items.ItemsCommonProxy"), //ensure still here
	TRANSITIONAL("TransitionalAssistance", "modTA.Core.TACore"), //mod dead
	ENDERSTORAGE("EnderStorage"),
	TREECAPITATOR("TreeCapitator"),
	HARVESTCRAFT("harvestcraft", "com.pam.harvestcraft.BlockRegistry", "com.pam.harvestcraft.ItemRegistry"),
	MYSTCRAFT("Mystcraft", "com.xcompwiz.mystcraft.api.MystObjects"),
	MAGICCROPS("magicalcrops", "com.mark719.magicalcrops.MagicalCrops"),
	MIMICRY("Mimicry", "com.sparr.mimicry.block.MimicryBlock", "com.sparr.mimicry.item.MimicryItem"),
	QCRAFT("QuantumCraft", "dan200.QCraft"),
	OPENBLOCKS("OpenBlocks", "openblocks.OpenBlocks$Blocks", "openblocks.OpenBlocks$Items"),
	FACTORIZATION("factorization", "factorization.common.Registry"),
	UE("UniversalElectricity"),
	EXTRAUTILS("ExtraUtilities", "com.rwtema.extrautils.ExtraUtils"),
	POWERSUITS("powersuits", "net.machinemuse.powersuits.common.ModularPowersuits"), //ensure still here
	ARSENAL("Redstone Arsenal", "redstonearsenal.item.RAItems"),
	EMASHER("emashercore", "emasher.core.EmasherCore"), //ensure still here
	HIGHLANDS("Highlands", "highlands.api.HighlandsBlocks"),
	PROJRED("ProjRed|Core"),
	WITCHERY("witchery", "com.emoniph.witchery.WitcheryBlocks", "com.emoniph.witchery.WitcheryItems"),
	GALACTICRAFT("GalacticraftCore", "micdoodle8.mods.galacticraft.core.blocks.GCBlocks", "micdoodle8.mods.galacticraft.core.items.GCItems"),
	MULTIPART("McMultipart"),
	OPENCOMPUTERS("OpenComputers"),
	NEI("NotEnoughItems"),
	ATG("ATG"),
	WAILA("Waila"),
	BLUEPOWER("bluepower", "com.bluepowermod.init.BPBlocks", "com.bluepowermod.init.BPItems"),
	COLORLIGHT("easycoloredlights"),
	ENDERIO("EnderIO", "crazypants.enderio.EnderIO"),
	COMPUTERCRAFT("ComputerCraft", "dan200.ComputerCraft"),
	ROUTER("RouterReborn", "router.reborn.RouterReborn"),
	PNEUMATICRAFT("PneumaticCraft", "pneumaticCraft.common.block.Blockss", "pneumaticCraft.common.item.Itemss"),
	PROJECTE("ProjectE", "moze_intel.projecte.gameObjs.ObjHandler"),
	BLOODMAGIC("AWWayofTime", "WayofTime.alchemicalWizardry.ModBlocks", "WayofTime.alchemicalWizardry.ModItems"),
	LYCANITE("lycanitesmobs"),
	CRAFTMANAGER("zcraftingmanager"),
	MINECHEM("minechem"),
	TFC("terrafirmacraft");

	private final boolean condition;
	public final String modLabel;
	private final String itemClass;
	private final String blockClass;

	//To save on repeated Class.forName
	private static final EnumMap<ModList, Class> blockClasses = new EnumMap(ModList.class);
	private static final EnumMap<ModList, Class> itemClasses = new EnumMap(ModList.class);
	private static final HashMap<String, ModList> modIDs = new HashMap();

	private static final Class liteClass;
	private static final Class optiClass;

	public static final ModList[] modList = values();

	private ModList(String label, String blocks, String items) {
		modLabel = label;
		boolean c = Loader.isModLoaded(modLabel);
		condition = c;
		itemClass = items;
		blockClass = blocks;
		if (c) {
			ReikaJavaLibrary.pConsole("DRAGONAPI: "+this+" detected in the MC installation. Adjusting behavior accordingly.");
		}
		else
			ReikaJavaLibrary.pConsole("DRAGONAPI: "+this+" not detected in the MC installation. No special action taken.");

		if (c) {
			ReikaJavaLibrary.pConsole("DRAGONAPI: Attempting to load data from "+this);
			if (blocks == null)
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not block class for "+this+": Specified class was null. This may not be an error.");
			if (items == null)
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not item class for "+this+": Specified class was null. This may not be an error.");
		}
	}

	private ModList(String label, String modClass) {
		this(label, modClass, modClass);
	}

	private ModList(String label) {
		this(label, null);
	}

	public Class getBlockClass() {
		if (blockClass == null) {
			ReikaJavaLibrary.pConsole("DRAGONAPI: Could not load block class for "+this+". Null class provided.");
			ReikaJavaLibrary.dumpStack();
			return null;
		}
		Class c = blockClasses.get(this);
		if (c == null) {
			try {
				c = Class.forName(blockClass);
				blockClasses.put(this, c);
				return c;
			}
			catch (ClassNotFoundException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not load block class for "+this+".");
				e.printStackTrace();
				return null;
			}
		}
		return c;
	}

	public Class getItemClass() {
		if (itemClass == null) {
			ReikaJavaLibrary.pConsole("DRAGONAPI: Could not load item class for "+this+". Null class provided.");
			ReikaJavaLibrary.dumpStack();
			return null;
		}
		Class c = itemClasses.get(this);
		if (c == null) {
			try {
				c = Class.forName(itemClass);
				itemClasses.put(this, c);
				return c;
			}
			catch (ClassNotFoundException e) {
				ReikaJavaLibrary.pConsole("DRAGONAPI: Could not load item class for "+this+".");
				e.printStackTrace();
				return null;
			}
		}
		return c;
	}

	public boolean isLoaded() {
		return condition;
	}

	public String getModLabel() {
		return modLabel;
	}

	public String getDisplayName() {
		if (this.isReikasMod())
			return modLabel;
		return ReikaStringParser.capFirstChar(this.name());
	}

	@Override
	public String toString() {
		return this.getModLabel();
	}

	public boolean isReikasMod() {
		return this.ordinal() <= ELECTRICRAFT.ordinal();
	}

	public static List<ModList> getReikasMods() {
		List<ModList> li = new ArrayList();
		for (int i = 0; i < modList.length; i++) {
			ModList mod = modList[i];
			if (mod.isReikasMod())
				li.add(mod);
		}
		return li;
	}

	public static ModList getModFromID(String id) {
		if (modIDs.containsKey(id))
			return modIDs.get(id);
		else {
			for (int i = 0; i < modList.length; i++) {
				ModList mod = modList[i];
				if (mod.modLabel.equals(id)) {
					modIDs.put(id, mod);
					return mod;
				}
			}
			modIDs.put(id, null);
			return null;
		}
	}

	public static boolean liteLoaderInstalled() {
		return liteClass != null;
	}

	public static boolean optifineInstalled() {
		return optiClass != null;
	}

	static {
		Class c = null;
		try {
			c = Class.forName("com.mumfrey.liteloader.core.LiteLoader");
			ReikaJavaLibrary.pConsole("DRAGONAPI: LiteLoader detected. Loading compatibility features.");
			ReikaJavaLibrary.pConsole("\t\tNote that some parts of the game, especially sounds and textures, may error out.");
			ReikaJavaLibrary.pConsole("\t\tTry reloading resources (F3+T) to fix this.");
		}
		catch (ClassNotFoundException e) {
			ReikaJavaLibrary.pConsole("DRAGONAPI: LiteLoader not detected.");
		}
		liteClass = c;

		c = null;
		try {
			c = Class.forName("optifine.OptiFineTweaker");
			ReikaJavaLibrary.pConsole("DRAGONAPI: Optifine detected. Loading compatibility features.");
			ReikaJavaLibrary.pConsole("\t\tNote that some parts of the game, especially rendering and textures, may error out.");
			ReikaJavaLibrary.pConsole("\t\tTry reloading resources (F3+T) to fix this.");
		}
		catch (ClassNotFoundException e) {
			ReikaJavaLibrary.pConsole("DRAGONAPI: Optifine not detected.");
		}
		optiClass = c;
	}

}
