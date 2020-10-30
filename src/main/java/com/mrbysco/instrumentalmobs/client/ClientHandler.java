package com.mrbysco.instrumentalmobs.client;

import com.mrbysco.instrumentalmobs.client.render.RenderCymbalZombie;
import com.mrbysco.instrumentalmobs.client.render.RenderDrumZombie;
import com.mrbysco.instrumentalmobs.client.render.RenderFrenchHornCreeper;
import com.mrbysco.instrumentalmobs.client.render.RenderMaracaSpider;
import com.mrbysco.instrumentalmobs.client.render.RenderMicrophoneGhast;
import com.mrbysco.instrumentalmobs.client.render.RenderTubaEnderman;
import com.mrbysco.instrumentalmobs.client.render.RenderXylophoneSkeleton;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientHandler {
	public static void doClientStuff(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.XYLOPHONE_SKELETON.get(), RenderXylophoneSkeleton::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.TUBA_ENDERMAN.get(), RenderTubaEnderman::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.FRENCH_HORN_CREEPER.get(), RenderFrenchHornCreeper::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.DRUM_ZOMBIE.get(), RenderDrumZombie::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.CYMBAL_HUSK.get(), RenderCymbalZombie::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.MARACA_SPIDER.get(), RenderMaracaSpider::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.MICROPHONE_GHAST.get(), RenderMicrophoneGhast::new);
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.SOUND_WAVE.get(), renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer(), 0.75F, true));
		RenderingRegistry.registerEntityRenderingHandler(InstrumentalRegistry.MICROPHONE_WAVE.get(), renderManager -> new SpriteRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));
	}
}
