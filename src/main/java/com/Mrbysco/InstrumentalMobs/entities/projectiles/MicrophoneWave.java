package com.mrbysco.instrumentalmobs.entities.projectiles;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class MicrophoneWave extends ThrowableItemProjectile {
	private SoundEvent sound = SoundEvents.GHAST_SCREAM;
	private LivingEntity shootingEntity;

	public MicrophoneWave(EntityType<? extends MicrophoneWave> type, Level level) {
		super(type, level);
	}

	public MicrophoneWave(Level level, LivingEntity throwerIn, SoundEvent theSound) {
		super(InstrumentalRegistry.MICROPHONE_WAVE.get(), throwerIn, level);
		this.shootingEntity = throwerIn;
		this.sound = theSound;
	}

	public MicrophoneWave(Level level, double x, double y, double z) {
		super(InstrumentalRegistry.MICROPHONE_WAVE.get(), x, y, z, level);
	}

	public MicrophoneWave(SpawnEntity spawnEntity, Level level) {
		this(InstrumentalRegistry.MICROPHONE_WAVE.get(), level);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return (Packet<ClientGamePacketListener>) NetworkHooks.getEntitySpawningPacket(this);
	}

	/**
	 * Called when this EntitySoundWaves hits a block or entity.
	 */

	protected void onHit(HitResult result) {
		super.onHit(result);
		if (!this.level().isClientSide) {
			this.soundExplosion();

			this.level().broadcastEntityEvent(this, (byte) 3);
			this.discard();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		if (entity instanceof Player collidingPlayer && shootingEntity instanceof Player playerIn) {
			if (playerIn.canHarmPlayer(collidingPlayer)) {
				if (this.level().random.nextInt(10) <= 2) {
					collidingPlayer.hurt(Reference.causeSoundDamage(this), 1F);
				}
			}
		} else {
			entity.hurt(Reference.causeSoundDamage(this), 6.0F);
			this.doEnchantDamageEffects(this.shootingEntity, entity);
		}
	}

	public void soundExplosion() {
		this.level().playSound(null, this.blockPosition(), sound, this.getSoundSource(), 1.0F, this.level().random.nextFloat() * 0.1F + 0.9F);
		this.level().addParticle(ParticleTypes.NOTE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
		if (InstrumentalConfig.COMMON.mobsReact.get()) {
			InstrumentHelper.instrumentDamage(this.level(), (LivingEntity) this.getOwner(), this.getBoundingBox().inflate(InstrumentalConfig.COMMON.instrumentRange.get()));
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	@Override
	protected Item getDefaultItem() {
		return InstrumentalRegistry.MICROPHONE.get();
	}
}