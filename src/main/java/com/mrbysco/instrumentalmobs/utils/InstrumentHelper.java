package com.mrbysco.instrumentalmobs.utils;

import com.mrbysco.instrumentalmobs.Reference;
import com.mrbysco.instrumentalmobs.config.InstrumentalConfig;
import com.mrbysco.instrumentalmobs.entities.IInstrumentalMobs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

public class InstrumentHelper {
	public static void instrumentDamage(Level worldIn, LivingEntity entityIn) {
		instrumentDamage(worldIn, entityIn, entityIn.getBoundingBox().inflate(InstrumentalConfig.COMMON.instrumentRange.get()));
	}

	public static void instrumentDamage(Level worldIn, LivingEntity entityIn, AABB box) {
		if (!worldIn.isClientSide && entityIn != null) {
			for (Entity entity : worldIn.getEntities(entityIn, box)) {
				if (entity.equals(entityIn))
					break;

				if (entity instanceof LivingEntity collidingEntity) {
					double xDist = collidingEntity.getX() - entityIn.getX() + worldIn.random.nextDouble() - worldIn.random.nextDouble();
					double zDist = collidingEntity.getZ() - entityIn.getZ() + worldIn.random.nextDouble() - worldIn.random.nextDouble();
					double distance = Math.sqrt(xDist * xDist + zDist * zDist);

					collidingEntity.hurtMarked = true;
					collidingEntity.push(0.5 * xDist / distance, 5.0D / (10.0D + distance), 0.5 * zDist / distance);

					if (worldIn.random.nextDouble() <= InstrumentalConfig.COMMON.soundDamageChance.get()) {
						if (entityIn instanceof Player) {
							if (collidingEntity instanceof Player collidingPlayer) {
								Player playerIn = (Player) entityIn;
								if (playerIn.canHarmPlayer(collidingPlayer)) {
									if (worldIn.random.nextInt(10) <= 3) {
										collidingEntity.hurt(Reference.causeSoundDamage(entityIn), 1.0F);
									}
								}
							} else {
								if (!(collidingEntity.getType().getCategory() == MobCategory.MONSTER)) {
									if (worldIn.random.nextInt(10) <= 3) {
										collidingEntity.hurt(Reference.causeSoundDamage(entityIn), 1.0F);
									}
								}
							}
						} else if ((collidingEntity.getType().getCategory() == MobCategory.MONSTER && !(collidingEntity instanceof IInstrumentalMobs)) || collidingEntity instanceof Player) {
							collidingEntity.hurt(Reference.causeSoundDamage(entityIn), 1.0F);
						}
					}
				}
			}
		}
	}
}
