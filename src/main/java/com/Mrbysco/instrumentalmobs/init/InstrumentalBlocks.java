package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.blocks.DrumBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;

@EventBusSubscriber
public class InstrumentalBlocks {
	public static Block drum;
	public static ArrayList<Block> BLOCKS = new ArrayList<>();
	 
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> registry = event.getRegistry();
	
		drum = registerBlock(new DrumBlock("drum_block"));
	
		registry.registerAll(BLOCKS.toArray(new Block[0]));
	}

	public static <T extends Block> T registerBlock(T block)
	{
	    return registerBlock(block, new ItemBlock(block));
	}

	public static <T extends Block> T registerBlock(T block, ItemBlock item) {
		item.setRegistryName(block.getRegistryName());
		InstrumentalItems.ITEMS.add(item);
	    BLOCKS.add(block);
	    return block;
	}
}
