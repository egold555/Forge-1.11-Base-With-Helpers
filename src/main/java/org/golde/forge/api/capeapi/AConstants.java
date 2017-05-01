package org.golde.forge.api.capeapi;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class AConstants {

	private static Minecraft mc;

	static {
		mc = Minecraft.getMinecraft();
	}
	
	public static String NAME = "EricsCapeAPI";
	public static String LOG = "[" + NAME + "] ";
	public static String MC_VERSION = "1.11.2";
	
	
	public static Minecraft getMinecraft() {
		return mc;
	}
	
	public static EntityPlayerSP getPlayer() {
		return mc.player;
	}
	
	public static ScaledResolution getScaledResolution() {
		return new ScaledResolution(mc);
	}
	
	public static FontRenderer getFontRenderer() {
		return mc.fontRenderer;
	}
	
}
