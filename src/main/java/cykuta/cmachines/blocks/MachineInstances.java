package cykuta.cmachines.blocks;

import cykuta.cmachines.fileManagment.MachineData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MachineInstances {
    public static List<Machine> placedMachines = new ArrayList<>();
    public static List<Machine> removedMachines = new ArrayList<>();

    public static boolean checkIfMachineExist(Machine machine){
        return placedMachines.contains(machine);
    }

    public static void initializeMachineList(){
        MachineData data = new MachineData();
        FileConfiguration file = data.getFile();

        if (file.getConfigurationSection("machines") == null) return;

        for(String uuid : file.getConfigurationSection("machines").getKeys(false)){
            MachineInstances.placedMachines.add(data.getMachine(uuid));
        }
    }
}
