package functionality;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Static class holds data for Parts and Products
 */
public class Inventory {

    /**
     * List of all Parts
     */
    static private ObservableList <Part> allParts = FXCollections.observableArrayList();
    /**
     * List of all Products
     */
    static private ObservableList <Product> allProducts = FXCollections.observableArrayList();

    /**
     * Returns of all parts
     */
    static public ObservableList<Part> getAllParts(){
        return allParts;
    };
    /**
     *  Return all products
     */

    static public ObservableList<Product> getAllProducts(){

        return allProducts;
    };

    /**
     * Adding Part to Inventory
     */

    static public void addPart(Part newPart){

        allParts.add(newPart);
    }

    /**
     * Adding Product to Inventory
     */

    static public void addProduct(Product newProduct){

        allProducts.add(newProduct);
    }

    /**
     * Modify Part
     */

    static public  void updatePart(int index, Part selectedPart){

        allParts.set(index, selectedPart);
    }

    /**
     * Delete Part
     */

    static public boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    };

    /**
     * Search Part using id
     */

    static public Part lookupPart(int PartId){
        Part lookedPart = null;
        for(int i = 0; i < allParts.size(); i++) {
            if(allParts.get(i).getId() == PartId){
                lookedPart = getAllParts().get(i);
                return lookedPart;
            }
        }
            return lookedPart;
    };

    /**
     * Search Part using name
     */

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

    /**
     * Update product
     */

    static public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    };

    /**
     * Delete product
     */
    static public boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }

    /**
     * Search for product using Id
     */
    static public Product lookupProduct(int productId){
        Product lookedProduct = null;
        for(int i = 0; i < allProducts.size(); i++) {
            if(allProducts.get(i).getId() == productId){
                lookedProduct = getAllProducts().get(i);
            }
        }
        return lookedProduct;

    };


    /**
     * Search for product using name
     */

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

