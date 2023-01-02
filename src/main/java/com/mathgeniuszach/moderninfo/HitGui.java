package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ModernInfoConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class HitGui extends Gui {
    public void render(int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.thePlayer;

        int color = ModernInfoConfig.meleeMarkerColor;
        if (
            player.fallDistance > 0.0F &&
            !player.onGround &&
            !player.isOnLadder() &&
            !player.isInWater() &&
            player.ridingEntity == null &&
            !player.isPotionActive(Potion.blindness)
        ) {
            color = ModernInfoConfig.meleeMarkerCritColor;
        }

        FontRenderer fr = mc.fontRendererObj;
        fr.drawString(
            ModernInfoConfig.meleeMarker,
            screenWidth/2+ModernInfoConfig.meleeXOffset-fr.getStringWidth(ModernInfoConfig.meleeMarker)/2+1,
            screenHeight/2+ModernInfoConfig.meleeYOffset,
            color
        );
    }
}
