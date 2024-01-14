package com.mrbysco.instrumentalmobs.platform;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {

	public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

	public static <T> T load(Class<T> clazz) {
		final T loadedService = ServiceLoader.load(clazz)
				.findFirst()
				.orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
		Constants.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
		return loadedService;
	}
}
