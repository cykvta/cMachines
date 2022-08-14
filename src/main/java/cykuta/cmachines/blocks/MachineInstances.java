package cykuta.cmachines.blocks;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MachineInstances {
    public static HashMap<Location, Machine> instances = new HashMap<>();
    public static List<Machine> removedMachines = new ArrayList<Machine>();

    public static boolean checkIfMachineExist(Machine machine){
        return instances.containsValue(machine);
    }

    public static boolean checkIfLocationHasMachine(Location loc){
        return instances.containsKey(loc);
    }

    public static Machine getMachine(Location loc){
        return instances.get(loc);
    }
}
