package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrenchHornCreeperEntity extends Creeper implements IInstrumentalMobs {

	public FrenchHornCreeperEntity(EntityType<? extends FrenchHornCreeperEntity> type, Level worldIn) {
		super(type, worldIn);
		this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(InstrumentalRegistry.FRENCH_HORN.get()));
		this.setDropChance(EquipmentSlot.MAINHAND, getDropChance());
	}

	@Override
	public void explodeCreeper() {
		if (!this.level().isClientSide) {
			Level.ExplosionInteraction explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this) ? Level.ExplosionInteraction.MOB : Level.ExplosionInteraction.NONE;
			float f = this.isPowered() ? 2.0F : 1.0F;
			this.dead = true;
			this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * f, explosion$mode);
			InstrumentHelper.instrumentDamage(this.level(), this);
			this.playSound(InstrumentalRegistry.FRENCH_HORN_SOUND.get(), 1F, 1F);

			this.discard();
			this.spawnLingeringCloud();
		}
	}
}
