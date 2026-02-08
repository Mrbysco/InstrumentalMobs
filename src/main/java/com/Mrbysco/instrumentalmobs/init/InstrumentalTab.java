package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;

public class InstrumentalTab extends CreativeTabs {

	public InstrumentalTab() {
		super(Reference.MOD_ID);
	}

	@Override
	public ItemStack createIcon() {
		return new ItemStack(Blocks.NOTEBLOCK);
	}

	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> list) {
		super.displayAllRelevantItems(list);
		
		list.add(getEgg(Reference.MOD_PREFIX + "french_horn_creeper"));
		list.add(getEgg(Reference.MOD_PREFIX + "tuba_enderman"));
		list.add(getEgg(Reference.MOD_PREFIX + "drum_zombie"));
		list.add(getEgg(Reference.MOD_PREFIX + "cymbal_husk"));
		list.add(getEgg(Reference.MOD_PREFIX + "xylophone_skeleton"));
		list.add(getEgg(Reference.MOD_PREFIX + "maraca_spider"));
		list.add(getEgg(Reference.MOD_PREFIX + "microphone_ghast"));
	}
	
	public static ItemStack getEgg(String entityName) {
        ItemStack stack = new ItemStack(Items.SPAWN_EGG);
        NBTTagCompound entityTag = new NBTTagCompound();
        entityTag.setString("id", entityName);
        NBTTagCompound eggTag = new NBTTagCompound();
        eggTag.setTag("EntityTag", entityTag);
        stack.setTagCompound(eggTag);
        return stack;
    }
}
