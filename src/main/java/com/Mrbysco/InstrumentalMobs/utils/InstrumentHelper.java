package com.mrbysco.instrumentalmobs.utils;

import com.mrbysco.instrumentalmobs.InstrumentalMobs;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfigGen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class InstrumentHelper {
	 public static void instrumentDamage(World worldIn, EntityLivingBase entityIn) {
		 if(!worldIn.isRemote && entityIn != null) {
			 for(EntityLivingBase collidingEntity : worldIn.getEntitiesWithinAABB(EntityLivingBase.class,
					 entityIn.getEntityBoundingBox().grow(InstrumentalConfigGen.general.instrumentRange))) {
				 if(collidingEntity.equals(entityIn))
					 continue;
				 
				 double xDist = collidingEntity.posX - entityIn.posX + worldIn.rand.nextDouble() - worldIn.rand.nextDouble();
				 double zDist = collidingEntity.posZ - entityIn.posZ + worldIn.rand.nextDouble() - worldIn.rand.nextDouble();
				 double distance = Math.sqrt(xDist * xDist + zDist * zDist);

				 collidingEntity.velocityChanged = true;
				 collidingEntity.addVelocity(0.5 * xDist / distance, 5.0D / (10.0D + distance), 0.5 * zDist / distance);
				 collidingEntity.setRevengeTarget(entityIn);

				 if(entityIn instanceof EntityPlayer) {
					 if(collidingEntity instanceof EntityPlayer) {
						 EntityPlayer playerIn = (EntityPlayer)entityIn;
						 EntityPlayer collidingPlayer = (EntityPlayer)collidingEntity;
					 	if(playerIn.canAttackPlayer(collidingPlayer)) {
							if(worldIn.rand.nextInt(10) <= 2) {
								collidingEntity.attackEntityFrom(InstrumentalMobs.soundDamage, 1F);
							}
						}
					 } else {
						 if(!collidingEntity.isCreatureType(EnumCreatureType.MONSTER, false)) {
							 if(worldIn.rand.nextInt(10) <= 2) {
								 collidingEntity.attackEntityFrom(InstrumentalMobs.soundDamage, 1F);
							 }
						 }
					 }
				 } else if(entityIn.isCreatureType(EnumCreatureType.MONSTER, false)) {
					 collidingEntity.attackEntityFrom(InstrumentalMobs.soundDamage, 1F);
					 collidingEntity.attackEntityAsMob(entityIn);
				 }
			 }
		 }
	 }
}
