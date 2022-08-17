package cykuta.cmachines.fileManagment;

import cykuta.cmachines.Utils.Block;

public class MachineSavePool implements Runnable {
    private final MachineData data = new MachineData();

    @Override
    public void run() {
        Block.saveAll(data);
    }
}
