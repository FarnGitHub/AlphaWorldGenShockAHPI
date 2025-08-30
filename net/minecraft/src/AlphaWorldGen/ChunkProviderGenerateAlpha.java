package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.*;

import java.util.Random;

public class ChunkProviderGenerateAlpha implements IChunkProvider {
	private Random rand;
	private NoiseGeneratorOctavesAlpha field_912_k;
	private NoiseGeneratorOctavesAlpha field_911_l;
	private NoiseGeneratorOctavesAlpha field_910_m;
	private NoiseGeneratorOctavesAlpha field_909_n;
	private NoiseGeneratorOctavesAlpha field_908_o;
	public NoiseGeneratorOctavesAlpha field_922_a;
	public NoiseGeneratorOctavesAlpha field_921_b;
	public NoiseGeneratorOctavesAlpha mobSpawnerNoise;
	private World worldObj;
	private double[] field_4180_q;
	private double[] sandNoise = new double[256];
	private double[] gravelNoise = new double[256];
	private double[] stoneNoise = new double[256];
	private MapGenBase field_902_u = new MapGenCaves();
	double[] field_4185_d;
	double[] field_4184_e;
	double[] field_4183_f;
	double[] field_4182_g;
	double[] field_4181_h;
	private boolean wintermode;

	public ChunkProviderGenerateAlpha(World world, long l) {
		this.worldObj = world;
		this.rand = new Random(l);
		this.field_912_k = new NoiseGeneratorOctavesAlpha(this.rand, 16);
		this.field_911_l = new NoiseGeneratorOctavesAlpha(this.rand, 16);
		this.field_910_m = new NoiseGeneratorOctavesAlpha(this.rand, 8);
		this.field_909_n = new NoiseGeneratorOctavesAlpha(this.rand, 4);
		this.field_908_o = new NoiseGeneratorOctavesAlpha(this.rand, 4);
		this.field_922_a = new NoiseGeneratorOctavesAlpha(this.rand, 10);
		this.field_921_b = new NoiseGeneratorOctavesAlpha(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctavesAlpha(this.rand, 8);
		wintermode = ConfigAlphaWorldGen.instance.wintermode();
	}

	public void generateTerrain(int i, int j, byte[] abyte0) {
		byte byte0 = 4;
		byte byte1 = 64;
		int k = byte0 + 1;
		byte byte2 = 17;
		int l = byte0 + 1;
		this.field_4180_q = this.func_4061_a(this.field_4180_q, i * byte0, 0, j * byte0, k, byte2, l);

		for(int i1 = 0; i1 < byte0; ++i1) {
			for(int j1 = 0; j1 < byte0; ++j1) {
				for(int k1 = 0; k1 < 16; ++k1) {
					double d = 0.125D;
					double d1 = this.field_4180_q[((i1 + 0) * l + j1 + 0) * byte2 + k1 + 0];
					double d2 = this.field_4180_q[((i1 + 0) * l + j1 + 1) * byte2 + k1 + 0];
					double d3 = this.field_4180_q[((i1 + 1) * l + j1 + 0) * byte2 + k1 + 0];
					double d4 = this.field_4180_q[((i1 + 1) * l + j1 + 1) * byte2 + k1 + 0];
					double d5 = (this.field_4180_q[((i1 + 0) * l + j1 + 0) * byte2 + k1 + 1] - d1) * d;
					double d6 = (this.field_4180_q[((i1 + 0) * l + j1 + 1) * byte2 + k1 + 1] - d2) * d;
					double d7 = (this.field_4180_q[((i1 + 1) * l + j1 + 0) * byte2 + k1 + 1] - d3) * d;
					double d8 = (this.field_4180_q[((i1 + 1) * l + j1 + 1) * byte2 + k1 + 1] - d4) * d;

					for(int l1 = 0; l1 < 8; ++l1) {
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i2 = 0; i2 < 4; ++i2) {
							int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1;
							short c = 128;
							double d14 = 0.25D;
							double d15 = d10;
							double d16 = (d11 - d10) * d14;

							for(int k2 = 0; k2 < 4; ++k2) {
								int l2 = 0;
								if(k1 * 8 + l1 < byte1) {
									if(wintermode && k1 * 8 + l1 >= byte1 - 1) {
										l2 = Block.ice.blockID;
									} else {
										l2 = Block.waterStill.blockID;
									}
								}

								if(d15 > 0.0D) {
									l2 = Block.stone.blockID;
								}

								abyte0[j2] = (byte)l2;
								j2 += c;
								d15 += d16;
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}

	}

	public void replaceBlocksForBiome(int i, int j, byte[] abyte0) {
		byte byte0 = 64;
		double d = 8.0D / 256D;
		this.sandNoise = this.field_909_n.generateNoiseOctaves(this.sandNoise, (double)(i * 16), (double)(j * 16), 0.0D, 16, 16, 1, d, d, 1.0D);
		this.gravelNoise = this.field_909_n.generateNoiseOctaves(this.gravelNoise, (double)(i * 16), 109.0134D, (double)(j * 16), 16, 1, 16, d, 1.0D, d);
		this.stoneNoise = this.field_908_o.generateNoiseOctaves(this.stoneNoise, (double)(i * 16), (double)(j * 16), 0.0D, 16, 16, 1, d * 2.0D, d * 2.0D, d * 2.0D);

		for(int k = 0; k < 16; ++k) {
			for(int l = 0; l < 16; ++l) {
				boolean flag = this.sandNoise[k + l * 16] + this.rand.nextDouble() * 0.2D > 0.0D;
				boolean flag1 = this.gravelNoise[k + l * 16] + this.rand.nextDouble() * 0.2D > 3.0D;
				int i1 = (int)(this.stoneNoise[k + l * 16] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D);
				int j1 = -1;
				byte byte1 = (byte)Block.grass.blockID;
				byte byte2 = (byte)Block.dirt.blockID;

				for(int k1 = 127; k1 >= 0; --k1) {
					int l1 = (l * 16 + k) * 128 + k1;
					if(k1 <= 0 + this.rand.nextInt(5)) {
						abyte0[l1] = (byte)Block.bedrock.blockID;
					} else {
						byte byte3 = abyte0[l1];
						if(byte3 == 0) {
							j1 = -1;
						} else if(byte3 == Block.stone.blockID) {
							if(j1 == -1) {
								if(i1 <= 0) {
									byte1 = 0;
									byte2 = (byte)Block.stone.blockID;
								} else if(k1 >= byte0 - 4 && k1 <= byte0 + 1) {
									byte1 = (byte)Block.grass.blockID;
									byte2 = (byte)Block.dirt.blockID;
									if(flag1) {
										byte1 = 0;
									}

									if(flag1) {
										byte2 = (byte)Block.gravel.blockID;
									}

									if(flag) {
										byte1 = (byte)Block.sand.blockID;
									}

									if(flag) {
										byte2 = (byte)Block.sand.blockID;
									}
								}

								if(k1 < byte0 && byte1 == 0) {
									byte1 = (byte)Block.waterMoving.blockID;
								}

								j1 = i1;
								if(k1 >= byte0 - 1) {
									abyte0[l1] = byte1;
								} else {
									abyte0[l1] = byte2;
								}
							} else if(j1 > 0) {
								--j1;
								abyte0[l1] = byte2;
								if(j1 == 0 && byte2 == Block.sand.blockID) {
									j1 = this.rand.nextInt(4);
									byte2 = (byte)Block.sandStone.blockID;
								}
							}
						}
					}
				}
			}
		}

	}

	public Chunk prepareChunk(int i, int j) {
		return this.provideChunk(i, j);
	}

	public Chunk provideChunk(int i, int j) {
		this.rand.setSeed((long)i * 341873128712L + (long)j * 132897987541L);
		byte[] abyte0 = new byte[32768];
		Chunk chunk = new Chunk(this.worldObj, abyte0, i, j);
		this.generateTerrain(i, j, abyte0);
		this.replaceBlocksForBiome(i, j, abyte0);
		this.field_902_u.func_867_a(this, this.worldObj, i, j, abyte0);
		chunk.func_1024_c();
		return chunk;
	}

	private double[] func_4061_a(double[] ad, int i, int j, int k, int l, int i1, int j1) {
		if(ad == null) {
			ad = new double[l * i1 * j1];
		}

		double d = 684.412D;
		double d1 = 684.412D;
		this.field_4182_g = this.field_922_a.generateNoiseOctaves(this.field_4182_g, (double)i, (double)k, (double)k, l, 1, j1, 1.0D, 0.0D, 1.0D);
		this.field_4181_h = this.field_921_b.generateNoiseOctaves(this.field_4181_h, (double)i, (double)k, (double)k, l, 1, j1, 100.0D, 0.0D, 100.0D);
		this.field_4185_d = this.field_910_m.generateNoiseOctaves(this.field_4185_d, (double)i, (double)j, (double)k, l, i1, j1, d / 80.0D, d1 / 160.0D, d / 80.0D);
		this.field_4184_e = this.field_912_k.generateNoiseOctaves(this.field_4184_e, (double)i, (double)j, (double)k, l, i1, j1, d, d1, d);
		this.field_4183_f = this.field_911_l.generateNoiseOctaves(this.field_4183_f, (double)i, (double)j, (double)k, l, i1, j1, d, d1, d);
		int k1 = 0;
		int l1 = 0;

		for(int i2 = 0; i2 < l; ++i2) {
			for(int j2 = 0; j2 < j1; ++j2) {
				double d2 = (this.field_4182_g[l1] + 256.0D) / 512.0D;
				if(d2 > 1.0D) {
					d2 = 1.0D;
				}

				double d3 = 0.0D;
				double d4 = this.field_4181_h[l1] / 8000.0D;
				if(d4 < 0.0D) {
					d4 = -d4;
				}

				d4 = d4 * 3.0D - 3.0D;
				if(d4 < 0.0D) {
					d4 /= 2.0D;
					if(d4 < -1.0D) {
						d4 = -1.0D;
					}

					d4 /= 1.4D;
					d4 /= 2.0D;
					d2 = 0.0D;
				} else {
					if(d4 > 1.0D) {
						d4 = 1.0D;
					}

					d4 /= 6.0D;
				}

				d2 += 0.5D;
				d4 = d4 * (double)i1 / 16.0D;
				double d5 = (double)i1 / 2.0D + d4 * 4.0D;
				++l1;

				for(int k2 = 0; k2 < i1; ++k2) {
					double d6 = 0.0D;
					double d7 = ((double)k2 - d5) * 12.0D / d2;
					if(d7 < 0.0D) {
						d7 *= 4.0D;
					}

					double d8 = this.field_4184_e[k1] / 512.0D;
					double d9 = this.field_4183_f[k1] / 512.0D;
					double d10 = (this.field_4185_d[k1] / 10.0D + 1.0D) / 2.0D;
					if(d10 < 0.0D) {
						d6 = d8;
					} else if(d10 > 1.0D) {
						d6 = d9;
					} else {
						d6 = d8 + (d9 - d8) * d10;
					}

					d6 -= d7;
					double d12;
					if(k2 > i1 - 4) {
						d12 = (double)((float)(k2 - (i1 - 4)) / 3.0F);
						d6 = d6 * (1.0D - d12) + -10.0D * d12;
					}

					if((double)k2 < d3) {
						d12 = (d3 - (double)k2) / 4.0D;
						if(d12 < 0.0D) {
							d12 = 0.0D;
						}

						if(d12 > 1.0D) {
							d12 = 1.0D;
						}

						d6 = d6 * (1.0D - d12) + -10.0D * d12;
					}

					ad[k1] = d6;
					++k1;
				}
			}
		}

		return ad;
	}

	public boolean chunkExists(int i, int j) {
		return true;
	}

	public void populate(IChunkProvider ichunkprovider, int i, int j) {
		BlockSand.fallInstantly = true;
		int k = i * 16;
		int l = j * 16;
		this.rand.setSeed(this.worldObj.getRandomSeed());
		long l1 = this.rand.nextLong() / 2L * 2L + 1L;
		long l2 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed((long)i * l1 + (long)j * l2 ^ this.worldObj.getRandomSeed());
		double d = 0.25D;

		int l3;
		int obj;
		int k10;
		int i15;
		for(l3 = 0; l3 < 8; ++l3) {
			obj = k + this.rand.nextInt(16) + 8;
			k10 = this.rand.nextInt(128);
			i15 = l + this.rand.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 10; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(128);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenClay(32)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 20; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(128);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.dirt.blockID, 32)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 10; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(128);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.gravel.blockID, 32)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 20; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(128);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreCoal.blockID, 16)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 20; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(64);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreIron.blockID, 8)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 2; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(32);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreGold.blockID, 8)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 8; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(16);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreRedstone.blockID, 7)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		for(l3 = 0; l3 < 1; ++l3) {
			obj = k + this.rand.nextInt(16);
			k10 = this.rand.nextInt(16);
			i15 = l + this.rand.nextInt(16);
			(new WorldGenMinable(Block.oreDiamond.blockID, 7)).generate(this.worldObj, this.rand, obj, k10, i15);
		}

		d = 0.5D;
		l3 = (int)((this.mobSpawnerNoise.func_806_a((double)k * d, (double)l * d) / 8.0D + this.rand.nextDouble() * 4.0D + 4.0D) / 3.0D);
		if(l3 < 0) {
			l3 = 0;
		}

		if(this.rand.nextInt(10) == 0) {
			++l3;
		}

		Object object18 = new WorldGenTrees();
		if(this.rand.nextInt(10) == 0) {
			object18 = new WorldGenBigTree();
		}

		int j17;
		for(k10 = 0; k10 < l3; ++k10) {
			i15 = k + this.rand.nextInt(16) + 8;
			j17 = l + this.rand.nextInt(16) + 8;
			((WorldGenerator)object18).func_517_a(1.0D, 1.0D, 1.0D);
			((WorldGenerator)object18).generate(this.worldObj, this.rand, i15, this.worldObj.getHeightValue(i15, j17), j17);
		}

		int k18;
		for(k10 = 0; k10 < 2; ++k10) {
			i15 = k + this.rand.nextInt(16) + 8;
			j17 = this.rand.nextInt(128);
			k18 = l + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantYellow.blockID)).generate(this.worldObj, this.rand, i15, j17, k18);
		}

		if(this.rand.nextInt(2) == 0) {
			k10 = k + this.rand.nextInt(16) + 8;
			i15 = this.rand.nextInt(128);
			j17 = l + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.plantRed.blockID)).generate(this.worldObj, this.rand, k10, i15, j17);
		}

		if(this.rand.nextInt(4) == 0) {
			k10 = k + this.rand.nextInt(16) + 8;
			i15 = this.rand.nextInt(128);
			j17 = l + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, this.rand, k10, i15, j17);
		}

		if(this.rand.nextInt(8) == 0) {
			k10 = k + this.rand.nextInt(16) + 8;
			i15 = this.rand.nextInt(128);
			j17 = l + this.rand.nextInt(16) + 8;
			(new WorldGenFlowers(Block.mushroomRed.blockID)).generate(this.worldObj, this.rand, k10, i15, j17);
		}

		for(k10 = 0; k10 < 10; ++k10) {
			i15 = k + this.rand.nextInt(16) + 8;
			j17 = this.rand.nextInt(128);
			k18 = l + this.rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(this.worldObj, this.rand, i15, j17, k18);
		}

		for(k10 = 0; k10 < 1; ++k10) {
			i15 = k + this.rand.nextInt(16) + 8;
			j17 = this.rand.nextInt(128);
			k18 = l + this.rand.nextInt(16) + 8;
			(new WorldGenCactus()).generate(this.worldObj, this.rand, i15, j17, k18);
		}

		for(k10 = 0; k10 < 50; ++k10) {
			i15 = k + this.rand.nextInt(16) + 8;
			j17 = this.rand.nextInt(this.rand.nextInt(120) + 8);
			k18 = l + this.rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.waterStill.blockID)).generate(this.worldObj, this.rand, i15, j17, k18);
		}

		for(k10 = 0; k10 < 20; ++k10) {
			i15 = k + this.rand.nextInt(16) + 8;
			j17 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(112) + 8) + 8);
			k18 = l + this.rand.nextInt(16) + 8;
			(new WorldGenLiquids(Block.lavaStill.blockID)).generate(this.worldObj, this.rand, i15, j17, k18);
		}

		if(wintermode) {
			for(k10 = k + 8 + 0; k10 < k + 8 + 16; ++k10) {
				for(i15 = l + 8 + 0; i15 < l + 8 + 16; ++i15) {
					j17 = this.worldObj.findTopSolidBlock(k10, i15);
					if(j17 > 0 && j17 < 128 && this.worldObj.getBlockId(k10, j17, i15) == 0 && this.worldObj.getBlockMaterial(k10, j17 - 1, i15).getIsSolid() && this.worldObj.getBlockMaterial(k10, j17 - 1, i15) != Material.ice) {
						this.worldObj.setBlockWithNotify(k10, j17, i15, Block.snow.blockID);
					}
				}
			}
		}

		BlockSand.fallInstantly = false;
	}

	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate) {
		return true;
	}

	public boolean unload100OldestChunks() {
		return false;
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "RandomLevelSource";
	}
}
