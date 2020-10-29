package com.mrbysco.instrumentalmobs.items;

import com.mrbysco.instrumentalmobs.InstrumentalMobs;
import com.mrbysco.instrumentalmobs.Reference;
import net.minecraft.item.Item;

public class InstrumentSilentItem extends Item {

	public InstrumentSilentItem(String registryName) {
		this.setCreativeTab(InstrumentalMobs.instrumentalTab);
		this.setTranslationKey(Reference.MOD_PREFIX + registryName.replaceAll("_", ""));
		this.setRegistryName(registryName);
		this.maxStackSize = 1;
	}
}
