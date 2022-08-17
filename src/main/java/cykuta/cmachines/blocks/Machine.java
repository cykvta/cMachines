package cykuta.cmachines.blocks;

import cykuta.cmachines.Utils.Block;
import cykuta.cmachines.blocks.machines.Crusher;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

import static cykuta.cmachines.blocks.MachineInstances.machineList;

public class Machine {
    protected UUID uuid;
    protected MachineType machineType;
    protected FontImageWrapper inventoryBackground;
    protected final int inventorySize;
    protected Inventory inventory;
    protected final Location location;

    public Machine(MachineType machineType, int inventorRows, Location machineLocation, FontImageWrapper inventoryBackground){
        this.location = machineLocation;
        this.inventorySize = inventorRows * 9;
        this.inventoryBackground = inventoryBackground;
        this.uuid = UUID.randomUUID();
        this.machineType = machineType;
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

    public UUID getUUID() {
        return this.uuid;
    }

    public String getUUIDStringify(){
        return this.uuid.toString();
    }

    // Setters
    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    // Utils
    public static Machine getMachineByLocation(Location blockLocation){
        Machine machine = null;
        String location = Block.blockLocationConverter(blockLocation);

        for(Machine i : machineList){
            if (!location.equals( Block.blockLocationConverter(i.getLocation()) )) break;
            machine = i;
        }
        return machine;
    }

    public static Machine createMachineByType(MachineType type, Location machineLocation){
        Machine machine = null;

        switch (type){
            case CRUSHER:
                machine = new Crusher(machineLocation);
        }

        return machine;
    }
}
