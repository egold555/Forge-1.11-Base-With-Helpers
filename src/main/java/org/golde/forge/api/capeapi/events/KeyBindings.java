package org.golde.forge.api.capeapi.events;

import org.golde.forge.api.capeapi.AConstants;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {

	private static KeyBinding reloadKey;
	
	public static void init() {
    	reloadKey = new KeyBinding("Reload Capes", Keyboard.KEY_END, AConstants.NAME);
    	ClientRegistry.registerKeyBinding(reloadKey);
	}
	
	public static KeyBinding getReloadKey() {
		return reloadKey;
	}
}
