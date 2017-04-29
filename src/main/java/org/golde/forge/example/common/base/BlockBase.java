package org.golde.forge.example.common.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBase extends Block{

	public BlockBase(String name) {
		this(Material.IRON, name, true);
	}
	
	public BlockBase(String name, boolean inTab) {
		this(Material.IRON, name, inTab);
	}

	public BlockBase(String name, Material material) {
		this(material, name, true);

	}
	
	public BlockBase(String name, boolean inTab, Material material) {
		this(material, name, inTab);

	}

	public BlockBase(Material material, String name, boolean inTab) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		//setCreativeTab(CREATIVE_TAB);
		
		this.setHardness(3.0F);
		this.setResistance(10f);
		this.setHarvestLevel("pickaxe", 1);
		
		setSoundType(SoundType.METAL);
		
		GameRegistry.register(this);
		ItemBlock item = new ItemBlock(this);
		item.setRegistryName(this.getRegistryName());
		GameRegistry.register(item);
	}
	
	//@SideOnly(Side.CLIENT)
	public void registerRender() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
	}

	/*@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
