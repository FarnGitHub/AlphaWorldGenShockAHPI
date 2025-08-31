package net.minecraft.src;

import net.minecraft.client.Minecraft;
import net.minecraft.src.AlphaWorldGen.ConfigAlphaWorldGen;
import net.minecraft.src.AlphaWorldGen.DimensionAlphaWorldGen;
import net.minecraft.src.AlphaWorldGen.ConfigScreen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mod_AlphaWorldGen extends BaseMod {

    public static final BiomeGenBase alphaWinter = (new BiomeGenBase()).setColor(0).setBiomeName("Alpha Winter").setEnableSnow().func_4124_a(0);
    public static final BiomeGenBase alphaBiome = (new BiomeGenBase()).setColor(0).setBiomeName("Alpha").func_4124_a(0);

    private static final List<Boolean> isSnowyBiome = new ArrayList<Boolean>();

    public static boolean hasAlwaySnowMod = false;

    @Override
    public void ModsLoaded() {
        ConfigAlphaWorldGen.instance.initOption();
        DimensionBase.list.remove(DimensionBase.getDimByNumber(0));
        new DimensionAlphaWorldGen();
        hasAlwaySnowMod = doesClassExist("mod_AlwaysSnow") || doesClassExist("net.minecraft.src.mod_AlwaysSnow");

        if(doesClassExist("GuiApiHelper") || doesClassExist("net.minecraft.src.GuiApiHelper")) {
            new ConfigScreen("Alpha World Gen");
        }
    }

    public mod_AlphaWorldGen() {
        ModLoader.SetInGameHook(this, true, false);
    }

    public boolean OnTickInGame(Minecraft minecraft) {
        if(ConfigAlphaWorldGen.instance.wintermode() && ConfigAlphaWorldGen.instance.alphaworldgen()) {
            minecraft.theWorld.prevRainingStrength = 0.3F;
            minecraft.theWorld.rainingStrength = 0.3F;
        }
        return true;
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
