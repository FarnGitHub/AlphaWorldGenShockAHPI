package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.NoiseGenerator;

import java.util.Random;

public class NoiseGeneratorOctavesAlpha extends NoiseGenerator {
	private NoiseGeneratorPerlinAlpha[] generatorCollection;
	private int field_1191_b;

	public NoiseGeneratorOctavesAlpha(Random random, int i) {
		this.field_1191_b = i;
		this.generatorCollection = new NoiseGeneratorPerlinAlpha[i];

		for(int j = 0; j < i; ++j) {
			this.generatorCollection[j] = new NoiseGeneratorPerlinAlpha(random);
		}

	}

	public double func_806_a(double d, double d1) {
		double d2 = 0.0D;
		double d3 = 1.0D;

		for(int i = 0; i < this.field_1191_b; ++i) {
			d2 += this.generatorCollection[i].func_801_a(d * d3, d1 * d3) / d3;
			d3 /= 2.0D;
		}

		return d2;
	}

	public double[] generateNoiseOctaves(double[] ad, double d, double d1, double d2, int i, int j, int k, double d3, double d4, double d5) {
		if(ad == null) {
			ad = new double[i * j * k];
		} else {
			for(int d6 = 0; d6 < ad.length; ++d6) {
				ad[d6] = 0.0D;
			}
		}

		double d20 = 1.0D;

		for(int i1 = 0; i1 < this.field_1191_b; ++i1) {
			this.generatorCollection[i1].func_805_a(ad, d, d1, d2, i, j, k, d3 * d20, d4 * d20, d5 * d20, d20);
			d20 /= 2.0D;
		}

		return ad;
	}
}
