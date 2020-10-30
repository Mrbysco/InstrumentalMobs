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
import net.minecraft.entity.projectile.DamagingProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;
@OnlyIn(
        value = Dist.CLIENT,
        _interface = IRendersAsItem.class
)
public class EntitySoundWaves extends DamagingProjectileEntity implements IRendersAsItem {
    private SoundEvent sound = SoundEvents.ENTITY_GHAST_SCREAM;

    public EntitySoundWaves(EntityType<? extends EntitySoundWaves> type, World worldIn) {
        super(type, worldIn);
    }

    public EntitySoundWaves(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
        super(InstrumentalRegistry.SOUND_WAVE.get(), x, y, z, accelX, accelY, accelZ, worldIn);
    }

    public EntitySoundWaves(World worldIn, LivingEntity shooter, double accelX, double accelY, double accelZ) {
        super(InstrumentalRegistry.SOUND_WAVE.get(), shooter, accelX, accelY, accelZ, worldIn);
    }
    
    public EntitySoundWaves(World worldIn, LivingEntity shooter, SoundEvent theSound) {
    	super(InstrumentalRegistry.SOUND_WAVE.get(), shooter, 1, 1, 1, worldIn);
		this.sound = theSound;
	}

    public EntitySoundWaves(FMLPlayMessages.SpawnEntity spawnEntity, World worldIn) {
        this(InstrumentalRegistry.SOUND_WAVE.get(), worldIn);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /**
     * Called when this EntitySoundWaves hits a block or entity.
     */
    
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            this.soundExplosion();

            this.setDead();
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if(entity instanceof PlayerEntity && func_234616_v_() instanceof PlayerEntity) {
            PlayerEntity playerIn = (PlayerEntity)func_234616_v_();
            PlayerEntity collidingPlayer = (PlayerEntity)entity;
            if(playerIn.canAttackPlayer(collidingPlayer)) {
                if(this.world.rand.nextInt(10) <= 2) {
                    collidingPlayer.attackEntityFrom(Reference.causeSoundDamage(this), 1F);
                }
            }
        } else {
            if(func_234616_v_() instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) this.func_234616_v_();
                entity.attackEntityFrom(Reference.causeSoundDamage(this), 6.0F);
                this.applyEnchantments(livingEntity, entity);
            }
        }
    }

    public void soundExplosion() {
        this.world.playSound(null, this.getPosition(), sound, this.getSoundCategory(), 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        this.world.addParticle(ParticleTypes.NOTE, this.getPosX(), this.getPosY(), this.getPosZ(), 0.0D, 0.0D, 0.0D);
        if(InstrumentalConfig.COMMON.mobsReact.get() && func_234616_v_() instanceof LivingEntity) {
            InstrumentHelper.instrumentDamage(this.world, (LivingEntity)this.func_234616_v_(), this.getBoundingBox().grow(InstrumentalConfig.COMMON.instrumentRange.get()));
        }
    }

	public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
        float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
        float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
        this.shoot(f, f1, f2, velocity, inaccuracy);

        Vector3d motion = getMotion();
        Vector3d throwerMotion = func_234616_v_().getMotion();
        double motionX = motion.x + throwerMotion.x;
        double motionY = motion.y;
        double motionZ = motion.z + throwerMotion.z;

        if (!entityThrower.isOnGround()) {
            motionY += throwerMotion.y;
        }

        this.setMotion(motionX, motionY, motionZ);
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z direction.
     */
	@Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.setMotion(x, y, z);
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
    }

    @Override
    protected void registerData() {
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(InstrumentalRegistry.microphone.get());
    }
}