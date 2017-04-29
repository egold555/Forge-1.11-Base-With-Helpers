package org.golde.forge.example.common.base;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemBase extends Item{

	public ItemBase(String name){
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		//setCreativeTab(tab)
		GameRegistry.register(this);
	}
	
	public void registerRender(){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}
	
}
