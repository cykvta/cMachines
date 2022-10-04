package cykuta.cmachines.blocks.machines;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineProcess;
import cykuta.cmachines.blocks.MachineType;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Location;

public class Crusher extends Machine {
    private MachineProcess process;

    public Crusher(Location location){
        super(MachineType.CRUSHER, 6, location, new FontImageWrapper("mcguis:blank_menu"));
        this.process = new MachineProcess(20);
        this.initialize();
    }

    private void initialize(){
        if (!(process.getTime() > 0 )) {
            process.cancelTask();
            return;
        };
    }
}

