package cykuta.cmachines;

import cykuta.cmachines.events.InteractEvent;
import cykuta.cmachines.events.InventoryEvent;
import cykuta.cmachines.fileManagment.MachineData;
import cykuta.cmachines.fileManagment.MachineSavePool;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class cMachines extends JavaPlugin {
    public FileConfiguration dataFile;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("CottageMachines loaded successfully");
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new MachineSavePool(), 0, 20 * 5);

        registerEvents();
        loadConfig();
    }

    private void registerEvents(){
        getServer().getPluginManager().registerEvents(new InteractEvent(), this);
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
    }

    private void loadConfig(){
        this.dataFile = new MachineData().getFile();
    }

    public static cMachines plugin(){
        return getPlugin(cMachines.class);
    }
}
