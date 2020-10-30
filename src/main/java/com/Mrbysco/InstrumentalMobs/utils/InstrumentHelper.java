package com.mrbysco.instrumentalmobs.utils;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.entities.IInstrumentalMobs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class InstrumentHelper {
	public static void instrumentDamage(World worldIn, LivingEntity entityIn) {
		instrumentDamage(worldIn, entityIn, entityIn.getBoundingBox().grow(InstrumentalConfig.COMMON.instrumentRange.get()));
	}

	public static void instrumentDamage(World worldIn, LivingEntity entityIn, AxisAlignedBB box) {
		if(!worldIn.isRemote && entityIn != null) {
			for(Entity entity : worldIn.getEntitiesWithinAABBExcludingEntity(entityIn, box)) {
				if(entity.equals(entityIn))
					break;

				if(entity instanceof LivingEntity) {
					LivingEntity collidingEntity = (LivingEntity)entity;
					double xDist = collidingEntity.getPosX() - entityIn.getPosX() + worldIn.rand.nextDouble() - worldIn.rand.nextDouble();
					double zDist = collidingEntity.getPosZ() - entityIn.getPosZ() + worldIn.rand.nextDouble() - worldIn.rand.nextDouble();
					double distance = Math.sqrt(xDist * xDist + zDist * zDist);

					collidingEntity.velocityChanged = true;
					collidingEntity.addVelocity(0.5 * xDist / distance, 5.0D / (10.0D + distance), 0.5 * zDist / distance);

					if(worldIn.rand.nextDouble() <= InstrumentalConfig.COMMON.soundDamageChance.get()) {
						if(entityIn instanceof PlayerEntity) {
							if(collidingEntity instanceof PlayerEntity) {
								PlayerEntity playerIn = (PlayerEntity)entityIn;
								PlayerEntity collidingPlayer = (PlayerEntity)collidingEntity;
								if(playerIn.canAttackPlayer(collidingPlayer)) {
									if(worldIn.rand.nextInt(10) <= 3) {
										collidingEntity.attackEntityFrom(Reference.causeSoundDamage(entityIn), 1F);
									}
								}
							} else {
								if(!(collidingEntity.getType().getClassification() == EntityClassification.MONSTER)) {
									if(worldIn.rand.nextInt(10) <= 3) {
										collidingEntity.attackEntityFrom(Reference.causeSoundDamage(entityIn), 1F);
									}
								}
							}
						} else if((collidingEntity.getType().getClassification() == EntityClassification.MONSTER && !(collidingEntity instanceof IInstrumentalMobs)) || collidingEntity instanceof PlayerEntity) {
							collidingEntity.attackEntityAsMob(entityIn);
							collidingEntity.attackEntityFrom(Reference.causeSoundDamage(entityIn), 1F);
							collidingEntity.setRevengeTarget(entityIn);
						}
					}
				}
			}
		}
	}
}
