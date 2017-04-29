package org.golde.forge.api.capeapi.events;

import java.util.UUID;

import org.golde.forge.api.capeapi.AConstants;
import org.golde.forge.api.capeapi.Loader;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JoinListener {

	@SubscribeEvent
	public void onJoin(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof EntityPlayer) {
			EntityPlayer entityPlayer = (EntityPlayer) event.getEntity();
			UUID uuid = entityPlayer.getGameProfile().getId();
			
			System.out.println(AConstants.LOG + "Try to load player renders (uuid=" + uuid +")");
			Loader.loadCape(uuid);
			Loader.loadElytra(uuid);
			Loader.loadEars(uuid);
		}
	}
	
}
