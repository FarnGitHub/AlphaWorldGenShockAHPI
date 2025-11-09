package net.minecraft.src;

import net.minecraft.client.Minecraft;
import AlphaWorldGen.ConfigAlphaWorldGen;
import AlphaWorldGen.sapi.DimensionAlphaWorldGen;

public class mod_AlphaWorldGen extends BaseMod {

    public static final BiomeGenBase alphaWinter = (new BiomeGenBase()).setColor(0).setBiomeName("Alpha Winter").setEnableSnow().func_4124_a(0);
    public static final BiomeGenBase alphaBiome = (new BiomeGenBase()).setColor(0).setBiomeName("Alpha").func_4124_a(0);

    @Override
    public void ModsLoaded() {
        ConfigAlphaWorldGen.generatorAlphaConfiguration.getName();
        DimensionBase.list.remove(DimensionBase.getDimByNumber(0));
        new DimensionAlphaWorldGen();
    }

    public mod_AlphaWorldGen() {
        ModLoader.SetInGameHook(this, true, false);
    }

    public boolean OnTickInGame(Minecraft minecraft) {
        if(ConfigAlphaWorldGen.wintermode() && ConfigAlphaWorldGen.alphaworldgen()) {
            minecraft.theWorld.prevRainingStrength = 0.3F;
            minecraft.theWorld.rainingStrength = 0.3F;
        }
        return true;
    }

    public String Version() {
        return "MangoPack 1.0";
    }
}
