package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ModernInfoConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;

public class BowChargeGui extends Gui {
    public void render(int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        
        int i = player.getItemInUseDuration();

        float f = (float)i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f < 0.0F) f = 0.0F;
        if (f > 1.0F) f = 1.0F;

        int color = ModernInfoConfig.defaultColor;
        if ((double)f < 0.1D) {
            // Bow will not shoot an arrow
            color = ModernInfoConfig.disabledColor;
            if (ModernInfoConfig.pullingOnly) return;
        } else if (f >= 1.0) {
            // Critical zone
            color = ModernInfoConfig.criticalColor;
        }

        // Outer Rect
        drawRect(
            screenWidth/2-ModernInfoConfig.width/2-1+ModernInfoConfig.xOffset,
            screenHeight/2-1+ModernInfoConfig.yOffset,
            screenWidth/2+ModernInfoConfig.width/2+1+ModernInfoConfig.xOffset,
            screenHeight/2+ModernInfoConfig.height+1+ModernInfoConfig.yOffset,
            ModernInfoConfig.backgroundColor
        );
        // Inner Rect
        drawRect(
            screenWidth/2-ModernInfoConfig.width/2+ModernInfoConfig.xOffset,
            screenHeight/2+ModernInfoConfig.yOffset,
            screenWidth/2-ModernInfoConfig.width/2+ModernInfoConfig.xOffset+(int)(ModernInfoConfig.width*f),
            screenHeight/2+ModernInfoConfig.height+ModernInfoConfig.yOffset,
            color
        );
    }
}
