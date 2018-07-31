package com.Mrbysco.InstrumentalMobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Mrbysco.InstrumentalMobs.config.InstrumentalConfigGen;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalLootTables;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalMobRegistry;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalSounds;
import com.Mrbysco.InstrumentalMobs.init.InstrumentalTab;
import com.Mrbysco.InstrumentalMobs.proxy.CommonProxy;

import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, 
	name = Reference.MOD_NAME, 
	version = Reference.VERSION, 
	acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)

public class InstrumentalMobs {
	@Instance(Reference.MOD_ID)
	public static InstrumentalMobs instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);
		
	public static InstrumentalTab instrumentalTab = new InstrumentalTab();
	public static final DamageSource soundDamage = new DamageSource(Reference.MOD_PREFIX + "soundDamage");

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{		
		logger.info("Registering config");
		MinecraftForge.EVENT_BUS.register(new InstrumentalConfigGen());

		InstrumentalLootTables.registerLootTables();
		InstrumentalSounds.registerSounds();
		
		InstrumentalMobRegistry.register();
		
		proxy.PreInit();
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event)
	{
		InstrumentalMobRegistry.registerBiomes();
		
		proxy.Init();
    }
	
	@EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		proxy.PostInit();
    }
}