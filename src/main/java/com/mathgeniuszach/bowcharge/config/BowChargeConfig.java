package com.mathgeniuszach.bowcharge.config;

import java.io.File;
import java.util.List;

import com.mathgeniuszach.bowcharge.BowCharge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;

public class BowChargeConfig {
    public static Configuration CONFIG;

    public static int xOffset;
    public static int yOffset;
    public static int width;
    public static int height;

    public static int backgroundColor;
    public static int defaultColor;
    public static int disabledColor;
    public static int criticalColor;

    public static boolean pullingOnly;

    public static int getConfigColor(String key, String defColor) {
        String stringColor = CONFIG.get("client", key, defColor).getString();

        long color = 0xFFFF00FF;

        if (!stringColor.isEmpty()) {
            if (stringColor.charAt(0) == '#') {
                stringColor = stringColor.substring(1);
            }

            // try {
                switch (stringColor.length()) {
                    case 3:
                        color = Long.parseLong(""
                            +stringColor.charAt(0)+stringColor.charAt(0)
                            +stringColor.charAt(1)+stringColor.charAt(1)
                            +stringColor.charAt(2)+stringColor.charAt(2)
                        , 16) + 0xFF000000;
                        break;
                    case 4:
                        color = Long.parseLong(""
                            +stringColor.charAt(0)+stringColor.charAt(0)
                            +stringColor.charAt(1)+stringColor.charAt(1)
                            +stringColor.charAt(2)+stringColor.charAt(2)
                            +stringColor.charAt(3)+stringColor.charAt(3)
                            , 16);
                        break;
                    case 6:
                        color = Long.parseLong(stringColor, 16) + 0xFF000000;
                        break;
                    case 8:
                        color = Long.parseLong(stringColor, 16);
                        break;
                }
            // } catch (NumberFormatException e) {
            //     System.out.format("Failure to parse color \"%s\"", key);
            // }
        }

        return (int)color;
    }

    public static void loadConfig() {
        xOffset = CONFIG.get("client", "barXOffset", 0).getInt();
        yOffset = CONFIG.get("client", "barYOffset", -20).getInt();
        width = CONFIG.get("client", "barWidth", 50).getInt();
        height = CONFIG.get("client", "barHeight", 5).getInt();

        backgroundColor = getConfigColor("colorBackground", "80000000");
        defaultColor = getConfigColor("colorDefault", "80EEEEEE");
        disabledColor = getConfigColor("colorDisabled", "80808080");
        criticalColor = getConfigColor("colorCritical", "80FF0000");

        pullingOnly = CONFIG.get("client", "pullingOnly", false).getBoolean();

        if (CONFIG.hasChanged()) CONFIG.save();
    }

    public BowChargeConfig(File file) {
        MinecraftForge.EVENT_BUS.register(this);
        CONFIG = new Configuration(file);
        CONFIG.load();
        loadConfig();
    }

    @SideOnly(Side.CLIENT)
	public List<IConfigElement> getClientGuiElements() {
		return new ConfigElement(BowChargeConfig.CONFIG.getCategory("client")).getChildElements();
	}
	
	public String getFileName(){
		return CONFIG.toString();
	}

    @SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(BowCharge.MODID)) {
			BowChargeConfig.loadConfig();
		}
	}
}
