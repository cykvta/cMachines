package cykuta.cmachines.blocks;

public enum MachineType {
    CRUSHER("bloques:azulejo_rojo");

    public final String namespacedID;
    MachineType(String namespacedID) {
        this.namespacedID = namespacedID;
    }
}
