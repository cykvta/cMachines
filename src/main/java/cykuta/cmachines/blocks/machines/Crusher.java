package cykuta.cmachines.blocks.machines;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineType;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Location;

public class Crusher extends Machine {

    public Crusher(Location location){
        super(MachineType.CRUSHER, 6, location, new FontImageWrapper("mcguis:blank_menu"));
    }
}

