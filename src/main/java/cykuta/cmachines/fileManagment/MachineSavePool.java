package cykuta.cmachines.fileManagment;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineInstances;

public class MachineSavePool implements Runnable {
    private final MachineData data = new MachineData();

    @Override
    public void run() {
        for(Machine machine : MachineInstances.instances.values())
        {
            String blockPath = "machines." + MachineData.getName(machine.getLocation());
            data.setInventoryContent(blockPath, machine.getInventory());
        }

        for(Machine machine : MachineInstances.removedMachines)
        {
            String blockPath = "machines." + MachineData.getName(machine.getLocation());
            data.removeMachine(blockPath, machine);
        }

        data.saveData();
    }
}
