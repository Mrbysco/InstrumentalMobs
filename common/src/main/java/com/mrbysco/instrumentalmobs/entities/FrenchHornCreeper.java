package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.platform.Services;
import com.mrbysco.instrumentalmobs.registration.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.registration.InstrumentalSounds;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class FrenchHornCreeper extends Creeper implements IInstrumentalMobs {

	public FrenchHornCreeper(EntityType<? extends FrenchHornCreeper> type, Level level) {
		super(type, level);
	}

	@Override
	public void explodeCreeper() {
		if (!this.level().isClientSide) {
			Level.ExplosionInteraction explosion$mode = Services.PLATFORM.getExplosionInteraction(this);
			float f = this.isPowered() ? 2.0F : 1.0F;
			this.dead = true;
			this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$mode);
			InstrumentHelper.instrumentDamage(this);
			this.playSound(InstrumentalSounds.FRENCH_HORN_SOUND.get(), 1F, 1F);

			this.discard();
			this.spawnLingeringCloud();
		}
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InstrumentalRegistry.FRENCH_HORN.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, getDropChance());
	}

	public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance,
										MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData,
										@Nullable CompoundTag compoundTag) {
		RandomSource randomSource = serverLevelAccessor.getRandom();
		spawnGroupData = super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);

		this.populateDefaultEquipmentSlots(randomSource, difficultyInstance);

		return spawnGroupData;
	}
}
