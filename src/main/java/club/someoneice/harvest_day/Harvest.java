package club.someoneice.harvest_day;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;



@Mod(modid = Harvest.MODID, version = Harvest.VERSION)
public class Harvest {
    public static final String MODID = "harvest_day";
    public static final String VERSION = "Day-1";
    public static Configuration config;
    public static boolean isInstallMMM;

    @Mod.EventHandler
    public void ModInitEvent(FMLPreInitializationEvent event) {
        try {
            Class.forName("project.studio.manametalmod.ManaMetalMod");
            Harvest.isInstallMMM = true;
        } catch (ClassNotFoundException e) {
            Harvest.isInstallMMM = false;
        }

        config = new Configuration(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void ModInit(FMLInitializationEvent event) {
        Item Tool = new Tool();
        GameRegistry.addShapedRecipe(new ItemStack(Tool), "III", "  S", "  S", 'I', Items.iron_ingot, 'S', Items.stick);
        GameRegistry.addShapedRecipe(new ItemStack(Tool), "III", "S  ", "S  ", 'I', Items.iron_ingot, 'S', Items.stick);
    }
}
