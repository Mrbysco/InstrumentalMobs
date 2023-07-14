package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;

public interface IInstrumentalMobs {
	default float getDropChance() {
		return InstrumentalConfig.COMMON.instrumentDropChance.get().floatValue();
	}
}
