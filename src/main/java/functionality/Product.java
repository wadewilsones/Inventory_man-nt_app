package functionality;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private  String name;
    private  double price;
    private  int stock;
    private  int min;
    private int max;

    /**
     * Constructors
     */

    public Product(){
        this.id = 0;
        this.name = "Not set";
        this.price = 0;
        this.stock = 0;
        this.min = 0;
        this.max = 0;
    }; // for handle associated parts
    public Product(int id, String name, double price, int stock, int min,int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /**Setters*/

    /**
     * Setting Id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Setting product Name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setting product price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     * Setting product stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Setting product minimum Q
     */
    public void setMin(int min){
        this.min = min;
    }

    /**
     * Setting product maximum Q
     */
    public void setMax(int max){
        this.max = max;
    }

    /**Getters*/

    /**
     * Getting Id
     */
    public int getId(){
        return this.id;
    }

    /**
     * Getting product Name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getting product price
     */
    public double getPrice(){
        return this.price;
    }

    /**
     * Getting product stock
     */
    public int getStock(){
        return this.stock;
    }

    /**
     * Getting product minimum Q
     */
    public int getMin(){
        return this.min;
    }

    /**
     * Getting product maximum Q
     */
    public int getMax(){
        return this.max;
    }


    /**
     * Adding a part for association
     */
    public void addAssociatedPart(Part part){

        associatedParts.add(part);
    }

    /**
     * Delete associated part
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
           return associatedParts.remove(selectedAssociatedPart);
    }

    /**
     * Display products associated parts
     * */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

    /**
     * Transform integer to Property to use for filling table rows
     * */
    public IntegerProperty getIntId(){
        IntegerProperty id = new SimpleIntegerProperty(this.id);
        return id;
    }

    /**
     * Transform integers, strings and double to simple Property to use for filling table rows
     * */

    public StringProperty getStringName(){
        StringProperty name = new SimpleStringProperty(this.name);
        return name;
    }
    //Return price
    public DoubleProperty getDoublePropertyPrice(){
        DoubleProperty price = new SimpleDoubleProperty(this.price);
        return price;
    }


    public IntegerProperty getIntStock(){
        IntegerProperty stock = new SimpleIntegerProperty(this.stock);
        return stock;
    }

}
