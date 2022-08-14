package cykuta.cmachines.blocks;

import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

public class Machine {
    protected MachineType machineType;
    protected FontImageWrapper inventoryBackground;
    protected final int inventorySize;
    protected Inventory inventory;
    protected final Location location;

    public Machine(int inventorRows, Location machineLocation, FontImageWrapper inventoryBackground){
        this.location = machineLocation;
        this.inventorySize = inventorRows * 9;
        this.inventoryBackground = inventoryBackground;
        createInventory();
    }

    private void createInventory(){
        this.inventory = Bukkit.createInventory(new MachineHolder(this), inventorySize,
                ChatColor.WHITE + this.inventoryBackground.applyPixelsOffset(-16));
    }

    // Getters
    public Inventory getInventory(){
        return this.inventory;
    }

    public MachineType getType(){
        return this.machineType;
    }

    public Location getLocation(){
        return this.location;
    }

    public MachineType getMachineType() {
        return this.machineType;
    }

    public String getBlockNamespacedID() {
        return this.machineType.namespacedID;
    }
}
