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

    static public void addPart(Part newPart){
        allParts.add(newPart);

    }
    static public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    static public ObservableList<Part> getAllParts(){
        return allParts;
    };

    /*
    static public Part lookupPart(int partId)
    static public  Product lookupProduct(int productId)
    static public ObservableList<Part> lookupPart(String partName){};
    static public  ObservableList<Product>lookupProduct(String productName);
    static public  void updatePart(int index, Part selectedPart);
    static public void updateProduct(int index, Product newProduct);
    static public boolean deletePart(Part selectedPart);
    static public  boolean deleteProduct(Product selectedProduct){};

    static public ObservableList<Product> getAllProducts(){};

*/
}
