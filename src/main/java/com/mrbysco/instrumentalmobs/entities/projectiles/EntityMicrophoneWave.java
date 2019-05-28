package com.mrbysco.instrumentalmobs.entities.projectiles;

import com.mrbysco.instrumentalmobs.InstrumentalMobs;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfigGen;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMicrophoneWave extends EntityThrowable
{
    private SoundEvent sound = SoundEvents.ENTITY_GHAST_SCREAM;
    private EntityLivingBase shootingEntity;

    public EntityMicrophoneWave(World worldIn)
    {
        super(worldIn);
    }

    public EntityMicrophoneWave(World worldIn, EntityLivingBase throwerIn, SoundEvent theSound)
    {
        super(worldIn, throwerIn);
        this.shootingEntity = throwerIn;
        this.sound = theSound;
    }

    @SideOnly(Side.CLIENT)
    public EntityMicrophoneWave(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    /**
     * Called when this EntitySoundWaves hits a block or entity.
     */
    
    protected void onImpact(RayTraceResult result)
    {
        if (!this.world.isRemote)
        {
            if (result.entityHit != null)
            {
            	result.entityHit.attackEntityFrom(InstrumentalMobs.soundDamage, 6.0F);
                this.applyEnchantments(this.shootingEntity, result.entityHit);
            }
            
            this.soundExplosion();

            this.setDead();
        }
    }
    
    public void soundExplosion()
    {
    	this.world.playSound(null, this.getPosition(), sound, this.getSoundCategory(), 1.0F, this.world.rand.nextFloat() * 0.1F + 0.9F);
    	this.world.spawnParticle(EnumParticleTypes.NOTE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    	if(InstrumentalConfigGen.general.mobsReact)
    	{
    		InstrumentHelper.instrumentDamage(this.world, this.shootingEntity);
    	}
    }
}