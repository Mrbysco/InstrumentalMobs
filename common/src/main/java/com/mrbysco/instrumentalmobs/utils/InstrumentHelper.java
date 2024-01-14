package com.mrbysco.instrumentalmobs.utils;

import com.mrbysco.instrumentalmobs.Constants;
import com.mrbysco.instrumentalmobs.entities.IInstrumentalMobs;
import com.mrbysco.instrumentalmobs.platform.Services;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class InstrumentHelper {
	public static void instrumentDamage(LivingEntity livingEntity) {
		instrumentDamage(livingEntity.level(), livingEntity, livingEntity.getBoundingBox().inflate(Services.PLATFORM.instrumentRange()));
	}

	public static void instrumentDamage(Level level, LivingEntity livingEntity, AABB box) {
		if (!level.isClientSide && livingEntity != null) {
			List<LivingEntity> livingEntities = level.getEntities(livingEntity, box).stream()
					.filter(entity -> entity instanceof LivingEntity).map(entity -> (LivingEntity) entity).toList();
			for (LivingEntity collidingEntity : livingEntities) {
				double xDist = collidingEntity.getX() - livingEntity.getX() + level.random.nextDouble() - level.random.nextDouble();
				double zDist = collidingEntity.getZ() - livingEntity.getZ() + level.random.nextDouble() - level.random.nextDouble();
				double distance = Math.sqrt(xDist * xDist + zDist * zDist);

				collidingEntity.hurtMarked = true;
				collidingEntity.push(0.5 * xDist / distance, 5.0D / (10.0D + distance), 0.5 * zDist / distance);

				if (level.random.nextDouble() <= Services.PLATFORM.soundDamageChance()) {
					if (livingEntity instanceof Player) {
						final double chance = Services.PLATFORM.instrumentHurtChance();
						if (collidingEntity instanceof Player collidingPlayer) {
							Player playerIn = (Player) livingEntity;
							if (playerIn.canHarmPlayer(collidingPlayer)) {
								if (level.random.nextDouble() <= chance) {
									collidingEntity.hurt(Constants.causeSoundDamage(livingEntity), 1.0F);
								}
							}
						} else {
							if (!(collidingEntity.getType().getCategory() == MobCategory.MONSTER)) {
								if (level.random.nextDouble() <= chance) {
									collidingEntity.hurt(Constants.causeSoundDamage(livingEntity), 1.0F);
								}
							}
						}
					} else if ((collidingEntity.getType().getCategory() == MobCategory.MONSTER && !(collidingEntity instanceof IInstrumentalMobs)) || collidingEntity instanceof Player) {
						collidingEntity.hurt(Constants.causeSoundDamage(livingEntity), 1.0F);
					}
				}
			}
		}
	}
}
