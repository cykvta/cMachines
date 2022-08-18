package cykuta.cmachines.events;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineHolder;
import cykuta.cmachines.fileManagment.MachineData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InventoryEvent implements Listener {
    private final MachineData data = new MachineData();

    @EventHandler
    private void onPlayerCloseInventory(InventoryOpenEvent e){
        // Check if is machine via Holder
        if (!(e.getInventory().getHolder() instanceof MachineHolder)) return;
        Inventory inventory = e.getInventory();
        Machine machine = ((MachineHolder) e.getInventory().getHolder()).getMachine();
    }
}
