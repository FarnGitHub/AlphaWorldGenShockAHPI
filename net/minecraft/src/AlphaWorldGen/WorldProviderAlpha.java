package net.minecraft.src.AlphaWorldGen;

import net.minecraft.src.IChunkProvider;
import net.minecraft.src.WorldProviderSurface;

public class WorldProviderAlpha extends WorldProviderSurface {

	public WorldProviderAlpha() {
		super();
	}

	public IChunkProvider getChunkProvider() {
		if(ConfigAlphaWorldGen.instance.alphaworldgen()) {
			return new ChunkProviderGenerateAlpha(this.worldObj, this.worldObj.getRandomSeed());
		} else {
			return super.getChunkProvider();
		}

	}

	protected void registerWorldChunkManager() {
		if(ConfigAlphaWorldGen.instance.alphaworldgen()) {
			this.worldChunkMgr = new WorldChunkManagerAlpha();
		} else {
			super.registerWorldChunkManager();
		}
	}
}
