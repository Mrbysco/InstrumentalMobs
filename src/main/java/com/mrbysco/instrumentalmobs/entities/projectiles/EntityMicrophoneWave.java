package com.mrbysco.instrumentalmobs.entities.projectiles;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
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
import net.minecraftforge.fmllegacy.network.FMLPlayMessages;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

public class EntityMicrophoneWave extends ThrowableItemProjectile {
    private SoundEvent sound = SoundEvents.GHAST_SCREAM;
    private LivingEntity shootingEntity;

    public EntityMicrophoneWave(EntityType<? extends EntityMicrophoneWave> type, Level worldIn) {
        super(type, worldIn);
    }

    public EntityMicrophoneWave(Level worldIn, LivingEntity throwerIn, SoundEvent theSound) {
        super(InstrumentalRegistry.MICROPHONE_WAVE.get(), throwerIn, worldIn);
        this.shootingEntity = throwerIn;
        this.sound = theSound;
    }

    public EntityMicrophoneWave(Level worldIn, double x, double y, double z) {
        super(InstrumentalRegistry.MICROPHONE_WAVE.get(), x, y, z, worldIn);
    }

    public EntityMicrophoneWave(FMLPlayMessages.SpawnEntity spawnEntity, Level worldIn) {
        this(InstrumentalRegistry.MICROPHONE_WAVE.get(), worldIn);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * Called when this EntitySoundWaves hits a block or entity.
     */
    
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.soundExplosion();

            this.level.broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        if(entity instanceof Player && shootingEntity instanceof Player) {
            Player playerIn = (Player)shootingEntity;
            Player collidingPlayer = (Player)entity;
            if(playerIn.canHarmPlayer(collidingPlayer)) {
                if(this.level.random.nextInt(10) <= 2) {
                    collidingPlayer.hurt(Reference.causeSoundDamage(this), 1F);
                }
            }
        } else {
            entity.hurt(Reference.causeSoundDamage(this), 6.0F);
            this.doEnchantDamageEffects(this.shootingEntity, entity);
        }
    }

    public void soundExplosion() {
    	this.level.playSound(null, this.blockPosition(), sound, this.getSoundSource(), 1.0F, this.level.random.nextFloat() * 0.1F + 0.9F);
    	this.level.addParticle(ParticleTypes.NOTE, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
    	if(InstrumentalConfig.COMMON.mobsReact.get()) {
            InstrumentHelper.instrumentDamage(this.level, (LivingEntity)this.getOwner(), this.getBoundingBox().inflate(InstrumentalConfig.COMMON.instrumentRange.get()));
    	}
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected Item getDefaultItem() {
        return InstrumentalRegistry.microphone.get();
    }
}