package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.*;

import java.util.Map;

public class ConfigScreen  extends ModSettingScreen {

    public ConfigScreen(String name) {
        super(name);
        for (Map.Entry<String, Boolean> entry : ConfigAlphaWorldGen.instance.nameToBool.entrySet()) {
            addSetting(entry.getKey(), entry.getValue());
        }
    }

    private void addSetting(String mapName, boolean defValue) {
        WidgetBoolean widget = new WidgetBoolean(new SettingBooleansAlphaWorldGen(mapName, defValue), ConfigAlphaWorldGen.instance.optionText.get(mapName), "ON", "OFF");
        this.append(widget);
    }
}
