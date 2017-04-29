package org.golde.forge.api.renderapi;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class RenderIcons {

	private static final int ROW_1 = 0;
	private static final int ROW_2 = 9;
	private static final int ROW_3 = 18;
	private static final int ROW_4 = 27;
	private static final int ROW_5 = 36;
	private static final int ROW_6 = 45;

	static enum Icon{

		FOOD_BAR_EMPTY_1(9, 9, 16, ROW_4),
		FOOD_BAR_EMPTY_2(9, 9, 25, ROW_4),
		FOOD_BAR_EMPTY_3(9, 9, 34, ROW_4),
		FOOD_BAR_EMPTY_4(9, 9, 43, ROW_4),
		FOOD_BAR_FULL_1(9, 9, 52, ROW_4),
		FOOD_BAR_HALF_FULL(9, 9, 61, ROW_4),
		FOOD_BAR_FULL_2(9, 9, 70, ROW_4),
		FOOD_BAR_HALF_FULL_2(9, 9, 79, ROW_4),
		FOOD_BAR_FULL_3(9, 9, 88, ROW_4),
		FOOD_BAR_HALF_FULL_3(9, 9, 97, ROW_4),
		FOOD_BAR_FULL_4(9, 9, 106, ROW_4),
		FOOD_BAR_HALF_FULL_4(9, 9, 115, ROW_4),
		FOOD_BAR_EMPTY_5(9, 9, 124, ROW_4),
		FOOD_BAR_EMPTY_6(9, 9, 133, ROW_4),
		FOOD_BAR_FULL_LEFT(9, 9, 16, ROW_5),

		HEART_EMPTY_1(9, 9, 16, ROW_1),
		HEART_EMPTY_2(9, 9, 25, ROW_1),
		HEART_EMPTY_3(9, 9, 34, ROW_1),
		HEART_EMPTY_4(9, 9, 43, ROW_1),
		HEART_FULL_1(9, 9, 52, ROW_1),
		HEART_HALF_FULL_1(9, 9, 61, ROW_1),
		HEART_FULL_2(9, 9, 70, ROW_1),
		HEART_HALF_FULL_2(9, 9, 79, ROW_1),
		HEART_FULL_3(9, 9, 88, ROW_1),
		HEART_HALF_FULL_3(9, 9, 97, ROW_1),
		HEART_FULL_4(9, 9, 106, ROW_1),
		HEART_HALF_FULL_4(9, 9, 115, ROW_1),
		HEART_FULL_5(9, 9, 124, ROW_1),
		HEART_HALF_FULL_5(9, 9, 133, ROW_1),
		HEART_FULL_6(9, 9, 142, ROW_1),
		HEART_HALF_FULL_6(9, 9, 151, ROW_1),
		HEART_FULL_7(9, 9, 160, ROW_1),
		HEART_HALF_FULL_7(9, 9, 169, ROW_1),

		HEART_HARDCORE_EMPTY_1(9, 9, 16, ROW_6),
		HEART_HARDCORE_EMPTY_2(9, 9, 25, ROW_6),
		HEART_HARDCORE_EMPTY_3(9, 9, 34, ROW_6),
		HEART_HARDCORE_EMPTY_4(9, 9, 43, ROW_6),
		HEART_HARDCORE_FULL_1(9, 9, 52, ROW_6),
		HEART_HARDCORE_HALF_FULL_1(9, 9, 61, ROW_6),
		HEART_HARDCORE_FULL_2(9, 9, 70, ROW_6),
		HEART_HARDCORE_HALF_FULL_2(9, 9, 79, ROW_6),
		HEART_HARDCORE_FULL_3(9, 9, 88, ROW_6),
		HEART_HARDCORE_HALF_FULL_3(9, 9, 97, ROW_6),
		HEART_HARDCORE_FULL_4(9, 9, 106, ROW_6),
		HEART_HARDCORE_HALF_FULL_4(9, 9, 115, ROW_6),
		HEART_HARDCORE_FULL_5(9, 9, 124, ROW_6),
		HEART_HARDCORE_HALF_FULL_5(9, 9, 133, ROW_6),
		HEART_HARDCORE_FULL_6(9, 9, 142, ROW_6),
		HEART_HARDCORE_HALF_FULL_6(9, 9, 151, ROW_6),
		HEART_HARDCORE_FULL_7(9, 9, 160, ROW_6),
		HEART_HARDCORE_HALF_FULL_7(9, 9, 169, ROW_6),

		ARMOR_EMPTY(9, 9, 16, ROW_2),
		ARMOR_HALF(9, 9, 25, ROW_2),
		ARMOR_FULL1(9, 9, 34, ROW_2),
		ARMOR_FULL2(9, 9, 43, ROW_2),
		HEART_MOB_EMPTY_1(9, 9, 52, ROW_2),
		HEART_MOB_EMPTY_2(9, 9, 61, ROW_2),
		HEART_MOB_EMPTY_3(9, 9, 70, ROW_2),
		HEART_MOB_EMPTY_4(9, 9, 79, ROW_2),
		HEART_MOB_FULL_1(9, 9, 88, ROW_2),
		HEART_HALF_HALF_FULL_1(9, 9, 97, ROW_2),
		HEART_MOB_FULL_2(9, 9, 106, ROW_2),
		HEART_HALF_HALF_FULL_2(9, 9, 115, ROW_2),

		BUBBLE_FULL(9, 9, 16, ROW_3),
		BUBBLE_POP(9, 9, 25, ROW_3),
		BUBBLE_ARMOR_HALF(9, 9, 34, ROW_3),
		BUBBLE_ARMOR_FULL(9, 9, 43, ROW_3),

		SIGNAL_BAR_1(10, 8, 0, 176),
		SIGNAL_BAR_2(10, 8, 0, 184),
		SIGNAL_BAR_3(10, 8, 0, 192),
		SIGNAL_BAR_4(10, 8, 0, 200),
		SIGNAL_BAR_5(10, 8, 0, 208),
		SIGNAL_BAR_SEARCHING_1(10, 8, 10, 176),
		SIGNAL_BAR_SEARCHING_2(10, 8, 10, 184),
		SIGNAL_BAR_SEARCHING_3(10, 8, 10, 192),
		SIGNAL_BAR_SEARCHING_4(10, 8, 10, 200),
		SIGNAL_BAR_SEARCHING_5(10, 8, 10, 208),
		SIGNAL_BAR_X(10, 8, 0, 216),

		;
		int x, y, sx, sy;
		Icon(int sx, int sy, int x, int y){
			this.sx = sx;
			this.sy = sy;
			this.x = x;
			this.y = y;
		}

		public static Icon get(int value) {
			return Arrays.asList(Icon.values()).get(value);
		}
	}

	public static void renderIconSpace(ItemTooltipEvent event, int nudgeRight, int nudgeDown) {
		nudgeRight+=1;
		String s = " ";
		for(int i = 0; i < nudgeDown; i++) {
			for(int j = 0; j < nudgeRight; j++) {
				s += "  ";
			}
			event.getToolTip().add(1, s);
		}


	}

	public static void renderIcon(RenderTooltipEvent.PostText event, Icon icon, int nudgeRight, int nudgeDown) {

		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1F, 1F);
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(GuiIngameForge.ICONS);
		Gui.drawModalRectWithCustomSizedTexture(event.getX() + nudgeRight * 10 - 2, event.getY() + nudgeDown * 9 + 12, icon.x, icon.y, icon.sx, icon.sy, 256, 256);
		//Gui.drawModalRectWithCustomSizedTexture(event.getX() + nudgeRight * 11 - 2, event.getY() + nudgeDown * 11 + 12, icon.x, icon.y, icon.sx, icon.sy, 256, 256);
		GlStateManager.popMatrix();
	}

	/////////////// Food ////////////
	public static void renderFoodSpace(ItemTooltipEvent event) {
		if(!event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof ItemFood) {
			int pips = ((ItemFood) event.getItemStack().getItem()).getHealAmount(event.getItemStack());
			int len = (int) Math.ceil((double) pips / 2);

			String s = " ";
			for(int i = 0; i < len; i++)
				s += "  ";

			event.getToolTip().add(1, s);
		}
	}

	public static void renderFood(RenderTooltipEvent.PostText event) {
		if(event.getStack() != null && event.getStack().getItem() instanceof ItemFood) {
			GlStateManager.pushMatrix();
			GlStateManager.color(1F, 1F, 1F);
			Minecraft mc = Minecraft.getMinecraft();
			mc.getTextureManager().bindTexture(GuiIngameForge.ICONS);
			int pips = ((ItemFood) event.getStack().getItem()).getHealAmount(event.getStack());
			for(int i = 0; i < Math.ceil((double) pips / 2); i++) {
				Gui.drawModalRectWithCustomSizedTexture(event.getX() + i * 9 - 2, event.getY() + 12, 16, 27, 9, 9, 256, 256);

				int u = 52;
				if(pips % 2 != 0 && i == 0)
					u += 9;

				Gui.drawModalRectWithCustomSizedTexture(event.getX() + i * 9 - 2, event.getY() + 12, u, 27, 9, 9, 256, 256);
			}

			GlStateManager.popMatrix();
		}
	}

}
