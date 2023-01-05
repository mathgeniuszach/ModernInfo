package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ConfigData;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {

    private BowChargeGui bowChargeGui;
    private HitGui hitGui;

    public ClientEventHandler() {
        bowChargeGui = new BowChargeGui();
        hitGui = new HitGui();
    }

    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        try {
            if (event.type == ElementType.HOTBAR) {
                Minecraft minecraft = Minecraft.getMinecraft();

                EntityPlayer player = minecraft.thePlayer;
                if (player == null) return;

                // Show bow charge bar if holding bow
                ItemStack stack = player.getEquipmentInSlot(0);
                if (stack != null) {
                    Item item = stack.getItem();
                    if (item instanceof ItemBow && !ConfigData.bowBarDisabled) {
                        bowChargeGui.render(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
                        // Exit, if we're showing the bow bar, no need to show the hit marker
                        return;
                    }
                }

                // Show crit marker if looking at entity
                if (!ConfigData.meleeMarkerDisabled) {
                    MovingObjectPosition mop = minecraft.objectMouseOver;
                    if (mop == null || mop.entityHit == null) return;
                    if (!ConfigData.meleeMarkerOnPlayers && mop.entityHit instanceof EntityPlayer) return;

                    hitGui.render(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
                }
            }
        } catch (Exception e) {
            System.err.println("Error in ClientEventHandler!!!");
            e.printStackTrace();
        }
    }
}
