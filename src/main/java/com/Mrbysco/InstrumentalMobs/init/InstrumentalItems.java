package com.mrbysco.instrumentalmobs.init;

import java.util.ArrayList;

import com.mrbysco.instrumentalmobs.items.DrumInstrument;
import com.mrbysco.instrumentalmobs.items.InstrumentItem;
import com.mrbysco.instrumentalmobs.items.InstrumentMicrophone;
import com.mrbysco.instrumentalmobs.items.InstrumentSilentItem;

import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber
public class InstrumentalItems {
	public static InstrumentSilentItem cymbal, maraca;

	public static InstrumentItem xylophone, tuba, french_horn, cymbals, maracas;

	public static InstrumentMicrophone microphone;
	
	public static DrumInstrument drum;
	
	public static ArrayList<Item> ITEMS = new ArrayList<>();
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
		IForgeRegistry<Item> registry = event.getRegistry();
		
		xylophone = registerItem(new InstrumentItem("xylophone", InstrumentalSounds.xylophone_sound, 30, 160, 30));
		tuba = registerItem(new InstrumentItem("tuba", InstrumentalSounds.tuba_sound, 20, 180, 40));
		french_horn = registerItem(new InstrumentItem("french_horn", InstrumentalSounds.french_horn_sound, 40, 165, 50));
		drum = registerItem(new DrumInstrument("drum", InstrumentalSounds.drum_sound, 30, 140, 40));
		cymbal = registerItem(new InstrumentSilentItem("cymbal"));
		cymbals = registerItem(new InstrumentItem("cymbals", InstrumentalSounds.cymbals_sound, 30, 100, 40));
		maraca = registerItem(new InstrumentSilentItem("maraca"));
		maracas = registerItem(new InstrumentItem("maracas", InstrumentalSounds.maraca_sound, 30, 125, 20));
		microphone = registerItem(new InstrumentMicrophone("microphone", SoundEvents.ENTITY_GHAST_SCREAM, 40, 110, 40));
		
		registry.registerAll(ITEMS.toArray(new Item[0]));
    }
	
	public static <T extends Item> T registerItem(T item)
    {
        ITEMS.add(item);
        return item;
    }
}
