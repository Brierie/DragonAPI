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

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import Reika.DragonAPI.ModList;
import Reika.DragonAPI.Instantiable.Data.Collections.OneWayCollections.OneWaySet;
import Reika.DragonAPI.Instantiable.Data.Immutable.ImmutableArray;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/** Register progression/balance-sensitive fluids here to blacklist other mods from adding shortcuts to obtain them. */
public final class SensitiveFluidRegistry {

	public static final SensitiveFluidRegistry instance = new SensitiveFluidRegistry();

	private final OneWaySet<Fluid> forbiddenFluids = new OneWaySet();

	private Class fluidCowClass;
	private Field fluidCowField;

	private SensitiveFluidRegistry() {
		MinecraftForge.EVENT_BUS.register(this);

		try {
			fluidCowClass = Class.forName("com.robrit.moofluids.common.entity.EntityFluidCow");
			fluidCowField = fluidCowClass.getDeclaredField("entityFluid");
			fluidCowField.setAccessible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void registerFluid(String fluid) {
		this.registerFluid(FluidRegistry.getFluid(fluid));
	}

	public void registerFluid(Fluid fluid) {
		for (int i = 0; i < Interactions.list.length; i++) {
			Interactions it = Interactions.list.get(i);
			if (it.isLoaded) {
				it.blacklist(fluid);
			}
		}
		forbiddenFluids.add(fluid);
	}

	@SubscribeEvent
	public void stopCows(EntityJoinWorldEvent evt) {
		Entity e = evt.entity;
		if (e.getClass() == fluidCowClass) {
			Fluid f = this.getCowFluid(e);
			if (forbiddenFluids.contains(f)) {
				evt.setCanceled(true);
				EntityCow repl = new EntityCow(evt.world);
				repl.setLocationAndAngles(e.posX, e.posY, e.posZ, e.rotationYaw, e.rotationPitch);
				repl.setHealth(((EntityCow)e).getHealth());
				repl.setGrowingAge(((EntityCow)e).getGrowingAge());
				evt.world.spawnEntityInWorld(repl);
			}
		}
	}

	private Fluid getCowFluid(Entity e) {
		try {
			return (Fluid)fluidCowField.get(e);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static enum Interactions {
		MYSTCRAFT(ModList.MYSTCRAFT.isLoaded()),
		MOOFLUID(Loader.isModLoaded("MooFluids"));

		private final boolean isLoaded;

		private static final ImmutableArray<Interactions> list = new ImmutableArray(values());

		private Interactions(boolean b) {
			isLoaded = b;
		}

		private void blacklist(Fluid fluid) {
			switch(this) {
			case MYSTCRAFT:
				ReikaMystcraftHelper.disableFluidPage(fluid.getName());
				break;
			case MOOFLUID:
				break;
			}
		}
	}

}
