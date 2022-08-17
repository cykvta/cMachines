package cykuta.cmachines.Utils;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineInstances;
import cykuta.cmachines.fileManagment.MachineData;
import org.bukkit.Location;

public class Block {
    public static String blockLocationConverter(Location location) {
        String world = location.getWorld().getName();
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        return world + x + y + z;
    }

    public static void saveAll(MachineData data){
        for(Machine machine : MachineInstances.placedMachines)
        {
            data.saveMachine(machine);
        }

        for(Machine machine : MachineInstances.removedMachines)
        {
            data.removeMachine(machine);
        }
        data.saveData();
    }
}
