package org.golde.forge.api.renderapi;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;

public class RenderGL {
	/**
     * Applies a color to GL state based on the current tick. This is the same code used by
     * sheeps for their jeb color effect.
     * 
     * @param entity The entity to render the effect on.
     * @param partialTicks The partial ticks.
     */
    public static void colorRainbow (EntityLivingBase entity, float partialTicks) {
        
        rainbowColor(entity.ticksExisted, entity.getEntityId(), partialTicks);
    }
    
    /**
     * Applies a color to GL state based on the current tick. This is similar to the code used
     * by jeb sheeps.
     * 
     * @param previousTicks The previous tick value.
     * @param offset An offset value.
     * @param partialTicks The partial ticks.
     */
    public static void rainbowColor (int previousTicks, int offset, float partialTicks) {
        
        final int ticks = previousTicks / 25 + offset;
        final int colorCount = EnumDyeColor.values().length;
        final int colorMeta1 = ticks % colorCount;
        final int colorMeta2 = (ticks + 1) % colorCount;
        final float f = (previousTicks % 25 + partialTicks) / 25.0F;
        final float[] color1 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(colorMeta1));
        final float[] color2 = EntitySheep.getDyeRgb(EnumDyeColor.byMetadata(colorMeta2));
        GlStateManager.color(color1[0] * (1.0F - f) + color2[0] * f, color1[1] * (1.0F - f) + color2[1] * f, color1[2] * (1.0F - f) + color2[2] * f);
    }
}
