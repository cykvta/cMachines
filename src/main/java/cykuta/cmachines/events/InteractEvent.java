package cykuta.cmachines.events;

import cykuta.cmachines.blocks.Machine;
import cykuta.cmachines.blocks.MachineInstances;
import cykuta.cmachines.fileManagment.MachineData;
import cykuta.cmachines.blocks.machines.Crusher;
import cykuta.cmachines.blocks.MachineType;
import dev.lone.itemsadder.api.CustomBlock;
import dev.lone.itemsadder.api.Events.CustomBlockInteractEvent;
import dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {
    private final MachineData data;
    private final String pathName = "machines.";

    public InteractEvent(){
        this.data = new MachineData();
    }

    @EventHandler
    public void onPlayerInteract(CustomBlockInteractEvent e){
        // Check if is machine
        CustomBlock machineBlock = CustomBlock.byAlreadyPlaced(e.getBlockClicked());
        Machine machine = getMachine(machineBlock, e.getBlockClicked().getLocation());
        if (machine == null) return;

        // Get name and path in data file
        String name = MachineData.getName(machine.getLocation());

        // Return if file not contains path data
        if (!MachineInstances.checkIfMachineExist(machine)) return;

        // Open machine inventory
        e.getPlayer().openInventory(machine.getInventory());
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDestroyMachine(BlockBreakEvent e){
        // CustomBlockEvent Event Fix
        // Check if is customBlock
        CustomBlock block = CustomBlock.byAlreadyPlaced(e.getBlock());
        if (block == null) return;

        // Check if is machine
        CustomBlock machineBlock = CustomBlock.getInstance(block.getNamespacedID());
        Machine machine = getMachine(machineBlock, e.getBlock().getLocation());
        if (machine == null) return;

        // Get name and path in data file
        String name = MachineData.getName(machine.getLocation());
        String blockPath = this.pathName + name;

        MachineInstances.instances.remove(machine.getLocation());
        MachineInstances.removedMachines.add(machine);
    }

    @EventHandler
    public void onPlayerPlaceMachine(CustomBlockPlaceEvent e){
        // Check if is machine
        CustomBlock machineBlock = CustomBlock.getInstance(e.getNamespacedID());
        Machine machine = getMachine(machineBlock, e.getBlock().getLocation());
        if (machine == null) return;

        // Get name and path in data file
        String name = MachineData.getName(machine.getLocation());
        String blockPath = this.pathName + name;
    }

    // This function get machine by CustomBlock via ItemsAdder
    private Machine getMachine(CustomBlock customBlock, Location blockLocation){
        // This loop check machine type and if is CustomBlock
        if (customBlock == null) return null;
        MachineType machineType = null;
        for (MachineType machine : MachineType.values())
        {
            if (!customBlock.getNamespacedID().equals(machine.namespacedID)) break;
            machineType = machine;
        }
        if (machineType == null) return null;

        // Load machine if already loaded
        if (MachineInstances.checkIfLocationHasMachine(blockLocation)) return MachineInstances.getMachine(blockLocation);

        //  Create machine from machine type
        Machine machine = null;
        switch (machineType)
        {
            case CRUSHER: machine = new Crusher(blockLocation);

        }
        if (machine == null) return null;

        // Load machine if exist in config
        String blockPath = this.pathName + MachineData.getName(blockLocation);
        if (data.getFile().contains(blockPath))
        {
            ItemStack[] content = data.getInventoryContent(blockPath);
            machine.getInventory().setContents(content);
        }

        MachineInstances.instances.put(blockLocation, machine);
        return machine;
    }
}
