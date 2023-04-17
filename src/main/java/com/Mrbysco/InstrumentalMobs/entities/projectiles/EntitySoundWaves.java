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
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages.SpawnEntity;

public class EntitySoundWaves extends AbstractHurtingProjectile implements ItemSupplier {
	private SoundEvent sound = SoundEvents.GHAST_SCREAM;

	public EntitySoundWaves(EntityType<? extends EntitySoundWaves> type, Level worldIn) {
		super(type, worldIn);
	}

	public EntitySoundWaves(Level worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(InstrumentalRegistry.SOUND_WAVE.get(), x, y, z, accelX, accelY, accelZ, worldIn);
	}

	public EntitySoundWaves(Level worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
		super(InstrumentalRegistry.SOUND_WAVE.get(), shooter, accelX, accelY, accelZ, worldIn);
	}

	public EntitySoundWaves(Level worldIn, LivingEntity shooter, SoundEvent theSound) {
		super(InstrumentalRegistry.SOUND_WAVE.get(), shooter, 1, 1, 1, worldIn);
		this.sound = theSound;
	}

	public EntitySoundWaves(SpawnEntity spawnEntity, Level worldIn) {
		this(InstrumentalRegistry.SOUND_WAVE.get(), worldIn);
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
		if (!this.level.isClientSide) {
			this.soundExplosion();

			this.removeAfterChangingDimensions();
		}
	}

	@Override
	protected void onHitEntity(EntityHitResult result) {
		Entity entity = result.getEntity();
		if (entity instanceof Player collidingPlayer && getOwner() instanceof Player playerIn) {
			if (playerIn.canHarmPlayer(collidingPlayer)) {
				if (this.level.random.nextInt(10) <= 2) {
					collidingPlayer.hurt(Reference.causeSoundDamage(this), 1F);
				}
			}
		} else {
			if (getOwner() instanceof LivingEntity) {
				LivingEntity livingEntity = (LivingEntity) this.getOwner();
				entity.hurt(Reference.causeSoundDamage(this), 6.0F);
				this.doEnchantDamageEffects(livingEntity, entity);
			}
		}
	}

	public void soundExplosion() {
		this.level.playSound(null, this.blockPosition(), sound, this.getSoundSource(), 1.0F, this.level.random.nextFloat() * 0.1F + 0.9F);
		this.level.addParticle(ParticleTypes.NOTE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
		if (InstrumentalConfig.COMMON.mobsReact.get() && getOwner() instanceof LivingEntity) {
			InstrumentHelper.instrumentDamage(this.level, (LivingEntity) this.getOwner(), this.getBoundingBox().inflate(InstrumentalConfig.COMMON.instrumentRange.get()));
		}
	}

	/**
	 * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
	 */
	@Override
	public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
		Vec3 vec3 = (new Vec3(x, y, z)).normalize().add(this.random.nextGaussian() * (double) 0.0075F * (double) inaccuracy, this.random.nextGaussian() * (double) 0.0075F * (double) inaccuracy, this.random.nextGaussian() * (double) 0.0075F * (double) inaccuracy).scale((double) velocity);
		this.setDeltaMovement(vec3);
		double d0 = vec3.horizontalDistance();
		this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) (180F / (float) Math.PI)));
		this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) (180F / (float) Math.PI)));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	@Override
	public ItemStack getItem() {
		return new ItemStack(InstrumentalRegistry.microphone.get());
	}
}