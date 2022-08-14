package cykuta.cmachines.events;

import cykuta.cmachines.blocks.MachineHolder;
import cykuta.cmachines.fileManagment.MachineData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryEvent implements Listener {
    private final MachineData data = new MachineData();

    @EventHandler
    private void onPlayerCloseInventory(InventoryCloseEvent e){
        // Check if is machine via Holder
        if (!(e.getInventory().getHolder() instanceof MachineHolder)) return;
    }
}
