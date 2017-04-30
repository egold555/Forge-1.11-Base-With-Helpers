package org.golde.forge.example.common.base;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class TileEntityRenderBase extends TileEntitySpecialRenderer{

	public TileEntityRenderBase(Class tile) {
		ClientRegistry.bindTileEntitySpecialRenderer(tile, this);
	}
	
}
