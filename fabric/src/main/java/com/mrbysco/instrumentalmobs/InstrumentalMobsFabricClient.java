package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.client.render.DrumZombieRenderer;
import com.mrbysco.instrumentalmobs.client.render.FrenchHornCreeperRenderer;
import com.mrbysco.instrumentalmobs.client.render.MaracaSpiderRenderer;
import com.mrbysco.instrumentalmobs.client.render.MicrophoneGhastRenderer;
import com.mrbysco.instrumentalmobs.client.render.TrumpetSkeletonRenderer;
import com.mrbysco.instrumentalmobs.client.render.TubaEndermanRenderer;
import com.mrbysco.instrumentalmobs.client.render.XylophoneSkeletonRenderer;
import com.mrbysco.instrumentalmobs.init.SupplierSpawnEggItem;
import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.HuskRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class InstrumentalMobsFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(InstrumentalEntities.XYLOPHONE_SKELETON.get(), XylophoneSkeletonRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.TRUMPET_SKELETON.get(), TrumpetSkeletonRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.TUBA_ENDERMAN.get(), TubaEndermanRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.FRENCH_HORN_CREEPER.get(), FrenchHornCreeperRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.DRUM_ZOMBIE.get(), DrumZombieRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.CYMBAL_HUSK.get(), HuskRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.MARACA_SPIDER.get(), MaracaSpiderRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.MICROPHONE_GHAST.get(), MicrophoneGhastRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.SOUND_WAVE.get(), ThrownItemRenderer::new);
		EntityRendererRegistry.register(InstrumentalEntities.MICROPHONE_WAVE.get(), ThrownItemRenderer::new);

		for (SupplierSpawnEggItem<?> registryObject : SupplierSpawnEggItem.getModEggs()) {
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
				return tintIndex == 0 ? registryObject.getColor(0) : -1;
			}, registryObject);
		}
	}
}
