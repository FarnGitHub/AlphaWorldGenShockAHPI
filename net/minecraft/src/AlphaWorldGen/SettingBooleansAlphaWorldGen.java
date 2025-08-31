package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.*;

public class SettingBooleansAlphaWorldGen extends SettingBoolean {
    private String nameFarn = "nan";

    public SettingBooleansAlphaWorldGen(String name, Boolean def) {
        super(name, def);
        this.nameFarn = name;
    }

    public void set(Boolean v, String context) {
        this.values.put(context, v);
        ConfigAlphaWorldGen.instance.nameToBool.replace(this.nameFarn, v);
        ConfigAlphaWorldGen.instance.saveOptions();
    }

    public Boolean get(String context) {
        return ConfigAlphaWorldGen.instance.nameToBool.getOrDefault(this.nameFarn, false);
    }
}
