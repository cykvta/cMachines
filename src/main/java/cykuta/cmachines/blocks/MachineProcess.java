package cykuta.cmachines.blocks;

import cykuta.cmachines.cMachines;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class MachineProcess {
    private long time;
    private BukkitTask task;

    public MachineProcess(long time){
        this.time = time;
        initialize();
    }

    private void initialize(){
        this.task = Bukkit.getScheduler().runTaskTimerAsynchronously(
                cMachines.plugin(), new BukkitRunnable() {
                    @Override
                    public void run() {
                        time++;
                    }
                }, 1L, 1L);
    }

    public void cancelTask(){
        task.cancel();
    }

    public long getTime(){
        return this.time;
    }
}
