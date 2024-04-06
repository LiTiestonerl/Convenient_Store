/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

import controller.WareHouseMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import controller.ProductMap;

/**
 *
 * @author Admin
 */
public class Input {

    private Scanner sc;

    public Input() {
        sc = new Scanner(System.in);
    }
    
    public String inputPrCodeWh(){
        String prCode;
        do {         
            try {
                System.out.println("input prCode:");
                prCode = sc.nextLine();
                if(Validation.checkEmptyString(prCode)){
                    throw new Exception("Product code must not be empty");
                }
                return prCode;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        } while (true);
    }

    public String inputPrCode(ProductMap prMap, String type) {
        String prCode;
        do {
            try {
                System.out.println("Input your prCode: ");
                prCode = sc.nextLine();
                if (Validation.checkEmptyString(prCode)) {
                    throw new Exception("Product Code must not be empty.");
                }
                if (type.equals("add")) {
                    if (prMap.findProductByCode(prCode) != null) {
                        throw new Exception("Your code is duplicated, please input again.");
                    }
                } else if (type.equals("find")) {
                    if (prMap.findProductByCode(prCode) == null) {
                        throw new Exception("Your code is not exist, please find again");
                    }
                }
                return prCode;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    
    /*public String inputOrderDetailID(ArrayList<WareHouseDetailImport> wareHouseDetailImport) {
        String exCode;
        do {
            try {
                System.out.println("Enter ID of the exCode:");
                exCode = sc.nextLine();
                if (Validation.checkEmptyString(exCode)) {
                    throw new Exception("ID can't be left blank!");
                }
                for (int i = 0; i < wareHouseDetailImport.size(); i++) {
                    if (wareHouseDetailImport.get(i).getExCodeDetailId().equalsIgnoreCase(exCode)) {
                    } else {
                        throw new Exception("ID can't be duplicated");
                    }
                }               
                return exCode;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }*/
   /* 
    public String inputOrderDetailIDExport(ArrayList<WareHouseDetailExport> wareHouseDetailExport) {
        String exCode;
        do {
            try {
                System.out.println("Enter ID of the exCode:");
                exCode = sc.nextLine();
                if (Validation.checkEmptyString(exCode)) {
                    throw new Exception("ID can't be left blank!");
                }
                for (int i = 0; i < wareHouseDetailExport.size(); i++) {
                    if (wareHouseDetailExport.get(i).getExCodeDetailId().equalsIgnoreCase(exCode)) {
                    } else {
                        throw new Exception("ID can't be duplicated");
                    }
                }               
                return exCode;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }*/

    public String inputName(ProductMap prMap) {
        String name;
        do {
            try {
                System.out.println("input your pr name: ");
                name = sc.nextLine();
                if (Validation.checkEmptyString(name)) {
                    throw new Exception("don't let empty");

                }
                return name;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (true);

    }

    public String inputAttribute(ProductMap prMap) {
        String attributes;
        do {

            try {
                System.out.println("input other attributes: ");
                attributes = sc.nextLine();
                if (Validation.checkEmptyString(attributes)) {
                    throw new Exception("dont let blank ");
                }
                return attributes;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (true);
    }

    public LocalDate inputExDate(ProductMap prMap) {
        LocalDate exDate = null;
        String exDateString;

        do {
            try {
                System.out.println("input exdate :");
                exDateString = sc.nextLine();
                if (Validation.checkEmptyString(exDateString)) {
                    throw new Exception("dont let blank ");
                }
                if (!Validation.checkDatePattern(exDateString)) {
                    throw new Exception("must follow 'dd/MM/yyyy'");
                }
                exDate = LocalDate.parse(exDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } while (true);
        return exDate;
    }

    public LocalDate inputManuDate(ProductMap prMap) {
        LocalDate manuDate = null;
        String manuDateString;
        do {
            try {
                System.out.println("input you Manufacturing date:");
                manuDateString = sc.nextLine();
                if (Validation.checkEmptyString(manuDateString)) {
                    throw new Exception("Manufacturing date cannot be empty.");
                }
                if (!Validation.checkDatePattern(manuDateString)) {
                    throw new Exception("must follow format 'dd/MM/yyyy'");
                }
                manuDate = LocalDate.parse(manuDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        return manuDate;
    }

    public int inputExcode(WareHouseMap whMap) {
        int exCode;
        do {
            try {
                System.out.println("Input your exCode: ");
                exCode = sc.nextInt();
                String exCodeStr = Integer.toString(exCode);
                if (exCodeStr.length() == 7) {
                    break;
                } else {
                    throw new Exception("Ex Code must have 7 digits");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        return exCode;
    }

    public Boolean getYesNo(String str) {
        String choice;

        do {
            try {
                System.out.println("do you want to continue to " + str + " , please choose yes or no: ");
                choice = sc.nextLine();
                if (!choice.toLowerCase().matches("y|n|no|yes")) {
                    throw new Exception("you must enter yes or no ");
                } else if (Validation.checkEmptyString(choice)) {
                    throw new Exception("you must not leave blank");

                } else {
                    if (choice.toLowerCase().matches("y||yes")) {
                        return true;
                    }
                    if (choice.toLowerCase().matches("n||no")) {
                        return false;
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);

    }
    
/*    public static void (ArrayList<Product> products, LocalDate targetDate) {
        System.out.println("" + targetDate + ":");
        for (Product product : products) {
            if (product.getExpirationDate().isAfter(targetDate)) {
                System.out.println(product.getName() + " - hsd " + product.getExpirationDate());
            }
        }
    
    LocalDate targetDate = LocalDate.of(2003, 2, 25);
    } */
      
    public int inputQuantity(String type){
        int quantity;
        do {            
            try {
                System.out.println("Enter "+ type + " quantity: ");
                quantity = Integer.parseInt(sc.nextLine());
                if (quantity <= 0) {
                    throw new Exception("Quantity must be a positive integer");
                }
                return quantity;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }
    

}
