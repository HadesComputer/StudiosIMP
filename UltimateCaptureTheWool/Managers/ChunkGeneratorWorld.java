package gamemanager;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class ChunkGeneratorWorld extends ChunkGenerator{
      
	int hight = 50;
	
	@Override
	public ChunkData generateChunkData(World world, Random rand, int chunkx, int chunkz, BiomeGrid grid){
		
		SimplexOctaveGenerator sm = new SimplexOctaveGenerator(new Random(world.getSeed()), 8);
		
		ChunkData chunk = createChunkData(world);
		
		sm.setScale(0.005D);
		
		for(int x = 0; x<16;x++){
			for(int z = 0;z<16;z++){
				hight  = (int) (sm.noise(chunkx*16+x, chunkz*16+z, 0.5D, 0.5D) * 15D+50D);
				
				chunk.setBlock(x, hight, z, Material.AIR);
				chunk.setBlock(x, hight-1, z, Material.AIR);
				
				for(int i = hight-2;i>0;i--){
					chunk.setBlock(x, i, z, Material.AIR);
					chunk.setBlock(x, 0, z, Material.AIR);
				}
			}
		}
		return chunk;
	}
	
     public ChunkGenerator getChunkGenerator(String world){
    	 return new ChunkGeneratorWorld();
     }
}
