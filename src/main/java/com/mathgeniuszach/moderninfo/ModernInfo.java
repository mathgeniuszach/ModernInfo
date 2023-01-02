package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ModernInfoConfig;
import com.mathgeniuszach.moderninfo.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.*;

@Mod(
    modid = ModernInfo.MODID,
    version = ModernInfo.VERSION,
    guiFactory = "com.mathgeniuszach.moderninfo.config.ConfigGuiFactory"
)
public class ModernInfo
{
    public static final String MODID = "moderninfo";
    public static final String VERSION = "1.1";

    @Mod.Instance(ModernInfo.MODID)
    public static ModernInfo INSTANCE;

    @SidedProxy(
        clientSide = "com.mathgeniuszach.moderninfo.proxy.ClientProxy",
        serverSide = "com.mathgeniuszach.moderninfo.proxy.ServerProxy"
    )
    public static CommonProxy PROXY;

    public static ModernInfoConfig CONFIG;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        CONFIG = new ModernInfoConfig(event.getSuggestedConfigurationFile());
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