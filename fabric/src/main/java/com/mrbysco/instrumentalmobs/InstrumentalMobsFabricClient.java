package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.client.render.CymbalHuskRenderer;
import com.mrbysco.instrumentalmobs.client.render.DrumZombieRenderer;
import com.mrbysco.instrumentalmobs.client.render.FrenchHornCreeperRenderer;
import com.mrbysco.instrumentalmobs.client.render.MaracaSpiderRenderer;
import com.mrbysco.instrumentalmobs.client.render.MicrophoneGhastRenderer;
import com.mrbysco.instrumentalmobs.client.render.TrumpetSkeletonRenderer;
import com.mrbysco.instrumentalmobs.client.render.TubaEndermanRenderer;
import com.mrbysco.instrumentalmobs.client.render.XylophoneSkeletonRenderer;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class InstrumentalMobsFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRenderers.register(InstrumentalEntities.XYLOPHONE_SKELETON.get(), XylophoneSkeletonRenderer::new);
		EntityRenderers.register(InstrumentalEntities.TRUMPET_SKELETON.get(), TrumpetSkeletonRenderer::new);
		EntityRenderers.register(InstrumentalEntities.TUBA_ENDERMAN.get(), TubaEndermanRenderer::new);
		EntityRenderers.register(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), FrenchHornCreeperRenderer::new);
		EntityRenderers.register(InstrumentalEntities.DRUM_ZOMBIE.get(), DrumZombieRenderer::new);
		EntityRenderers.register(InstrumentalEntities.CYMBAL_HUSK.get(), CymbalHuskRenderer::new);
		EntityRenderers.register(InstrumentalEntities.MARACA_SPIDER.get(), MaracaSpiderRenderer::new);
		EntityRenderers.register(InstrumentalEntities.MICROPHONE_GHAST.get(), MicrophoneGhastRenderer::new);
		EntityRenderers.register(InstrumentalEntities.SOUND_WAVE.get(), ThrownItemRenderer::new);
		EntityRenderers.register(InstrumentalEntities.MICROPHONE_WAVE.get(), ThrownItemRenderer::new);
	}
}
