package org.golde.forge.api.capeapi.render;

import org.golde.forge.api.capeapi.AConstants;
import org.golde.forge.api.capeapi.EricsCapeAPI;
import org.golde.forge.api.capeapi.Loader;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelElytra;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class LayerElytra implements LayerRenderer{

	private final RenderPlayer playerRenderer;
	private final ModelElytra modelElytra = new ModelElytra();

	public LayerElytra(RenderPlayer playerRendererIn)
	{
		this.playerRenderer = playerRendererIn;
		System.out.println(AConstants.LOG + "Created LayerElytra (playerRenderer=" + playerRendererIn + ")");
	}

	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entitylivingbaseIn.hasPlayerInfo() && !entitylivingbaseIn.isInvisible() && Loader.hasElytra(entitylivingbaseIn.getGameProfile().getId())) {

			ItemStack itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
			if ((itemstack != null) && (itemstack.getItem() == Items.ELYTRA)){
				if(!EricsCapeAPI.getInstance().getWhatSHouldRender()[1]) {
					return;
				}
				 GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				 GlStateManager.pushMatrix();
				 playerRenderer.bindTexture(Loader.getElytra(entitylivingbaseIn.getGameProfile().getId()));
				 GlStateManager.translate(0.0F, 0.0F, 0.125F);
				 modelElytra.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
				 modelElytra.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				 GlStateManager.popMatrix();
			}
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}
	
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale)
	{
		this.doRenderLayer((AbstractClientPlayer)entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);
	}

}
