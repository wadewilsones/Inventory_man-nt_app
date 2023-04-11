package functionality;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**RUNTIME ERROR: Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
 * FIXED with
 */
public class Inventory {

    //List of all Parts
    static private ObservableList <Part> allParts = FXCollections.observableArrayList();
    //List of all products
    static private ObservableList <Product> allProducts = FXCollections.observableArrayList();

    /**Methods*/
    //Return all  Parts
    static public ObservableList<Part> getAllParts(){
        return allParts;
    };
    //Returns of all products
    static public ObservableList<Product> getAllProducts(){
        return allProducts;
    };
    //Adding Part to Inventory
    static public void addPart(Part newPart){
        allParts.add(newPart);
    }
    //Adding Product to Inventory
    static public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    //Modify Part
    static public  void updatePart(int index, Part selectedPart){
        System.out.println("New name:  "+  selectedPart.getName());
        allParts.set(index-1, selectedPart);
    }
    //Delete Part
    static public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    };
    //Search Part using id
    static public Part lookupPart(int PartId){

        return allParts.get(PartId);
    };

    //Search Part using name

    static public ObservableList<Part> lookupPart(String partName){

        /*Find part using name*/
        String namePattern = partName;
        ObservableList<Part> tempList = FXCollections.observableArrayList();
        for(int i = 0; i < allParts.size(); i++){
            if(allParts.get(i).getName().matches(namePattern)){
                tempList.add(allParts.get(i));
            }
        }
        return tempList;
    };
};




    /*


+ addProduct(newProduct:Product):void
+ lookupProduct(productId:int):Product

+ lookupProduct(productName:String):ObservableList<Product>
+ updateProduct(index:int, newProduct:Product):void
+ deleteProduct(selectedProduct:Product):boolean





*/

