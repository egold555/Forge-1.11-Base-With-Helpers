package org.golde.forge.api.miscutils;

import java.util.Arrays;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingUtils {

	/**
     * Creates 9 recipes which allow an ItemStack to be converted into a different one. 9
     * recipes to allow up to 9 at a time.
     * 
     * @param input The initial input item.
     * @param output The resulting item.
     */
    public static void createConversionRecipes (ItemStack input, ItemStack output) {
        
        for (int amount = 1; amount < 10; amount++) {
            
            final ItemStack[] inputs = new ItemStack[amount];
            Arrays.fill(inputs, input);
            GameRegistry.addShapelessRecipe(ItemStackUtils.copyStackWithSize(output, amount), (Object[]) inputs);
        }
    }
	
}
