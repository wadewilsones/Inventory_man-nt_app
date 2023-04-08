package functionality;

abstract public class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**Constructor */

     public Part( int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**Methods*/

    /**Setters*/
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setStock(int stock){
        this.stock = stock;
    }

    public void setMin(int min){
        this.min = min;
    }
    public void setMax(int max){
        this.max = max;
    }

    /**Getters*/

    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(double price){
        return this.price;
    }
    public int getStock(int stock){
        return this.stock;
    }
    public int getMin(int min){
        return this.min;
    }
    public int getMax(int max){
        return this.max;
    }


}
