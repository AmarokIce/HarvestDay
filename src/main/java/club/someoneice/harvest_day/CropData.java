package club.someoneice.harvest_day;

import net.minecraft.block.BlockCrops;
import net.minecraft.world.World;

public class CropData {
    BlockCrops crop;
    World worldObj;
    int x;
    int y;
    int z;

    public CropData(BlockCrops crop, World world, int x, int y, int z) {
        this.crop = crop;
        this.worldObj = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
