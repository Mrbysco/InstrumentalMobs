package com.mrbysco.instrumentalmobs;

import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;

public class CommonClass {

	public static void init() {
		InstrumentalSounds.loadClass();
		InstrumentalEntities.loadClass();
		InstrumentalRegistry.loadClass();
	}
}