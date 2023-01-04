package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ConfigData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;

public class BowChargeGui extends Gui {
    public void render(int screenWidth, int screenHeight) {
        try {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = mc.thePlayer;
        
            int i = player.getItemInUseDuration();

            float f = (float)i / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;
            if (f < 0.0F) f = 0.0F;
            if (f > 1.0F) f = 1.0F;

            int color = ConfigData.defaultColor;
            if ((double)f < 0.1D) {
                // Bow will not shoot an arrow
                color = ConfigData.disabledColor;
                if (ConfigData.pullingOnly) return;
            } else if (f >= 1.0) {
                // Critical zone
                color = ConfigData.criticalColor;
            }

            // Outer Rect
            drawRect(
                screenWidth/2-ConfigData.width/2-1+ConfigData.xOffset,
                screenHeight/2-1+ConfigData.yOffset,
                screenWidth/2+ConfigData.width/2+1+ConfigData.xOffset,
                screenHeight/2+ConfigData.height+1+ConfigData.yOffset,
                ConfigData.backgroundColor
            );
            // Inner Rect
            drawRect(
                screenWidth/2-ConfigData.width/2+ConfigData.xOffset,
                screenHeight/2+ConfigData.yOffset,
                screenWidth/2-ConfigData.width/2+ConfigData.xOffset+(int)(ConfigData.width*f),
                screenHeight/2+ConfigData.height+ConfigData.yOffset,
                color
            );
        } catch (Exception e) {
            System.err.println("Error in BowChargeGui!!!");
            e.printStackTrace();
        }
    }
}
