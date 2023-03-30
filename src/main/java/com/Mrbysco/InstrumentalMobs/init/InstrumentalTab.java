package com.mrbysco.instrumentalmobs.init;

import com.mrbysco.instrumentalmobs.Reference;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class InstrumentalTab {
	private static CreativeModeTab INSTRUMENTAL_TAB;

	@SubscribeEvent
	public void registerCreativeTabs(final CreativeModeTabEvent.Register event) {
		INSTRUMENTAL_TAB = event.registerCreativeModeTab(new ResourceLocation(Reference.MOD_ID, "instrumental_tab"), builder ->
				builder.icon(() -> new ItemStack(Blocks.NOTE_BLOCK))
						.title(Component.translatable("itemGroup.instrumentalmobs"))
						.displayItems((displayParameters, output) -> {
							List<ItemStack> stacks = InstrumentalRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
							output.acceptAll(stacks);
						}));
	}
}
