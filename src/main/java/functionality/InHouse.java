package functionality;

/**
 * Inherited class that derivative of Part class
 */
public class InHouse extends Part {

    private int machineId;

    /**
     * The constructor is setting up initial Values for
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max); // refer to parent Part class
        this.machineId = machineId;
    }

    /**
     *Setter of machineId
     */

    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     * Getter of MachineID
     */

    public int getMachineId(){
        return this.machineId;
    }
}
