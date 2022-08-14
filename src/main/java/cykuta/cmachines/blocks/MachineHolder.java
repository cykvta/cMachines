package cykuta.cmachines.blocks;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MachineHolder implements InventoryHolder {
    private final Machine machine;

    public MachineHolder(Machine machine){
        this.machine = machine;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return null;
    }

    public Machine getMachine(){
        return this.machine;
    }
}
