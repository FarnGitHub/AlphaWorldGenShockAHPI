package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.DimensionBase;

public class DimensionAlphaWorldGen extends DimensionBase {

    public DimensionAlphaWorldGen() {
        super(0, WorldProviderAlpha.class, (Class)null);
        this.name = "Overworld";
    }

}
