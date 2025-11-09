package AlphaWorldGen.sapi;

import net.minecraft.src.DimensionBase;

public class DimensionAlphaWorldGen extends DimensionBase {

    public DimensionAlphaWorldGen() {
        super(0, WorldProviderAlpha.class, null);
        this.name = "Overworld";
    }

}
