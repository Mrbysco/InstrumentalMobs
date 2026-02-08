package com.mrbysco.instrumentalmobs.config;

import com.mrbysco.instrumentalmobs.Reference;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Reference.MOD_ID)
@Config.LangKey("instrumentalmobs.config.title")
public class InstrumentalConfigGen {
	@Config.Comment({"General settings"})
	public static General general = new General();

	public static class General{
		@Config.Comment("Mobs react upon usage of the instruments [default: true]")
		public boolean mobsReact = true;
		
		@Config.Comment("The area in which the instruments react with mobs. [default: 10.0D]")
		public double instrumentRange = 10.0D;
	}
	
	@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
	private static class EventHandler {

		@SubscribeEvent
		public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
			if (event.getModID().equals(Reference.MOD_ID)) {
				ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}