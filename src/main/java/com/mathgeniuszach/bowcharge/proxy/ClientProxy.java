package com.mathgeniuszach.bowcharge.proxy;

import com.mathgeniuszach.bowcharge.ClientEventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
