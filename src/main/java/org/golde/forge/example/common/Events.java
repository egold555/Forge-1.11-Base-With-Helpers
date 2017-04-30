package org.golde.forge.example.common;

import org.golde.forge.api.renderapi.RenderIconFont;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class Events {

	@SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
		String all = "";
		for(RenderIconFont icon:RenderIconFont.values()) {
			all += icon.getValue();
		}
		event.player.sendMessage(new TextComponentString(all));
	}
	
}
