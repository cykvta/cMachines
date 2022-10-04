package cykuta.cmachines.blocks;

import cykuta.cmachines.Utils.Block;
import cykuta.cmachines.blocks.machines.Crusher;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

import static cykuta.cmachines.blocks.MachineInstances.placedMachines;

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
        this.inventory = Bukkit.createInventory(new MachineHolder(this), inventorySize,
                ChatColor.WHITE + this.inventoryBackground.applyPixelsOffset(-16));

        MachineInstances.addMachine(this);
    }

    // Utils
    public void delete(){
        MachineInstances.removeMachine(this);
    }

    public void openInventory(Player player) {
        player.openInventory(this.inventory);
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

    public World getWorld() {
        return this.getLocation().getWorld();
    }

    public String getUUIDStringify(){
        return this.uuid.toString();
    }

    // Setters
    public void setInventoryBackground(FontImageWrapper inventoryBackground){
        this.inventoryBackground = inventoryBackground;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    // Statics
    public static Machine getMachineByLocation(Location blockLocation){
        String location = Block.blockLocationConverter(blockLocation);

        for(Machine tempMachine : placedMachines){
            if (!location.equals(Block.blockLocationConverter(tempMachine.getLocation()))) continue;
            return tempMachine;
        }
        return null;
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
