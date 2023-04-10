package functionality;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**RUNTIME ERROR: Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
 * FIXED with
 */
public class Inventory {
    static private ObservableList <Part> allParts = FXCollections.observableArrayList();
    static private ObservableList <Product> allProducts = FXCollections.observableArrayList();

    /**Methods*/

    static public ObservableList<Product> getAllProducts(){
        return allProducts;
    };
    static public void addPart(Part newPart){
        allParts.add(newPart);
    }
    static public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    static public ObservableList<Part> getAllParts(){
        return allParts;
    };
    static public  void updatePart(int index, Part selectedPart){
        System.out.println("New name:  "+  selectedPart.getName());
        allParts.set(index-1, selectedPart);
    }
    static public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    };
};


    /*


+ addProduct(newProduct:Product):void
+ lookupPart(partId:int):Part
+ lookupProduct(productId:int):Product
+ lookupPart(partName:String):ObservableList<Part>
+ lookupProduct(productName:String):ObservableList<Product>
+ updatePart(index:int, selectedPart:Part):void
+ updateProduct(index:int, newProduct:Product):void

+ deleteProduct(selectedProduct:Product):boolean





*/

