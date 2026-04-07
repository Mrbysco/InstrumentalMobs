package com.mrbysco.instrumentalmobs.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;

public class InstrumentalMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> AutoConfigClient.getConfigScreen(InstrumentalConfigFabric.class, parent).get();
	}
}
