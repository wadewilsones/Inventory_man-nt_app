package functionality;

import javafx.beans.property.*;

abstract public class Part {


    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Constructor for Part
     * */

     public Part( int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Setting part Id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Setting part Name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setting part price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Setting part Stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Setting part Minimum Part Q
     */
    public void setMin(int min){
        this.min = min;
    }

    /**
     * Setting part Maximum Part Q
     */
    public void setMax(int max){
        this.max = max;
    }

    //GETTERS

    /**
     * Getting part id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Getting part Name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getting part price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Getting part Stock
     */
    public int getStock(){
        return this.stock;
    }

    /**
     * Getting part Minimum Part Q
     */
    public int getMin(){
        return this.min;
    }


    /**
     * Getting part Maximum Part Q
     */
    public int getMax(){
        return this.max;
    }




    /**
    * Transform integers, strings and double to simple Property to use for filling table rows
     * */
    public IntegerProperty getIntId(){
        IntegerProperty id = new SimpleIntegerProperty(this.id);
        return id;
    }

    public StringProperty getStringName(){
        StringProperty name = new SimpleStringProperty(this.name);
        return name;
    }

    public DoubleProperty getDoublePropertyPrice(){
        DoubleProperty price = new SimpleDoubleProperty(this.price);
        return price;
    }


    public IntegerProperty getIntStock(){
        IntegerProperty stock = new SimpleIntegerProperty(this.stock);
        return stock;
    }


}
