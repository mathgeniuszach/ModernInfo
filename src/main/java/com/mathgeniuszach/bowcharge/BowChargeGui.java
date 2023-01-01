package com.mathgeniuszach.bowcharge;

import com.mathgeniuszach.bowcharge.config.BowChargeConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;

public class BowChargeGui extends Gui {

    public void render(int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;
        
        FontRenderer fr = mc.fontRendererObj;
        int i = player.getItemInUseDuration();

        float f = (float)i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f < 0.0F) f = 0.0F;
        if (f > 1.0F) f = 1.0F;

        int color = BowChargeConfig.defaultColor;
        if ((double)f < 0.1D) {
            // Bow will not shoot an arrow
            color = BowChargeConfig.disabledColor;
            if (BowChargeConfig.pullingOnly) return;
        } else if (f >= 1.0) {
            // Critical zone
            color = BowChargeConfig.criticalColor;
        }

        // Outer Rect
        drawRect(
            screenWidth/2-BowChargeConfig.width/2-1+BowChargeConfig.xOffset,
            screenHeight/2-1+BowChargeConfig.yOffset,
            screenWidth/2+BowChargeConfig.width/2+1+BowChargeConfig.xOffset,
            screenHeight/2+BowChargeConfig.height+1+BowChargeConfig.yOffset,
            BowChargeConfig.backgroundColor
        );
        // Inner Rect
        drawRect(
            screenWidth/2-BowChargeConfig.width/2+BowChargeConfig.xOffset,
            screenHeight/2+BowChargeConfig.yOffset,
            screenWidth/2-BowChargeConfig.width/2+BowChargeConfig.xOffset+(int)(BowChargeConfig.width*f),
            screenHeight/2+BowChargeConfig.height+BowChargeConfig.yOffset,
            color
        );
    }
}
