package com.mrbysco.instrumentalmobs.client;

import com.mrbysco.instrumentalmobs.client.render.DrumZombieRenderer;
import com.mrbysco.instrumentalmobs.client.render.FrenchHornCreeperRenderer;
import com.mrbysco.instrumentalmobs.client.render.MaracaSpiderRenderer;
import com.mrbysco.instrumentalmobs.client.render.MicrophoneGhastRenderer;
import com.mrbysco.instrumentalmobs.client.render.TubaEndermanRenderer;
import com.mrbysco.instrumentalmobs.client.render.XylophoneSkeletonRenderer;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.items.CustomSpawnEggItem;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.HuskRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ClientHandler {
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), XylophoneSkeletonRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.TUBA_ENDERMAN.get(), TubaEndermanRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), FrenchHornCreeperRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.DRUM_ZOMBIE.get(), DrumZombieRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.CYMBAL_HUSK.get(), HuskRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.MARACA_SPIDER.get(), MaracaSpiderRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.MICROPHONE_GHAST.get(), MicrophoneGhastRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.SOUND_WAVE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.MICROPHONE_WAVE.get(), ThrownItemRenderer::new);
	}

	public static void registerItemColors(final ColorHandlerEvent.Item event) {
		ItemColors colors = event.getItemColors();

		for(RegistryObject<Item> itemObject : InstrumentalRegistry.ITEMS.getEntries()) {
			if(itemObject.get() instanceof CustomSpawnEggItem spawnEggItem) {
				colors.register((stack, tintIndex) -> spawnEggItem.getColor(tintIndex), spawnEggItem);
			}
		}
	}
}
