package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.WorldChunkManager;
import net.minecraft.src.mod_AlphaWorldGen;

import java.util.Arrays;

public class WorldChunkManagerAlpha extends WorldChunkManager {
    private boolean winter = false;

    public WorldChunkManagerAlpha() {
        this.winter = ConfigAlphaWorldGen.instance.wintermode();
    }

    public BiomeGenBase getBiomeGenAtChunkCoord(ChunkCoordIntPair chunkCoordIntPair1) {
        return this.winter ?  mod_AlphaWorldGen.alphaWinter : mod_AlphaWorldGen.alphaBiome;
    }

    public BiomeGenBase getBiomeGenAt(int i1, int i2) {
        return this.winter ? mod_AlphaWorldGen.alphaWinter : mod_AlphaWorldGen.alphaBiome;
    }

    public double getTemperature(int i1, int i2) {
        return this.winter ? 0.49D : 0.98D;
    }

    public BiomeGenBase[] func_4069_a(int i1, int i2, int i3, int i4) {
        this.field_4195_d = this.loadBlockGeneratorData(this.field_4195_d, i1, i2, i3, i4);
        return this.field_4195_d;
    }

    public double[] getTemperatures(double[] d1, int i2, int i3, int i4, int i5) {
        if(d1 == null || d1.length < i4 * i5) {
            d1 = new double[i4 * i5];
        }

        Arrays.fill(d1, 0, i4 * i5, this.winter ? 0.49D : 0.98D);
        return d1;
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBase1, int i2, int i3, int i4, int i5) {
        if(biomeGenBase1 == null || biomeGenBase1.length < i4 * i5) {
            biomeGenBase1 = new BiomeGenBase[i4 * i5];
        }

        if(this.temperature == null || this.temperature.length < i4 * i5) {
            this.temperature = new double[i4 * i5];
            this.humidity = new double[i4 * i5];
        }

        Arrays.fill(biomeGenBase1, 0, i4 * i5, this.winter ? mod_AlphaWorldGen.alphaWinter : mod_AlphaWorldGen.alphaBiome);
        Arrays.fill(this.humidity, 0, i4 * i5, this.winter ? 0.19D : 0.44D);
        Arrays.fill(this.temperature, 0, i4 * i5, this.winter ? 0.49D : 0.98D);
        return biomeGenBase1;
    }


}
