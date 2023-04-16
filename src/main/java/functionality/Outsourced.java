package functionality;

/**
 * Inherited class that derivative of Part class
 */
public class Outsourced extends Part{
    private String companyName;

     /**
      * Constructor
      * */

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * setting Company Name
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    /**
     * getting Company Name
     */
    public String getCompanyName(){
        return this.companyName;
    }
}
