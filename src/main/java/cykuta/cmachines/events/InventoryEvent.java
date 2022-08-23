package cykuta.cmachines.events;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryEvent implements Listener {

    @EventHandler
    public void inventoryInteract(InventoryClickEvent e){
        if (e.getClickedInventory() == null) return;
        if (!(e.getInventory().getHolder() instanceof MachineHolder)) return;
        if (e.isShiftClick()) {
            e.setCancelled(true);
            return;
        }
        if (!(e.getClickedInventory().getHolder() instanceof MachineHolder)) return;

        switch (e.getSlot()){
            case 20:
                break;
            case 24:
                break;
            default:
                e.setCancelled(true);
                break;
        }

        e.getWhoClicked().sendMessage(e.getSlot() + "");
    }
}
