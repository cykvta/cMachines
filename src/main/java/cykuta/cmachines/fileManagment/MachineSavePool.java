package cykuta.cmachines.fileManagment;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineInstances;

public class MachineSavePool implements Runnable {
    private final MachineData data = new MachineData();

    @Override
    public void run() {
        for(Machine machine : MachineInstances.machineList)
        {
            data.removeMachine(machine);
            data.saveMachine(machine);
        }
        data.saveData();
    }
}
