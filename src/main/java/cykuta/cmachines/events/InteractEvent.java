package cykuta.cmachines.events;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineInstances;
import cykuta.cmachines.blocks.MachineType;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent;
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static cykuta.cmachines.blocks.MachineInstances.placedMachines;
import static cykuta.cmachines.blocks.MachineInstances.removedMachines;

public class InteractEvent implements Listener {

    @EventHandler
    public void onPlayerInteract(CustomBlockInteractEvent e){
        Machine machine = Machine.getMachineByLocation(e.getBlockClicked().getLocation().toBlockLocation());
        if (machine == null) return;

        // Return if machine is in machine list
        if (!MachineInstances.checkIfMachineExist(machine)) return;

        // Open machine inventory
        machine.openInventory(e.getPlayer());
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlaceMachine(CustomBlockPlaceEvent e){
        MachineType type = null;

        for(MachineType i: MachineType.values()) {
            if (!Objects.equals(i.namespacedID, e.getNamespacedID())) break;
            type = i;
        }
        if (type == null) return;

        Machine.createMachineByType(type, e.getBlock().getLocation().toBlockLocation());
    }

    @EventHandler
    public void onPlayerDestroyMachine(BlockBreakEvent e){
        // CustomBlockEvent Event Fix
        // Check if is customBlock
        CustomBlock block = CustomBlock.byAlreadyPlaced(e.getBlock());
        if (block == null) return;

        // Check if is machine
        Machine machine = Machine.getMachineByLocation(e.getBlock().getLocation());
        if (machine == null) return;

        for (ItemStack item : machine.getInventory().getContents()){
            if (item == null || item.getType() == Material.AIR) continue;
            machine.getWorld().dropItem(machine.getLocation(), item);
        }
        
        machine.delete();
    }
}
