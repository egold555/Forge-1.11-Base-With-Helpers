package org.golde.forge.api.capeapi;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class Loader {

	private static Map < UUID, ResourceLocation > capes = new HashMap <UUID, ResourceLocation> ();
	private static Map < UUID, ResourceLocation > elytras = new HashMap <UUID, ResourceLocation> ();
	private static Map < UUID, ResourceLocation > ears = new HashMap <UUID, ResourceLocation> ();
	public static Map < UUID, String > urls_Capes = new HashMap <UUID, String> ();
	public static Map < UUID, String > urls_Elytras = new HashMap <UUID, String> ();
	public static Map < UUID, String > urls_Ears = new HashMap <UUID, String> ();

	public static void loadCape(final UUID uuid) {
		String url = urls_Capes.get(uuid);
		if(url == null) {return;}
		final ResourceLocation resourceLocation = new ResourceLocation(AConstants.NAME + "/capes/" + uuid.toString() + ".png");
		TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

		IImageBuffer iImageBuffer = new IImageBuffer() {

			@Override
			public BufferedImage parseUserSkin(BufferedImage image) {
				return image;
			}

			@Override
			public void skinAvailable() {
				capes.put(uuid, resourceLocation);
			}
		};

		ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData((File) null, url, (ResourceLocation) null, iImageBuffer);
		textureManager.loadTexture(resourceLocation, threadDownloadImageData);
	}
	
	public static void loadElytra(final UUID uuid) {
		String url = urls_Elytras.get(uuid);
		if(url == null) {return;}
		final ResourceLocation resourceLocation = new ResourceLocation(AConstants.NAME + "/elytras/" + uuid.toString() + ".png");
		TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

		IImageBuffer iImageBuffer = new IImageBuffer() {

			@Override
			public BufferedImage parseUserSkin(BufferedImage image) {
				return image;
			}

			@Override
			public void skinAvailable() {
				elytras.put(uuid, resourceLocation);
			}
		};

		ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData((File) null, url, (ResourceLocation) null, iImageBuffer);
		textureManager.loadTexture(resourceLocation, threadDownloadImageData);
	}
	
	public static void loadEars(final UUID uuid) {
		String url = urls_Ears.get(uuid);
		if(url == null) {return;}
		final ResourceLocation resourceLocation = new ResourceLocation(AConstants.NAME + "/ears/" + uuid.toString() + ".png");
		TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

		IImageBuffer iImageBuffer = new IImageBuffer() {

			@Override
			public BufferedImage parseUserSkin(BufferedImage image) {
				return image;
			}

			@Override
			public void skinAvailable() {
				ears.put(uuid, resourceLocation);
			}
		};

		ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData((File) null, url, (ResourceLocation) null, iImageBuffer);
		textureManager.loadTexture(resourceLocation, threadDownloadImageData);
	}

	public static void deleteCape(UUID uuid) {
		capes.remove(uuid);
	}
	
	public static void deleteElytra(UUID uuid) {
		elytras.remove(uuid);
	}
	
	public static void deleteEars(UUID uuid) {
		ears.remove(uuid);
	}

	public static ResourceLocation getCape(UUID uuid) {
		return capes.containsKey(uuid) ? capes.get(uuid) : null;
	}
	
	
	public static ResourceLocation getElytra(UUID uuid) {
		return elytras.containsKey(uuid) ? elytras.get(uuid) : null;
	}
	
	public static ResourceLocation getEars(UUID uuid) {
		return ears.containsKey(uuid) ? ears.get(uuid) : null;
	}

	public static boolean hasCape(UUID uuid) {
		return capes.containsKey(uuid);
	}
	
	public static boolean hasElytra(UUID uuid) {
		return elytras.containsKey(uuid);
	}
	
	public static boolean hasEars(UUID uuid) {
		return ears.containsKey(uuid);
	}

	public static Map<UUID, ResourceLocation> getCapes() {
		return capes;
	}
	
	public static Map<UUID, ResourceLocation> getElytras() {
		return elytras;
	}
	
	public static Map<UUID, ResourceLocation> getEars() {
		return ears;
	}
	
}
