package com.mathgeniuszach.bowcharge;

import com.mathgeniuszach.bowcharge.config.BowChargeConfig;
import com.mathgeniuszach.bowcharge.proxy.CommonProxy;

import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
    modid = BowCharge.MODID,
    version = BowCharge.VERSION,
    guiFactory = "com.mathgeniuszach.bowcharge.config.ConfigGuiFactory"
)
public class BowCharge
{
    public static final String MODID = "bowcharge";
    public static final String VERSION = "1.0";

    @Mod.Instance(BowCharge.MODID)
    public static BowCharge INSTANCE;

    @SidedProxy(
        clientSide = "com.mathgeniuszach.bowcharge.proxy.ClientProxy",
        serverSide = "com.mathgeniuszach.bowcharge.proxy.ServerProxy"
    )
    public static CommonProxy PROXY;

    public static BowChargeConfig CONFIG;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONFIG = new BowChargeConfig(event.getSuggestedConfigurationFile());
        PROXY.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        PROXY.postInit(event);
    }

}