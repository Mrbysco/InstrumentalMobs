package com.mrbysco.instrumentalmobs.client;

import com.mrbysco.instrumentalmobs.client.render.DrumZombieRenderer;
import com.mrbysco.instrumentalmobs.client.render.FrenchHornCreeperRenderer;
import com.mrbysco.instrumentalmobs.client.render.MaracaSpiderRenderer;
import com.mrbysco.instrumentalmobs.client.render.MicrophoneGhastRenderer;
import com.mrbysco.instrumentalmobs.client.render.TrumpetSkeletonRenderer;
import com.mrbysco.instrumentalmobs.client.render.TubaEndermanRenderer;
import com.mrbysco.instrumentalmobs.client.render.XylophoneSkeletonRenderer;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.client.renderer.entity.HuskRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), XylophoneSkeletonRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.TRUMPET_SKELETON.get(), TrumpetSkeletonRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.TUBA_ENDERMAN.get(), TubaEndermanRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), FrenchHornCreeperRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.DRUM_ZOMBIE.get(), DrumZombieRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.CYMBAL_HUSK.get(), HuskRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.MARACA_SPIDER.get(), MaracaSpiderRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.MICROPHONE_GHAST.get(), MicrophoneGhastRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.SOUND_WAVE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(InstrumentalRegistry.MICROPHONE_WAVE.get(), ThrownItemRenderer::new);
	}
}
