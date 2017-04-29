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
	}


	private String capeUrl = "http://bit.ly/2pByn5N";
	private String elytraUrl = "http://bit.ly/2pByn5N";
	private String earsUrl = "http://textures.minecraft.net/texture/48db3fbf18e5f0ef266b47f5e6b54126a55f331558ff34cb17468753d84d096";
	/**
	 * %uuid% gets replaced with uuid of player
	 * @param url
	 */
	public void setCapeUrl(String url) {
		capeUrl = url;
	}

	public void setElytraUrl(String url) {
		elytraUrl = url;
	}
	
	public void setEarsUrl(String url) {
		earsUrl = url;
	}

	private boolean[] wsr =  {true, true, false};
	public void setWhatShouldRender(boolean cape, boolean elytra, boolean ears) {
		wsr = new boolean[] {cape, elytra, ears};
	}
	
	public boolean[] getWhatSHouldRender() {
		return wsr;
	}

	public String[] getUrls(UUID uuid) {
		capeUrl = capeUrl.replace("%uuid%", uuid.toString());
		capeUrl = capeUrl.replace("%UUID%", uuid.toString());

		elytraUrl = elytraUrl.replace("%uuid%", uuid.toString());
		elytraUrl = elytraUrl.replace("%UUID%", uuid.toString());
		
		earsUrl = earsUrl.replace("%uuid%", uuid.toString());
		earsUrl = earsUrl.replace("%UUID%", uuid.toString());
		
		return new String[] {capeUrl, elytraUrl, earsUrl};
	}

}
