package AlphaWorldGen.sapi;

import AlphaWorldGen.ConfigAlphaWorldGen;
import AlphaWorldGen.worldgen.ChunkProviderGenerateAlpha;
import net.minecraft.src.*;

public class WorldProviderAlpha extends WorldProviderSurface {

	public WorldProviderAlpha() {
		super();
	}

	public IChunkProvider getChunkProvider() {
		if(ConfigAlphaWorldGen.alphaworldgen()) {
			return new ChunkProviderGenerateAlpha(this.worldObj, this.worldObj.getRandomSeed());
		} else {
			return super.getChunkProvider();
		}

	}

	protected void registerWorldChunkManager() {
		if(ConfigAlphaWorldGen.alphaworldgen()) {
			this.worldChunkMgr = new WorldChunkManagerAlpha();
		} else {
			super.registerWorldChunkManager();
		}
	}
}
