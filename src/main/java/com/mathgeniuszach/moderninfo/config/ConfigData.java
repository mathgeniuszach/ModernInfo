package com.mathgeniuszach.moderninfo.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.mathgeniuszach.moderninfo.ModernInfo;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;

public class ConfigData {
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

    public static boolean bowBarDisabled;



    public static String meleeMarker;

    public static int meleeXOffset;
    public static int meleeYOffset;
    
    public static int meleeMarkerColor;
    public static int meleeMarkerCritColor;

    public static boolean meleeMarkerDisabled;
    public static boolean meleeMarkerOnPlayers;



    public static boolean customNamesVisible;

    public ConfigData(File file) {
        MinecraftForge.EVENT_BUS.register(this);
        CONFIG = new Configuration(file);
        CONFIG.load();
        loadConfig();
    }

    public static void loadConfig() {
        try {
            xOffset = CONFIG.get("bow", "barXOffset", 0).getInt();
            yOffset = CONFIG.get("bow", "barYOffset", -20).getInt();
            width = CONFIG.get("bow", "barWidth", 50).getInt();
            height = CONFIG.get("bow", "barHeight", 5).getInt();

            backgroundColor = getConfigColor("bow", "colorBackground", "80000000");
            defaultColor = getConfigColor("bow", "colorDefault", "80EEEEEE");
            disabledColor = getConfigColor("bow", "colorDisabled", "80808080");
            criticalColor = getConfigColor("bow", "colorCritical", "80FF0000");

            pullingOnly = CONFIG.get("bow", "pullingOnly", false).getBoolean();
            bowBarDisabled = CONFIG.get("bow", "barDisabled", false).getBoolean();



            meleeMarker = CONFIG.get("melee", "markerText", "-+-").getString();

            meleeXOffset = CONFIG.get("melee", "markerXOffset", 0).getInt();
            meleeYOffset = CONFIG.get("melee", "markerYOffset", -20).getInt();

            meleeMarkerColor = getConfigColor("melee", "markerDefaultColor", "FFFFFF");
            meleeMarkerCritColor = getConfigColor("melee", "markerCritColor", "FF0000");

            meleeMarkerOnPlayers = CONFIG.get("melee", "showOnPlayers", false).getBoolean();
            meleeMarkerDisabled = CONFIG.get("melee", "disabled", false).getBoolean();



            customNamesVisible = CONFIG.get("other", "customNamesVisible", true).getBoolean();



            if (CONFIG.hasChanged()) CONFIG.save();
        } catch (Exception e) {
            System.err.println("Error in ConfigData!!!");
            e.printStackTrace();
        }
    }

    public static int getConfigColor(String category, String key, String defColor) {
        String stringColor = CONFIG.get(category, key, defColor).getString();

        long color = 0xFFFF00FF;

        if (!stringColor.isEmpty()) {
            if (stringColor.charAt(0) == '#') {
                stringColor = stringColor.substring(1);
            }

            try {
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
            } catch (NumberFormatException e) {
                System.out.format("Failure to parse color \"%s\"", key);
            }
        }

        return (int)color;
    }

    @SideOnly(Side.CLIENT)
	public List<IConfigElement> getClientGuiElements() {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.add(new ConfigElement(ConfigData.CONFIG.getCategory("bow")));
        list.add(new ConfigElement(ConfigData.CONFIG.getCategory("melee")));
        list.add(new ConfigElement(ConfigData.CONFIG.getCategory("other")));
        return list;
	}
	
	public String getFileName(){
		return CONFIG.toString();
	}

    @SubscribeEvent
	public void onConfigChanged(OnConfigChangedEvent event) {
		if (event.modID.equalsIgnoreCase(ModernInfo.MODID)) {
			ConfigData.loadConfig();
		}
	}
}
