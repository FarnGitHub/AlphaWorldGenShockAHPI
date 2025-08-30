package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.NoiseGenerator;

import java.util.Random;

public class NoiseGeneratorPerlinAlpha extends NoiseGenerator {
	private int[] permutations;
	public double xCoord;
	public double yCoord;
	public double zCoord;

	public NoiseGeneratorPerlinAlpha(Random random) {
		this.permutations = new int[512];
		this.xCoord = random.nextDouble() * 256.0D;
		this.yCoord = random.nextDouble() * 256.0D;
		this.zCoord = random.nextDouble() * 256.0D;

		int j;
		for(j = 0; j < 256; this.permutations[j] = j++) {
		}

		for(j = 0; j < 256; ++j) {
			int k = random.nextInt(256 - j) + j;
			int l = this.permutations[j];
			this.permutations[j] = this.permutations[k];
			this.permutations[k] = l;
			this.permutations[j + 256] = this.permutations[j];
		}

	}

	public double generateNoise(double d, double d1, double d2) {
		double d3 = d + this.xCoord;
		double d4 = d1 + this.yCoord;
		double d5 = d2 + this.zCoord;
		int i = (int)d3;
		int j = (int)d4;
		int k = (int)d5;
		if(d3 < (double)i) {
			--i;
		}

		if(d4 < (double)j) {
			--j;
		}

		if(d5 < (double)k) {
			--k;
		}

		int l = i & 255;
		int i1 = j & 255;
		int j1 = k & 255;
		d3 -= (double)i;
		d4 -= (double)j;
		d5 -= (double)k;
		double d6 = d3 * d3 * d3 * (d3 * (d3 * 6.0D - 15.0D) + 10.0D);
		double d7 = d4 * d4 * d4 * (d4 * (d4 * 6.0D - 15.0D) + 10.0D);
		double d8 = d5 * d5 * d5 * (d5 * (d5 * 6.0D - 15.0D) + 10.0D);
		int k1 = this.permutations[l] + i1;
		int l1 = this.permutations[k1] + j1;
		int i2 = this.permutations[k1 + 1] + j1;
		int j2 = this.permutations[l + 1] + i1;
		int k2 = this.permutations[j2] + j1;
		int l2 = this.permutations[j2 + 1] + j1;
		return this.lerp(d8, this.lerp(d7, this.lerp(d6, this.grad(this.permutations[l1], d3, d4, d5), this.grad(this.permutations[k2], d3 - 1.0D, d4, d5)), this.lerp(d6, this.grad(this.permutations[i2], d3, d4 - 1.0D, d5), this.grad(this.permutations[l2], d3 - 1.0D, d4 - 1.0D, d5))), this.lerp(d7, this.lerp(d6, this.grad(this.permutations[l1 + 1], d3, d4, d5 - 1.0D), this.grad(this.permutations[k2 + 1], d3 - 1.0D, d4, d5 - 1.0D)), this.lerp(d6, this.grad(this.permutations[i2 + 1], d3, d4 - 1.0D, d5 - 1.0D), this.grad(this.permutations[l2 + 1], d3 - 1.0D, d4 - 1.0D, d5 - 1.0D))));
	}

	public final double lerp(double d, double d1, double d2) {
		return d1 + d * (d2 - d1);
	}

	public final double grad(int i, double d, double d1, double d2) {
		int j = i & 15;
		double d3 = j < 8 ? d : d1;
		double d4 = j < 4 ? d1 : (j != 12 && j != 14 ? d2 : d);
		return ((j & 1) == 0 ? d3 : -d3) + ((j & 2) == 0 ? d4 : -d4);
	}

	public double func_801_a(double d, double d1) {
		return this.generateNoise(d, d1, 0.0D);
	}

	public void func_805_a(double[] ad, double d, double d1, double d2, int i, int j, int k, double d3, double d4, double d5, double d6) {
		int l = 0;
		double d7 = 1.0D / d6;
		int i1 = -1;
		boolean flag = false;
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		boolean flag4 = false;
		boolean flag5 = false;
		double d8 = 0.0D;
		double d9 = 0.0D;
		double d10 = 0.0D;
		double d11 = 0.0D;

		for(int j1 = 0; j1 < i; ++j1) {
			double d12 = (d + (double)j1) * d3 + this.xCoord;
			int k1 = (int)d12;
			if(d12 < (double)k1) {
				--k1;
			}

			int l1 = k1 & 255;
			d12 -= (double)k1;
			double d13 = d12 * d12 * d12 * (d12 * (d12 * 6.0D - 15.0D) + 10.0D);

			for(int i2 = 0; i2 < k; ++i2) {
				double d14 = (d2 + (double)i2) * d5 + this.zCoord;
				int j2 = (int)d14;
				if(d14 < (double)j2) {
					--j2;
				}

				int k2 = j2 & 255;
				d14 -= (double)j2;
				double d15 = d14 * d14 * d14 * (d14 * (d14 * 6.0D - 15.0D) + 10.0D);

				for(int l2 = 0; l2 < j; ++l2) {
					double d16 = (d1 + (double)l2) * d4 + this.yCoord;
					int i3 = (int)d16;
					if(d16 < (double)i3) {
						--i3;
					}

					int j3 = i3 & 255;
					d16 -= (double)i3;
					double d17 = d16 * d16 * d16 * (d16 * (d16 * 6.0D - 15.0D) + 10.0D);
					if(l2 == 0 || j3 != i1) {
						i1 = j3;
						int d18 = this.permutations[l1] + j3;
						int l3 = this.permutations[d18] + k2;
						int d19 = this.permutations[d18 + 1] + k2;
						int j4 = this.permutations[l1 + 1] + j3;
						int d20 = this.permutations[j4] + k2;
						int l4 = this.permutations[j4 + 1] + k2;
						d8 = this.lerp(d13, this.grad(this.permutations[l3], d12, d16, d14), this.grad(this.permutations[d20], d12 - 1.0D, d16, d14));
						d9 = this.lerp(d13, this.grad(this.permutations[d19], d12, d16 - 1.0D, d14), this.grad(this.permutations[l4], d12 - 1.0D, d16 - 1.0D, d14));
						d10 = this.lerp(d13, this.grad(this.permutations[l3 + 1], d12, d16, d14 - 1.0D), this.grad(this.permutations[d20 + 1], d12 - 1.0D, d16, d14 - 1.0D));
						d11 = this.lerp(d13, this.grad(this.permutations[d19 + 1], d12, d16 - 1.0D, d14 - 1.0D), this.grad(this.permutations[l4 + 1], d12 - 1.0D, d16 - 1.0D, d14 - 1.0D));
					}

					double d64 = this.lerp(d17, d8, d9);
					double d65 = this.lerp(d17, d10, d11);
					double d66 = this.lerp(d15, d64, d65);
					int i10001 = l++;
					ad[i10001] += d66 * d7;
				}
			}
		}

	}
}
