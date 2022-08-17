package cykuta.cmachines;

import cykuta.cmachines.blocks.MachineInstances;
import cykuta.cmachines.events.InteractEvent;
import cykuta.cmachines.events.InventoryEvent;
import cykuta.cmachines.fileManagment.MachineSavePool;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class cMachines extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("CottageMachines loaded successfully");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new MachineSavePool(), 0, 20 * 5);

        registerEvents();
        MachineInstances.initializeMachineList();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
    }

    public static cMachines plugin(){
        return getPlugin(cMachines.class);
    }
}
