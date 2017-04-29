package org.golde.forge.api.capeapi.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.golde.forge.api.capeapi.AConstants;
import org.golde.forge.api.capeapi.Loader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class KeyListener {

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event) {
		if(KeyBindings.getReloadKey() != null && KeyBindings.getReloadKey().isPressed()) {
			List<UUID> uuids = new ArrayList<UUID>(Loader.getCapes().keySet());

			for(Entity entity : AConstants.getMinecraft().world.loadedEntityList)
				if(entity instanceof EntityPlayer && ((EntityPlayer) entity).getGameProfile() != null &&!uuids.contains(((EntityPlayer) entity).getGameProfile().getId()))
					uuids.add(((EntityPlayer) entity).getGameProfile().getId());

			Loader.getCapes().clear();
			for(UUID uuid : uuids) {
				Loader.loadCape(uuid);
				Loader.loadElytra(uuid);
				Loader.loadEars(uuid);
			}
		}
	}

}
