package com.mathgeniuszach.moderninfo;

import com.mathgeniuszach.moderninfo.config.ModernInfoConfig;

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
        if (event.type == ElementType.HOTBAR) {
            Minecraft minecraft = Minecraft.getMinecraft();

            EntityPlayer player = minecraft.thePlayer;
            if (player == null) return;

            // Show bow charge bar if holding bow
            ItemStack stack = player.getEquipmentInSlot(0);
            if (stack != null) {
                Item item = stack.getItem();
                if (item instanceof ItemBow && !ModernInfoConfig.bowBarDisabled) {
                    bowChargeGui.render(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
                    // Exit, if we're showing the bow bar, no need to show the hit marker
                    return;
                }
            }

            // Show crit marker if looking at entity
            if (!ModernInfoConfig.meleeMarkerDisabled) {
                MovingObjectPosition mop = minecraft.objectMouseOver;
                if (mop == null || mop.entityHit == null) return;
                if (!ModernInfoConfig.meleeMarkerOnPlayers && mop.entityHit instanceof EntityPlayer) return;

                hitGui.render(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
            }
        }
    }
}
