package club.someoneice.harvest_day;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class HarvestConfig {
    public static int HARVEST_RANGE = 2;
    public HarvestConfig() {
        Configuration config = Harvest.config;
        config.load();
        Property harvest_range = config.get("Int", "The harvest sickle 's harvest range.", HARVEST_RANGE);
        harvest_range.comment = "The range is radius.";
        HARVEST_RANGE = harvest_range.getInt(HARVEST_RANGE);

        config.save();
    }

}
