package com.mathgeniuszach.moderninfo.proxy;

import com.mathgeniuszach.moderninfo.ClientEventHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.*;

public class ClientProxy extends CommonProxy {
    @Override
    public void postInit(FMLPostInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
