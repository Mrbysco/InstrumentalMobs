package com.Mrbysco.InstrumentalMobs.proxy;

import com.Mrbysco.InstrumentalMobs.entities.EntityCymbalHusk;
import com.Mrbysco.InstrumentalMobs.entities.EntityDrumZombie;
import com.Mrbysco.InstrumentalMobs.entities.EntityFrenchHornCreeper;
import com.Mrbysco.InstrumentalMobs.entities.EntityMaracaSpider;
import com.Mrbysco.InstrumentalMobs.entities.EntityMicrophoneGhast;
import com.Mrbysco.InstrumentalMobs.entities.EntityTubaEnderman;
import com.Mrbysco.InstrumentalMobs.entities.EntityXylophoneSkeletal;
import com.Mrbysco.InstrumentalMobs.entities.projectiles.EntitySoundWaves;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalBlocks;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalItems;
import com.Mrbysco.InstrumentalMobs.render.RenderCymbalZombie;
import com.Mrbysco.InstrumentalMobs.render.RenderDrumZombie;
import com.Mrbysco.InstrumentalMobs.render.RenderFrenchHornCreeper;
import com.Mrbysco.InstrumentalMobs.render.RenderMaracaSpider;
import com.Mrbysco.InstrumentalMobs.render.RenderMicrophoneGhast;
import com.Mrbysco.InstrumentalMobs.render.RenderSoundWaves;
import com.Mrbysco.InstrumentalMobs.render.RenderTubaEnderman;
import com.Mrbysco.InstrumentalMobs.render.RenderXylophoneSkeleton;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
