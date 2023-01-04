package com.mathgeniuszach.moderninfo;

import org.lwjgl.opengl.GL11;

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
            FontRenderer fr = mc.fontRendererObj;

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

            GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
            GL11.glPushMatrix();

            fr.drawString(
                ConfigData.meleeMarker,
                screenWidth/2+ConfigData.meleeXOffset-fr.getStringWidth(ConfigData.meleeMarker)/2+1,
                screenHeight/2+ConfigData.meleeYOffset,
                color
            );
            
            GL11.glPopMatrix();
            GL11.glPopAttrib();
        } catch (Exception e) {
            System.err.println("Error in HitGui!!!");
            e.printStackTrace();
        }
    }
}
