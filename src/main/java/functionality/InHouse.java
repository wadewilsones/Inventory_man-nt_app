package functionality;


public class InHouse extends Part {

    private int machineId;

    /**Constructor */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max); // refer to parent Part class
        this.machineId = machineId;
    }

    /**Methods*/
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    public int getMachineId(){
        return this.machineId;
    }
}
