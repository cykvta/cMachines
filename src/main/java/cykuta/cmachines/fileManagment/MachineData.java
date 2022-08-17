package cykuta.cmachines.fileManagment;

import cykuta.cmachines.blocks.MachineInstances;
import cykuta.cmachines.blocks.MachineType;
import cykuta.cmachines.cMachines;
import cykuta.cmachines.blocks.Machine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class MachineData {
    private FileConfiguration data;
    private final cMachines plugin;
    private final File customConfigFile;

    public MachineData() {
        // Create File
        this.plugin = cMachines.plugin();
        this.customConfigFile = new File(plugin.getDataFolder(), "data.yml");
        createConfig();
    }

    private void createConfig() {
        // Check if file exist
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            plugin.saveResource("data.yml", false);
        }

        this.data = YamlConfiguration.loadConfiguration(customConfigFile);
    }

    public void saveData(){
        try{
            this.data.save(this.customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // GETERS
    public FileConfiguration getFile(){
        return this.data;
    }

    // SETERS
    public void removeMachine(Machine machine) {
        String path = "machines." + machine.getUUIDStringify();
        data.set(path, null);
    }

    public void saveMachine(Machine machine){
        // Saving data in to data.yml
        String path = "machines." + machine.getUUIDStringify();
        data.set(path + ".type", machine.getType().toString());
        data.set(path + ".location.world", machine.getLocation().getWorld().getName());
        data.set(path + ".location.x", machine.getLocation().getX());
        data.set(path + ".location.y", machine.getLocation().getY());
        data.set(path + ".location.z", machine.getLocation().getZ());
        data.set(path + ".inventory", machine.getInventory().getContents());
    }

    public Machine getMachine(String uuid){
        // Get data from data.yml
        String path = "machines." + uuid;
        ItemStack[] content = ((List<ItemStack>) data.get(path + ".inventory")).toArray(new ItemStack[0]);
        World world = Bukkit.getWorld(data.getString(path + ".location.world"));
        int x = data.getInt(path + ".location.x");
        int y = data.getInt(path + ".location.y");
        int z = data.getInt(path + ".location.z");
        MachineType type = MachineType.valueOf(data.getString(path + ".type"));
        Location machineLoc = new Location(world, x, y, z);

        // Create machine
        Machine machine = Machine.createMachineByType(type, machineLoc);
        machine.getInventory().setContents(content);
        machine.setUUID(UUID.fromString(uuid));
        return machine;
    }
}
