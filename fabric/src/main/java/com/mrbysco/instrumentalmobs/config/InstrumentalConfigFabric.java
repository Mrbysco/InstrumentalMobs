package com.mrbysco.instrumentalmobs.config;

import com.mrbysco.instrumentalmobs.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Constants.MOD_ID)
public class InstrumentalConfigFabric implements ConfigData {
	@ConfigEntry.Gui.CollapsibleObject
	public General general = new General();

	public static class General {

		@Comment("Mobs react upon usage of the instruments [default: true]")
		public boolean mobsReact = true;

		@Comment("The area in which the instruments react with mobs. [default: 10.0D]")
		public double instrumentRange = 10.0D;

		@Comment("The chance of instrument sounds damaging mobs after pushing. [default: 0.35D]")
		public double soundDamageChance = 0.35D;

		@Comment("The chance of instrument dropping from a mob holding one [default: 0.5D]")
		public double instrumentDropChance = 0.5D;

		@Comment("The chance of instrument hurting nearby entities upon sounding [default: 0.3D]")
		public double instrumentHurtChance = 0.3D;
	}

	@Override
	public void validatePostLoad() throws ValidationException {
		if (general.instrumentRange < 0.01D) {
			general.instrumentRange = 0.01D;
			throw new ValidationException("Instrument range is too low, setting to minimum: 0.01");
		} else if (general.instrumentRange > 128.0D) {
			general.instrumentRange = 128.0D;
			throw new ValidationException("Instrument range is too high, setting to maximum: 128.0");
		}

		if (general.soundDamageChance < 0.001D) {
			general.soundDamageChance = 0.001D;
			throw new ValidationException("Sound damage chance is too low, setting to minimum: 0.001");
		} else if (general.soundDamageChance > 1.0D) {
			general.soundDamageChance = 1.0D;
			throw new ValidationException("Sound damage chance is too high, setting to maximum: 1.0");
		}

		if (general.instrumentDropChance < 0.0D) {
			general.instrumentDropChance = 0.0D;
			throw new ValidationException("Instrument drop chance is too low, setting to minimum: 0.0");
		} else if (general.instrumentDropChance > 1.0D) {
			general.instrumentDropChance = 1.0D;
			throw new ValidationException("Instrument drop chance is too high, setting to maximum: 1.0");
		}

		if (general.instrumentHurtChance < 0.0D) {
			general.instrumentHurtChance = 0.0D;
			throw new ValidationException("Instrument hurt chance is too low, setting to minimum: 0.0");
		} else if (general.instrumentHurtChance > 1.0D) {
			general.instrumentHurtChance = 1.0D;
			throw new ValidationException("Instrument hurt chance is too high, setting to maximum: 1.0");
		}
	}
}