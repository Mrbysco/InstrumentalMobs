package com.mrbysco.instrumentalmobs.entities;

import com.mrbysco.instrumentalmobs.init.InstrumentalRegistry;
import com.mrbysco.instrumentalmobs.utils.InstrumentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class FrenchHornCreeperEntity extends CreeperEntity implements IInstrumentalMobs {

    public FrenchHornCreeperEntity(EntityType<? extends FrenchHornCreeperEntity> type, World worldIn) {
		super(type, worldIn);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(InstrumentalRegistry.french_horn.get()));
	}

    @Override
	public void explode() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isCharged() ? 2.0F : 1.0F;
            this.dead = true;
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionRadius * f, explosion$mode);
            InstrumentHelper.instrumentDamage(this.world, this);
            this.playSound(InstrumentalRegistry.french_horn_sound.get(), 1F, 1F);

            this.remove();
            this.spawnLingeringCloud();
        }
    }
}
