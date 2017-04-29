package org.golde.forge.api.capeapi.events;

import org.golde.forge.api.capeapi.AConstants;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Render2DListener {

	@SubscribeEvent
	public void onRender2D(RenderGameOverlayEvent event) {
		if(event.getType() == ElementType.TEXT) {
			if(!AConstants.getMinecraft().gameSettings.showDebugInfo)
				return;
			
			ScaledResolution scaledResolution = AConstants.getScaledResolution();
			AConstants.getFontRenderer().drawString("> " + AConstants.NAME + " <", 2, scaledResolution.getScaledHeight() - (AConstants.getFontRenderer().FONT_HEIGHT * 3) - 2, Integer.MAX_VALUE);
			//Constants.getFontRenderer().drawString("Cape available: " + CapeLoader.hasCape(Constants.getMinecraft().player.getGameProfile().getId()), 2, scaledResolution.getScaledHeight() - (Constants.getFontRenderer().FONT_HEIGHT * 2) - 2, Integer.MAX_VALUE);
			//Constants.getFontRenderer().drawString("Loaded capes: " + CapeLoader.getCapes().size(), 2, scaledResolution.getScaledHeight() - Constants.getFontRenderer().FONT_HEIGHT - 2, Integer.MAX_VALUE);
		}
	}
	
}
