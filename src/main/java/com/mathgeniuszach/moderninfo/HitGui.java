package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ConfigData;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;

public class HitGui extends Gui {
    public void render(int screenWidth, int screenHeight) {
        try {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = mc.thePlayer;

            int color = ConfigData.meleeMarkerColor;
            if (
                player.fallDistance > 0.0F &&
                !player.onGround &&
                !player.isOnLadder() &&
                !player.isInWater() &&
                player.ridingEntity == null &&
                !player.isPotionActive(Potion.blindness)
            ) {
                color = ConfigData.meleeMarkerCritColor;
            }

            FontRenderer fr = mc.fontRendererObj;
            fr.drawString(
                ConfigData.meleeMarker,
                screenWidth/2+ConfigData.meleeXOffset-fr.getStringWidth(ConfigData.meleeMarker)/2+1,
                screenHeight/2+ConfigData.meleeYOffset,
                color
            );
        } catch (Exception e) {
            System.err.println("Error in HitGui!!!");
            e.printStackTrace();
        }
    }
}
