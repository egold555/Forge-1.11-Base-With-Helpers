package org.golde.forge.example.common.base;

import java.util.ArrayList;
import java.util.List;

import org.golde.forge.example.Constants;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public class AcievementBase extends Achievement{

	public static final List<Achievement> achievements = new ArrayList<Achievement>();
	
	public AcievementBase(String name, int x, int y, ItemStack icon, Achievement parent) {
		super("achievement." + Constants.PREFIX_MOD + name, Constants.PREFIX_MOD + name, x, y, icon, parent);
		achievements.add(this);
		registerStat();
	}
	
	public AcievementBase(String name, int x, int y, Block icon, Achievement parent) {
		this(name, x, y, new ItemStack(icon), parent);
	}
	
	public AcievementBase(String name, int x, int y, Item icon, Achievement parent) {
		this(name, x, y, new ItemStack(icon), parent);
	}

	public AcievementBase(String name, int x, int y, ItemStack icon) {
		this(name, x, y, icon, null);
	}
	
	public AcievementBase(String name, int x, int y, Block icon) {
		this(name, x, y, new ItemStack(icon), null);
	}
	
	public AcievementBase(String name, int x, int y, Item icon) {
		this(name, x, y, new ItemStack(icon), null);
	}
	
}
