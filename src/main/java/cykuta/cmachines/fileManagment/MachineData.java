package cykuta.cmachines.fileManagment;

import cykuta.cmachines.cMachines;
import cykuta.cmachines.blocks.Machine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
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

    // Save data info into file
    public void saveData(){
        try{
            this.data.save(this.customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Public Function to get file
    public FileConfiguration getFile(){
        return this.data;
    }

    /*
        Public Methods
    */

    // GETTERS
    public ItemStack[] getInventoryContent(String path){
        // Find better way to make this function
        ItemStack[] itemList;
        try{
            itemList = (ItemStack[]) this.data.get(path + ".inventory");
        } catch (ClassCastException e){
            itemList = ((List<ItemStack>) this.data.get(path + ".inventory")).toArray(new ItemStack[0]);
        }
        return itemList;
    }

    public Player getOwnerID(String path){
        String UID = this.data.getString(path + ".ownerID");
        return Bukkit.getPlayer(UUID.fromString(UID));
    }

    public Location getLocation(String path){
        return (Location) this.data.get(path + ".location");
    }

    // SETERS
    public void setInventoryContent(String path, Inventory inv) {
        this.data.set(path + ".inventory", inv.getContents());
    }

    public void setOwnerID(String path, Player player){
        this.data.set(path + ".ownerID", player.getUniqueId().toString());
    }

    public void setLocation(String path, Location location){
        this.data.set(path + ".location", location);
    }

    public void removeMachine(String path, Machine machine) {
        data.set(path, null);
    }

    /*
        Statics
    */

    // This function transform location into data file path
    public static String getName(Location loc){
        String x = String.valueOf(loc.getBlockX());
        String y = String.valueOf(loc.getBlockY());
        String z = String.valueOf(loc.getBlockZ());
        String world = loc.getWorld().getName();

        return x + "-" + y + "-" + z + "-" + world;
    }
}
