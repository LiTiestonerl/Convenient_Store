/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Product;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class ProductMap {

    private HashMap<String, Product> productMap;

    public ProductMap() {
        productMap = new HashMap<>();
    }

    public HashMap<String, Product> getProductMap() {
        return productMap;
    }

    public Boolean addProduct(Product product) {
        if (product != null) {
            String prCode = product.getPrCode();
            if (!productMap.containsKey(prCode)) {
                productMap.put(prCode, product);
                return true;
            }
        }
        return false;
    }

    public Boolean removeProduct(Product product) {
        if (product != null) {
            String prCode = product.getPrCode();
            if (productMap.containsKey(prCode)) {
                productMap.remove(prCode);
                return true;
            }
        }
        return false;
    }

    public Product findProductByCode(String prCode) {
        return productMap.get(prCode);
    }

    public boolean isEmpty() {
        return productMap.isEmpty();
    }

    public int size() {
        return productMap.size();
    }
    
    public Collection<Product> getValues(){
        return productMap.values();
    }
    
    public void clear(){
        productMap.clear();
    }
    
    public void setProductMapAgain(Collection<Product> products){
        productMap.clear();
        for (Product product : products) {
            addProduct(product);         
        }
    }
}
