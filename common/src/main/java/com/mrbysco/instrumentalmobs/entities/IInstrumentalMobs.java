package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.platform.Services;

public interface IInstrumentalMobs {
	default float getDropChance() {
		return (float) Services.PLATFORM.instrumentDropChance();
	}
}
