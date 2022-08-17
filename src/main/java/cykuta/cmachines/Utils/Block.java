package cykuta.cmachines.Utils;

import org.bukkit.Location;

public class Block {
    public static String blockLocationConverter(Location location) {
        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return world + x + y + z;
    }
}
