package org.golde.forge.example.proxy;

import org.golde.forge.example.common.Events;
import org.golde.forge.example.common.registry.ExampleBlocks;
import org.golde.forge.example.common.registry.ExampleItems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

	/*
	 * Blocks
	 * Items
	 * Entities
	 * Ore Dictionary
	 */
	public void preInit(FMLPreInitializationEvent event) {
		ExampleBlocks.register();
		ExampleItems.register();
	}

	/*
	 * World Generators
	 * Recipes
	 * Events
	 * Sending IMC Messages
	 */
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new Events());
	}

	/*
	 * Mod compatibility
	 */
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	/*
	 * Mod compatibility
	 * (Gets called after Post init)
	 */
	public void finalInit(FMLPostInitializationEvent event) {
		
	}

	/*
	 * Process received IMC Messages
	 * Register Commands
	 */
	public void serverStarting(FMLServerStartingEvent event) {
		
	}

}

