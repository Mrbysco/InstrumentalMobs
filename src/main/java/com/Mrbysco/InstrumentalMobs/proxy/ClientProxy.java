package com.mrbysco.instrumentalmobs.proxy;

import com.mrbysco.instrumentalmobs.entities.EntityCymbalHusk;
import com.mrbysco.instrumentalmobs.entities.EntityDrumZombie;
import com.mrbysco.instrumentalmobs.entities.EntityFrenchHornCreeper;
import com.mrbysco.instrumentalmobs.entities.EntityMaracaSpider;
import com.mrbysco.instrumentalmobs.entities.EntityMicrophoneGhast;
import com.mrbysco.instrumentalmobs.entities.EntityTubaEnderman;
import com.mrbysco.instrumentalmobs.entities.EntityXylophoneSkeletal;
import com.mrbysco.instrumentalmobs.entities.projectiles.EntityMicrophoneWave;
import com.mrbysco.instrumentalmobs.entities.projectiles.EntitySoundWaves;
import com.mrbysco.instrumentalmobs.init.InstrumentalBlocks;
import com.mrbysco.instrumentalmobs.init.InstrumentalItems;
import com.mrbysco.instrumentalmobs.render.RenderCymbalZombie;
import com.mrbysco.instrumentalmobs.render.RenderDrumZombie;
import com.mrbysco.instrumentalmobs.render.RenderFrenchHornCreeper;
import com.mrbysco.instrumentalmobs.render.RenderMaracaSpider;
import com.mrbysco.instrumentalmobs.render.RenderMicrophoneGhast;
import com.mrbysco.instrumentalmobs.render.RenderSoundWaves;
import com.mrbysco.instrumentalmobs.render.RenderTubaEnderman;
import com.mrbysco.instrumentalmobs.render.RenderXylophoneSkeleton;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy{
	@Override
	public void PreInit() {
		registerMobRendering();
	}

	@Override
	public void Init() {
			
	}

	@Override
	public void PostInit() {
		
	}
	
	public static void registerMobRendering() {
		RenderingRegistry.registerEntityRenderingHandler(EntityXylophoneSkeletal.class, RenderXylophoneSkeleton.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityTubaEnderman.class, RenderTubaEnderman.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityFrenchHornCreeper.class, RenderFrenchHornCreeper.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityDrumZombie.class, RenderDrumZombie.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityCymbalHusk.class, RenderCymbalZombie.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMaracaSpider.class, RenderMaracaSpider.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMicrophoneGhast.class, RenderMicrophoneGhast.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntitySoundWaves.class, RenderSoundWaves.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(EntityMicrophoneWave.class, renderManager -> new RenderSnowball(renderManager, InstrumentalItems.microphone, Minecraft.getMinecraft().getRenderItem()));
	}
	
	@SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event)
    {
        for(Item item : InstrumentalItems.ITEMS)
        {
        	ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
		}
        
        for(Block block : InstrumentalBlocks.BLOCKS)
        {
        	Item item = Item.getItemFromBlock(block);
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
    }
}
