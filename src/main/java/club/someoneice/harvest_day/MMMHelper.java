package club.someoneice.harvest_day;

import net.minecraft.entity.player.EntityPlayer;
import project.studio.manametalmod.MMM;
import project.studio.manametalmod.entity.nbt.ManaMetalModRoot;
import project.studio.manametalmod.items.ItemCoinSpecial;
import project.studio.manametalmod.produce.Produce;
import project.studio.manametalmod.produce.farming.BlockCropSpecial;

public class MMMHelper {
    public MMMHelper() { }

    public static void init(CropBuffer crops, EntityPlayer player) {
        if (crops.crop instanceof BlockCropSpecial) {
            BlockCropSpecial MMMcrop = (BlockCropSpecial) crops.crop;
            ManaMetalModRoot root = MMM.getEntityNBT(player);
            if (root != null) {
                root.produce.addEXP(16 + MMMcrop.LV * 16, Produce.Farmer);
            }
            MMM.spawnItemInWorld(crops.worldObj, ItemCoinSpecial.getTheCoinDropItems(ItemCoinSpecial.CoinTypes.Farm, 2 + MMMcrop.LV), crops.x, crops.y + 0.5D, crops.z);

        } else {
            ManaMetalModRoot root = MMM.getEntityNBT(player);
            if (root != null) {
                root.produce.addEXP(16, Produce.Farmer);
            }
            MMM.spawnItemInWorld(crops.worldObj, ItemCoinSpecial.getTheCoinDropItems(ItemCoinSpecial.CoinTypes.Farm, 2), crops.x, crops.y + 0.5D, crops.z);
        }

    }
}
