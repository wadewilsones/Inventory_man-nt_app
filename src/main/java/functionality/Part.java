package functionality;

import javafx.beans.property.*;

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

    /*Transform integer to Property to use for filling table rows*/
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



    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    public int getStock(){
        return this.stock;
    }
    public int getMin(){
        return this.min;
    }
    public int getMax(){
        return this.max;
    }


}
