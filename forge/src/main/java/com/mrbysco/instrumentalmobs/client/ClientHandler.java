package com.mrbysco.instrumentalmobs.client;

import com.mrbysco.instrumentalmobs.client.render.DrumZombieRenderer;
import com.mrbysco.instrumentalmobs.client.render.FrenchHornCreeperRenderer;
import com.mrbysco.instrumentalmobs.client.render.MaracaSpiderRenderer;
import com.mrbysco.instrumentalmobs.client.render.MicrophoneGhastRenderer;
import com.mrbysco.instrumentalmobs.client.render.TrumpetSkeletonRenderer;
import com.mrbysco.instrumentalmobs.client.render.TubaEndermanRenderer;
import com.mrbysco.instrumentalmobs.client.render.XylophoneSkeletonRenderer;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.minecraft.client.renderer.entity.HuskRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ClientHandler {
	public static void registerEntityRenders(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(InstrumentalEntities.XYLOPHONE_SKELETON.get(), XylophoneSkeletonRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.TRUMPET_SKELETON.get(), TrumpetSkeletonRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.TUBA_ENDERMAN.get(), TubaEndermanRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), FrenchHornCreeperRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.DRUM_ZOMBIE.get(), DrumZombieRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.CYMBAL_HUSK.get(), HuskRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.MARACA_SPIDER.get(), MaracaSpiderRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.MICROPHONE_GHAST.get(), MicrophoneGhastRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.SOUND_WAVE.get(), ThrownItemRenderer::new);
		event.registerEntityRenderer(InstrumentalEntities.MICROPHONE_WAVE.get(), ThrownItemRenderer::new);
	}
}
