package org.golde.forge.example.common.base;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityBase extends TileEntity{

	public TileEntityBase(String name) {
		GameRegistry.registerTileEntity(TileEntityBase.class, name);
	}
	
}
