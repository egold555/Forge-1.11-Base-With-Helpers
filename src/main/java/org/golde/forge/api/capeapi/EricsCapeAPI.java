package org.golde.forge.api.capeapi;

import java.util.UUID;

import org.golde.forge.api.capeapi.events.JoinListener;
import org.golde.forge.api.capeapi.events.KeyBindings;
import org.golde.forge.api.capeapi.events.KeyListener;
import org.golde.forge.api.capeapi.events.Render2DListener;
import org.golde.forge.api.capeapi.render.LayerCape;
import org.golde.forge.api.capeapi.render.LayerEars;
import org.golde.forge.api.capeapi.render.LayerElytra;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraftforge.common.MinecraftForge;

public class EricsCapeAPI {

	private static EricsCapeAPI myObj;
	/**
	 * Create private constructor
	 */
	private EricsCapeAPI(){

	}
	/**
	 * Create a static method to get instance.
	 */
	public static EricsCapeAPI getInstance(){
		if(myObj == null){
			myObj = new EricsCapeAPI();
		}
		return myObj;
	}

	String CLIENT_UUID;
	public void clientPostInit() {
		MinecraftForge.EVENT_BUS.register(new JoinListener());
		MinecraftForge.EVENT_BUS.register(new Render2DListener());
		MinecraftForge.EVENT_BUS.register(new KeyListener());

		// Register keybinds
		KeyBindings.init();

		// Enable capes
		AConstants.getMinecraft().gameSettings.setModelPartEnabled(EnumPlayerModelParts.CAPE, true);

		// Add layercape
		for(RenderPlayer renderPlayer : AConstants.getMinecraft().getRenderManager().getSkinMap().values()) {
			renderPlayer.addLayer(new LayerCape(renderPlayer));
			renderPlayer.addLayer(new LayerElytra(renderPlayer));
			renderPlayer.addLayer(new LayerEars(renderPlayer));
		}
		CLIENT_UUID = Minecraft.getMinecraft().getSession().getProfile().getId().toString();
	}

	public static final String EXAMPLE_CAPE = "http://bit.ly/2pByn5N";
	public static final String EXAMPLE_ELYTRA = "http://bit.ly/2pByn5N";
	public static final String EXAMPLE_EARS = "http://textures.minecraft.net/texture/48db3fbf18e5f0ef266b47f5e6b54126a55f331558ff34cb17468753d84d096";

	public void setCapeAndElytraUrl(String uuid, String url) {
		Loader.urls_Capes.put(UUID.fromString(uuid), url);
		Loader.urls_Elytras.put(UUID.fromString(uuid), url);
	}

	public void setCapeUrl(String uuid, String url) {
		Loader.urls_Capes.put(UUID.fromString(uuid), url);
	}

	public void setElytraUrl(String uuid, String url) {
		Loader.urls_Elytras.put(UUID.fromString(uuid), url);
	}

	public void setEarsUrl(String uuid, String url) {
		Loader.urls_Ears.put(UUID.fromString(uuid), url);
	}

}
