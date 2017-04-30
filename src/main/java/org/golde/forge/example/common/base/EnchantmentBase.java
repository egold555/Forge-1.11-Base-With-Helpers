package org.golde.forge.example.common.base;

import org.golde.forge.example.Constants;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public abstract class EnchantmentBase extends Enchantment{
	protected EnchantmentBase(Rarity rarityIn, EnumEnchantmentType typeIn, EntityEquipmentSlot[] slots) {
		super(rarityIn, typeIn, slots);
		super.setName(Constants.PREFIX_MOD + getUnlocalizedName());
	}

	public abstract String getUnlocalizedName();
    public abstract int getMinEnchantability(int enchantmentLevel);
    public abstract int getMaxEnchantability(int enchantmentLevel);
    public abstract int getMaxLevel();
}
