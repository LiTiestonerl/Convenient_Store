/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class Validation {

    
    public static Boolean checkEmptyString(String str){
        return str.trim().isEmpty();
    }
    
    public static Boolean checkRegexString(String str, String strRegex){
        return str.matches(strRegex);
    }
    
    /*public static List<Product> getProduct(List<Product> products) {
        return products.stream()
                .filter(product -> product.getName().startsWith("e") || product.getName().startsWith("E"))
                .collect(Collectors.toList());
    
    
    List<Product> filteredProducts = getProductsStartingWithE(products);
    
    
    System.out.println(" 'e':");
        for (Product product : filteredProducts) {
            System.out.println(product.getName() + " - hsd: " + product.getExpirationDate());
        }
    }*/
    
    public static boolean checkDatePattern(String inputDate) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(inputDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}    
    
   
