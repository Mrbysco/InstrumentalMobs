package com.mrbysco.instrumentalmobs.entities.projectiles;

import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class MicrophoneWaveForge extends MicrophoneWave {

	public MicrophoneWaveForge(EntityType<? extends MicrophoneWave> type, Level level) {
		super(type, level);
	}

	public MicrophoneWaveForge(SpawnEntity spawnEntity, Level level) {
		this(InstrumentalEntities.MICROPHONE_WAVE.get(), level);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}