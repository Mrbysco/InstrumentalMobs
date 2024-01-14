package com.mrbysco.instrumentalmobs.entities.projectiles;

import com.mrbysco.instrumentalmobs.registration.InstrumentalEntities;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class SoundWavesForge extends SoundWaves {
	public SoundWavesForge(EntityType<? extends SoundWaves> type, Level level) {
		super(type, level);
	}

	public SoundWavesForge(SpawnEntity spawnEntity, Level level) {
		this(InstrumentalEntities.SOUND_WAVE.get(), level);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}