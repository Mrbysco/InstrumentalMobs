package com.Mrbysco.InstrumentalMobs.items;

import com.Mrbysco.InstrumentalMobs.InstrumentalMobs;
import com.Mrbysco.InstrumentalMobs.Reference;

import net.minecraft.item.Item;

public class InstrumentSilentItem extends Item{

	public InstrumentSilentItem(String registryName) {
		setCreativeTab(InstrumentalMobs.instrumentalTab);
		this.setUnlocalizedName(Reference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
		maxStackSize = 1;
	}
}
