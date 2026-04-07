package com.mrbysco.instrumentalmobs.client.render;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.state.ArmedEntityRenderState;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class CustomBipedRenderer<T extends Mob, S extends HumanoidRenderState, M extends HumanoidModel<S>> extends MobRenderer<T, S, M> {
	private static final Identifier DEFAULT_RES_LOC = Identifier.withDefaultNamespace("textures/entity/steve.png");

	public CustomBipedRenderer(EntityRendererProvider.Context context, M humanoidModel, float shadowSize) {
		super(context, humanoidModel, shadowSize);
		this.addLayer(new CustomHeadLayer<>(this, context.getModelSet(), context.getPlayerSkinRenderCache()));
	}

	public void extractRenderState(T mob, S state, float partialTick) {
		super.extractRenderState(mob, state, partialTick);
		extractHumanoidRenderState(mob, state, partialTick, this.itemModelResolver);
		state.leftArmPose = this.getArmPose(mob, HumanoidArm.LEFT);
		state.rightArmPose = this.getArmPose(mob, HumanoidArm.RIGHT);
	}

	protected HumanoidModel.ArmPose getArmPose(T mob, HumanoidArm arm) {
		return ArmPose.EMPTY;
	}

	public static void extractHumanoidRenderState(LivingEntity entity, HumanoidRenderState reusedState, float partialTick, ItemModelResolver itemModelResolver) {
		ArmedEntityRenderState.extractArmedEntityRenderState(entity, reusedState, itemModelResolver, partialTick);
		reusedState.isCrouching = entity.isCrouching();
		reusedState.isFallFlying = entity.isFallFlying();
		reusedState.isVisuallySwimming = entity.isVisuallySwimming();
		reusedState.isPassenger = entity.isPassenger();
		reusedState.speedValue = 1.0F;
		if (reusedState.isFallFlying) {
			reusedState.speedValue = (float)entity.getDeltaMovement().lengthSqr();
			reusedState.speedValue /= 0.2F;
			reusedState.speedValue = reusedState.speedValue * reusedState.speedValue * reusedState.speedValue;
		}

		if (reusedState.speedValue < 1.0F) {
			reusedState.speedValue = 1.0F;
		}

		reusedState.attackTime = entity.getAttackAnim(partialTick);
		reusedState.swimAmount = entity.getSwimAmount(partialTick);
		reusedState.attackArm = getAttackArm(entity);
		reusedState.useItemHand = entity.getUsedItemHand();
		reusedState.maxCrossbowChargeDuration = (float) CrossbowItem.getChargeDuration(entity.getUseItem(), entity);
		reusedState.ticksUsingItem = entity.getTicksUsingItem();
		reusedState.isUsingItem = entity.isUsingItem();
		reusedState.elytraRotX = entity.elytraAnimationState.getRotX(partialTick);
		reusedState.elytraRotY = entity.elytraAnimationState.getRotY(partialTick);
		reusedState.elytraRotZ = entity.elytraAnimationState.getRotZ(partialTick);
		reusedState.headEquipment = getEquipmentIfRenderable(entity, EquipmentSlot.HEAD);
		reusedState.chestEquipment = getEquipmentIfRenderable(entity, EquipmentSlot.CHEST);
		reusedState.legsEquipment = getEquipmentIfRenderable(entity, EquipmentSlot.LEGS);
		reusedState.feetEquipment = getEquipmentIfRenderable(entity, EquipmentSlot.FEET);
	}

	private static ItemStack getEquipmentIfRenderable(LivingEntity entity, EquipmentSlot slot) {
		ItemStack itemstack = entity.getItemBySlot(slot);
		return HumanoidArmorLayer.shouldRender(itemstack, slot) ? itemstack.copy() : ItemStack.EMPTY;
	}

	private static HumanoidArm getAttackArm(LivingEntity entity) {
		HumanoidArm humanoidarm = entity.getMainArm();
		return entity.swingingArm == InteractionHand.MAIN_HAND ? humanoidarm : humanoidarm.getOpposite();
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@NotNull
	@Override
	public Identifier getTextureLocation(@NotNull S state) {
		return DEFAULT_RES_LOC;
	}
}