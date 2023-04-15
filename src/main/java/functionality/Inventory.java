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
            if(allParts.get(i).getName().contains(namePattern)){
                tempList.add(allParts.get(i));
            }
        }
        return tempList;
    };

//Update product
static public void updateProduct(int index, Product newProduct){
    allProducts.set(index, newProduct);
        };


/*Delete product*/

static public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
        }

    static public Product lookupProduct(int productId){

        return allProducts.get(productId);
    };

    static public ObservableList<Product> lookupProduct(String productName){
        /*Find part using name*/
        String namePattern = productName;
        ObservableList<Product> tempList = FXCollections.observableArrayList();
        for(int i = 0; i < allProducts.size(); i++){
            if(allProducts.get(i).getName().contains(namePattern)){
                tempList.add(allProducts.get(i));
            }
        }
        return tempList;
    }
};

    /*




+







*/

