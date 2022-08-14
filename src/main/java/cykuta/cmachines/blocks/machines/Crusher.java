package cykuta.cmachines.blocks.machines;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineType;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Location;

public class Crusher extends Machine {
    protected MachineType machineType = MachineType.CRUSHER;

    public Crusher(Location location){
        super(6, location, new FontImageWrapper("mcguis:blank_menu"));
    }
}

