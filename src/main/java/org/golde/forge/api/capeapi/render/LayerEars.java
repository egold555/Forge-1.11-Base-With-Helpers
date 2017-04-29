package org.golde.forge.api.capeapi.render;

import org.golde.forge.api.capeapi.AConstants;
import org.golde.forge.api.capeapi.EricsCapeAPI;
import org.golde.forge.api.capeapi.Loader;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class LayerEars implements LayerRenderer{

	private final RenderPlayer playerRenderer;

    public LayerEars(RenderPlayer playerRendererIn)
    {
        this.playerRenderer = playerRendererIn;
        System.out.println(AConstants.LOG + "Created LayerEars (playerRenderer=" + playerRendererIn + ")");
    }
	
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		if (entitylivingbaseIn.hasPlayerInfo() && !entitylivingbaseIn.isInvisible() && Loader.hasEars(entitylivingbaseIn.getGameProfile().getId())) {
			for (int i = 0; i < 2; i++)
			{
				
				if(!EricsCapeAPI.getInstance().getWhatSHouldRender()[2]) {
					return;
				}
				this.playerRenderer.bindTexture(Loader.getEars(entitylivingbaseIn.getGameProfile().getId()));
				float f2 = (entitylivingbaseIn.prevRotationYaw + (entitylivingbaseIn.rotationYaw - entitylivingbaseIn.prevRotationYaw) * partialTicks) - (entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks);
				float f3 = entitylivingbaseIn.prevRotationPitch + (entitylivingbaseIn.rotationPitch - entitylivingbaseIn.prevRotationPitch) * partialTicks;
				GlStateManager.pushMatrix();
				GlStateManager.rotate(f2, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
				GlStateManager.translate(0.375F * (float)(i * 2 - 1), 0.0F, 0.0F);
				GlStateManager.translate(0.0F, -0.375F, 0.0F);
				GlStateManager.rotate(-f3, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(-f2, 0.0F, 1.0F, 0.0F);
				float f8 = 1.333333F;
				GlStateManager.scale(f8, f8, f8);
				this.playerRenderer.getMainModel().renderDeadmau5Head(0.0625F);
				GlStateManager.popMatrix();
			}
		}
	}
	
	@Override
	public boolean shouldCombineTextures() {
		return true;
	}
	
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float p_177141_2_, float p_177141_3_, float partialTicks, float p_177141_5_, float p_177141_6_, float p_177141_7_, float scale)
	{
		this.doRenderLayer((AbstractClientPlayer)entitylivingbaseIn, p_177141_2_, p_177141_3_, partialTicks, p_177141_5_, p_177141_6_, p_177141_7_, scale);
	}
	
}
