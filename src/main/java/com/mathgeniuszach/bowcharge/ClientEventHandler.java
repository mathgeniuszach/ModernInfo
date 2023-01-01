package com.mathgeniuszach.bowcharge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientEventHandler {

    private BowChargeGui bowChargeGui;

    public ClientEventHandler() {
        bowChargeGui = new BowChargeGui();
    }

    @SubscribeEvent(receiveCanceled = true)
    public void onEvent(RenderGameOverlayEvent.Pre event) {
        if (event.type == ElementType.HOTBAR) {
            Minecraft minecraft = Minecraft.getMinecraft();

            EntityPlayerSP player = minecraft.thePlayer;
            if (player == null) return;

            ItemStack stack = player.getEquipmentInSlot(0);
            if (stack == null) return;
    
            if (stack.getItem() instanceof ItemBow) {
                bowChargeGui.render(event.resolution.getScaledWidth(), event.resolution.getScaledHeight());
            }
        }
    }
}
