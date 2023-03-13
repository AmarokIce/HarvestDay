package club.someoneice.harvest_day;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockCrops;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

public class Tool extends ItemSword {
    public Tool() {
        super(ToolMaterial.IRON);
        this.setMaxDamage(240);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabTools);
        this.setUnlocalizedName("harvest_sickle");
        this.setTextureName(Harvest.MODID + ":harvest_sickle");

        GameRegistry.registerItem(this, "harvest_sickle", Harvest.MODID);
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int s, float posx, float posy, float posz) {
        if (!isCropBlock(world, x, y, z)) return false;
        int range = HarvestConfig.HARVEST_RANGE;

        List<CropData> cropsList = new ArrayList<CropData>();
        for (int i = -range; i < range; ++i) for (int k = -range; k < range; ++k)
            if (world.getBlock(x + i, y, z + k) instanceof BlockCrops)
                cropsList.add(new CropData((BlockCrops) world.getBlock(x + i, y, z + k), world, x + i, y, z + k));

        return isHarvest(cropsList, player, item);
    }

    private boolean isHarvest(List<CropData> cropsList, EntityPlayer player, ItemStack item) {
        for (CropData crop : cropsList) {
            int meta = crop.crop.getPlantMetadata(crop.worldObj, crop.x, crop.y ,crop.z);
            if (meta >= 7) {
                List<ItemStack> outputList = crop.crop.getDrops(crop.worldObj, crop.x, crop.y, crop.z, meta, 0);
                for (ItemStack output : outputList) {
                    if (output == null) continue;
                    if (output.getItem() instanceof IPlantable) {
                        if (output.stackSize > 1) output.stackSize--;
                        else outputList.remove(output);

                        break;
                    }
                }

                if (!crop.worldObj.isRemote) {
                    crop.worldObj.setBlockMetadataWithNotify(crop.x, crop.y, crop.z, 0, 3);
                    for (ItemStack itemStack : outputList) {
                        EntityItem entityItem = new EntityItem(crop.worldObj, crop.x, crop.y + 0.5, crop.z, itemStack);
                        entityItem.delayBeforeCanPickup = 10;
                        crop.worldObj.spawnEntityInWorld(entityItem);
                    }

                    outputList.clear();
                }

                if (Harvest.isInstallMMM) MMMHelper.init(crop, player);
                this.setDamage(item, this.getDamage(item) - 1);
            }
        }

        return true;
    }

    private boolean isCropBlock(World world, int x, int y, int z) {
        return world.getBlock(x, y, z) instanceof BlockCrops;
    }
}
