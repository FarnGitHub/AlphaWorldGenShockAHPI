package AlphaWorldGen;

import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_AlphaWorldGen;
import worldsettings.WorldSettingsConfiguration;
import worldsettings.api.gui.impl.GuiButtonSwitch;
import worldsettings.api.gui.impl.GuiSimpleConfiguration;
import worldsettings.api.settings.ConfigurationSupplier;
import worldsettings.api.settings.SettingSupplier;
import worldsettings.api.settings.impl.ConfigurationDetailedSlot;
import worldsettings.api.settings.impl.Setting;

import java.util.HashMap;
import java.util.Map;


public class ConfigAlphaWorldGen {

    public static final HashMap<String, Boolean> nameToBool = new HashMap<String, Boolean>();
    public static final HashMap<String, String> optionText = new HashMap<String, String>();

    public static final String LANG_ALPHAWORLDGEN = "farn.alphaworld";
    private static GuiSimpleConfiguration generatorAlphaGUI = new GuiSimpleConfiguration(LANG_ALPHAWORLDGEN) {

        @Override
        public void initGui() {
            this.registeredButtons.clear();
            for (Map.Entry<String, Boolean> entry : nameToBool.entrySet()) {
                this.registeredButtons.add(new GuiButtonSwitch(this.getNextButtonID(), 0, 0, optionText.get(entry.getKey()), generatorAlphaConfiguration.get(entry.getKey())));
            }

            this.initButtons();
        }

    };
    public static ConfigurationSupplier generatorAlphaConfiguration = new ConfigurationDetailedSlot(generatorAlphaGUI, "alphaWorldGen", LANG_ALPHAWORLDGEN, "", "");

    private static void addSetting(String mapName, String normalName) {
        nameToBool.put(mapName, false);
        optionText.put(mapName, normalName);
        SettingSupplier<Boolean> setting = new Setting<Boolean>(mapName, false, Boolean.class);
        generatorAlphaConfiguration.put(setting);
    }

    public static boolean alphaworldgen() {
        return (boolean)generatorAlphaConfiguration.get("enabled").getValue();
    }

    public static boolean wintermode() {
        return (boolean)generatorAlphaConfiguration.get("winter").getValue();
    }

    static {
        addSetting("winter", "Winter Mode");
        addSetting("enabled", "Enabled");
        ModLoader.AddLocalization(ConfigAlphaWorldGen.LANG_ALPHAWORLDGEN, "Alpha Terrain Generation");
        WorldSettingsConfiguration.registerConfiguration(generatorAlphaConfiguration);
    }

}
