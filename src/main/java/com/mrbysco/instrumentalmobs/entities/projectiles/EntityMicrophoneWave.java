package com.mrbysco.instrumentalmobs.entities.projectiles;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

@OnlyIn(
        value = Dist.CLIENT,
        _interface = IRendersAsItem.class
)
public class EntityMicrophoneWave extends ThrowableEntity implements IRendersAsItem {
    private SoundEvent sound = SoundEvents.GHAST_SCREAM;
    private LivingEntity shootingEntity;

    public EntityMicrophoneWave(EntityType<? extends EntityMicrophoneWave> type, World worldIn) {
        super(type, worldIn);
    }

    public EntityMicrophoneWave(World worldIn, LivingEntity throwerIn, SoundEvent theSound) {
        super(InstrumentalRegistry.MICROPHONE_WAVE.get(), throwerIn, worldIn);
        this.shootingEntity = throwerIn;
        this.sound = theSound;
    }

    public EntityMicrophoneWave(World worldIn, double x, double y, double z) {
        super(InstrumentalRegistry.MICROPHONE_WAVE.get(), x, y, z, worldIn);
    }

    public EntityMicrophoneWave(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(InstrumentalRegistry.MICROPHONE_WAVE.get(), worldIn);
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * Called when this EntitySoundWaves hits a block or entity.
     */
    
    protected void onHit(RayTraceResult result) {
        super.onHit(result);
        if (!this.level.isClientSide) {
            this.soundExplosion();

            this.level.broadcastEntityEvent(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected void onHitEntity(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if(entity instanceof PlayerEntity && shootingEntity instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity)shootingEntity;
            PlayerEntity collidingPlayer = (PlayerEntity)entity;
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
    public ItemStack getItem() {
        return new ItemStack(InstrumentalRegistry.microphone.get());
    }
}