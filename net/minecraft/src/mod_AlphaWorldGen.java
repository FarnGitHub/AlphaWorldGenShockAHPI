package net.minecraft.src;

import net.minecraft.src.AlphaWorldGen.DimensionAlphaWorldGen;
import net.minecraft.src.AlphaWorldGen.ConfigScreen;

public class mod_AlphaWorldGen extends BaseMod {

    @Override
    public void ModsLoaded() {
        DimensionBase.list.remove(DimensionBase.getDimByNumber(0));
        new DimensionAlphaWorldGen();
        if(doesClassExist("GuiApiHelper") || doesClassExist("net.minecraft.src.GuiApiHelper")) {
            new ConfigScreen("Alpha World Gen");
        }
    }

    public String Version() {
        return "1.0";
    }

    public static boolean doesClassExist(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
