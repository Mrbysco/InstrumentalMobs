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
		instrumentDamage(worldIn, entityIn, entityIn.getBoundingBox().inflate(InstrumentalConfig.COMMON.instrumentRange.get()));
	}

	public static void instrumentDamage(World worldIn, LivingEntity entityIn, AxisAlignedBB box) {
		if(!worldIn.isClientSide && entityIn != null) {
			for(Entity entity : worldIn.getEntities(entityIn, box)) {
				if(entity.equals(entityIn))
					break;

				if(entity instanceof LivingEntity) {
					LivingEntity collidingEntity = (LivingEntity)entity;
					double xDist = collidingEntity.getX() - entityIn.getX() + worldIn.random.nextDouble() - worldIn.random.nextDouble();
					double zDist = collidingEntity.getZ() - entityIn.getZ() + worldIn.random.nextDouble() - worldIn.random.nextDouble();
					double distance = Math.sqrt(xDist * xDist + zDist * zDist);

					collidingEntity.hurtMarked = true;
					collidingEntity.push(0.5 * xDist / distance, 5.0D / (10.0D + distance), 0.5 * zDist / distance);

					if(worldIn.random.nextDouble() <= InstrumentalConfig.COMMON.soundDamageChance.get()) {
						if(entityIn instanceof PlayerEntity) {
							if(collidingEntity instanceof PlayerEntity) {
								PlayerEntity playerIn = (PlayerEntity)entityIn;
								PlayerEntity collidingPlayer = (PlayerEntity)collidingEntity;
								if(playerIn.canHarmPlayer(collidingPlayer)) {
									if(worldIn.random.nextInt(10) <= 3) {
										collidingEntity.hurt(Reference.causeSoundDamage(entityIn), 1.0F);
									}
								}
							} else {
								if(!(collidingEntity.getType().getCategory() == EntityClassification.MONSTER)) {
									if(worldIn.random.nextInt(10) <= 3) {
										collidingEntity.hurt(Reference.causeSoundDamage(entityIn), 1.0F);
									}
								}
							}
						} else if((collidingEntity.getType().getCategory() == EntityClassification.MONSTER && !(collidingEntity instanceof IInstrumentalMobs)) || collidingEntity instanceof PlayerEntity) {
							collidingEntity.hurt(Reference.causeSoundDamage(entityIn), 1.0F);
						}
					}
				}
			}
		}
	}
}
