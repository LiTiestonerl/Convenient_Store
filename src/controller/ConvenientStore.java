/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Product;
import model.WareHouse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import model.WareHouseDetailExport;
import model.WareHouseDetailImport;
import validate.Input;
import validate.Validation;
import view.IConvenientStore;

/**
 *
 * @author Admin
 */
public class ConvenientStore implements IConvenientStore {

    private ProductMap prMap;
    private WareHouseMap whMap;
    private Validation vl;
    private ArrayList<WareHouseDetailImport> wareHouseDetailList1;
    private ArrayList<WareHouseDetailExport> wareHouseDetailList2;
    private Input ip;

    public ConvenientStore() {
        prMap = new ProductMap();
        whMap = new WareHouseMap();
        vl = new Validation();
        ip = new Input();
        wareHouseDetailList1 = new ArrayList<>();
        wareHouseDetailList2 = new ArrayList<>();

    }

    @Override
    public void addPr() {
        String prCode = ip.inputPrCode(prMap, "add");
        String prName = ip.inputName(prMap);
        LocalDate prManu = ip.inputManuDate(prMap);
        LocalDate prEx = ip.inputExDate(prMap);
        int quantity = ip.inputQuantity("product");
        String prAttri = ip.inputAttribute(prMap);
        Product pr = new Product(prCode, prName, prManu, prEx, quantity, prAttri);
        boolean checkStatus = prMap.addProduct(pr);
        if (checkStatus) {
            System.out.println("added succesfull");
        } else {
            System.out.println("added not succesfull");
        }

    }

    @Override
    public void updatePr() {
        if (prMap.isEmpty()) {
            System.out.println("There is no product");
        } else {
            String code = ip.inputPrCode(prMap, "find");
            Product prUpdate = prMap.findProductByCode(code);
            if (prUpdate == null) {
                System.out.println("Product does not exist");
            } else if (prUpdate != null) {
                prUpdate.setPrName(ip.inputName(prMap));
                prUpdate.setManuDate(ip.inputManuDate(prMap));
                prUpdate.setExDate(ip.inputExDate(prMap));
                prUpdate.setQuantity(ip.inputQuantity("product"));
                prUpdate.setAttributes(ip.inputAttribute(prMap));
                System.out.println("Update succesfully");
                System.out.println(prUpdate.toString());
            }
        }
    }

    @Override
    public void deletePr() {
        if (prMap.isEmpty()) {
            System.out.println("There is no product");
        } else {
            String prCode = ip.inputPrCode(prMap, "find");
            boolean hasImportExportDetails = false;

            for (Product product : prMap.getValues()) {
                if (product.getPrCode().equalsIgnoreCase(prCode)) {
                    hasImportExportDetails = false;
                    break;
                }
            }
            for (WareHouseDetailImport whDetail1 : wareHouseDetailList1) {
                if (whDetail1.getPrCode().equalsIgnoreCase(prCode)) {
                    hasImportExportDetails = true;
                    break;
                }
            }
            for (WareHouseDetailExport whDetail2 : wareHouseDetailList2) {
                if (whDetail2.getPrCode().equalsIgnoreCase(prCode)) {
                    hasImportExportDetails = true;
                    break;
                }
            }
            if (!hasImportExportDetails) {
                Product pr = prMap.findProductByCode(prCode);
                boolean choice = ip.getYesNo("delete this product");
                if (choice) {
                    prMap.removeProduct(pr);
                    System.out.println("delete succesfully !");
                } else {
                    System.out.println("cancelled, back to menu");
                }
            } else {
                System.out.println("can't deleted because the items also has in Import or Export");
            }
        }
    }

    @Override
    public void showAllPr() {
        String filePath = "C:\\Users\\Admin\\Desktop\\LAB221\\ConvenientStore\\Product.txt";
        ArrayList<Product> productTxt = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String readString;
            while ((readString = reader.readLine()) != null) {
                String[] partSplit = readString.split(",");
                if (partSplit.length == 6) {
                    String prCode = partSplit[0].trim();
                    String prName = partSplit[1].trim();
                    LocalDate manuDate = LocalDate.parse(partSplit[2].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    LocalDate exDate = LocalDate.parse(partSplit[3].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    int quantity = Integer.parseInt(partSplit[4].trim());
                    String attributes = partSplit[5].trim();
                    Product product = new Product(prCode, prName, manuDate, exDate, quantity, attributes);
                    productTxt.add(product);
                    prMap.addProduct(product);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!productTxt.isEmpty()) {
            System.out.println("List of product from product.dat: ");
            for (Product product : productTxt) {
                System.out.println("Product Code: " + product.getPrCode());
                System.out.println("Product Name: " + product.getPrName());
                System.out.println("Manufacturing Date: " + product.getManuDate());
                System.out.println("Expiration Date: " + product.getExDate());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("Attributes: " + product.getAttributes());

                if (product.getQuantity() >= 100) {
                    System.out.println("Status : Available.");
                } else {
                    System.out.println("Status : N/A.");

                }
                System.out.println("========================================");
            }
        }/*else if(!prMap.isEmpty()) {
            System.out.println("List of product from prMap: ");
            for (Product product : prMap.getValues()) {
                System.out.println("Product Code: " + product.getPrCode());
                System.out.println("Product Name: " + product.getPrName());
                System.out.println("Manufacturing Date: " + product.getManuDate());
                System.out.println("Expiration Date: " + product.getExDate());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("Attributes: " + product.getAttributes());
                System.out.println("==============================");
            }
        } */ else {
            System.out.println("There is no product");
        }

    }

    @Override
    public void createImport() {
        System.out.println("input Import Code to create: ");
        int exCode = ip.inputExcode(whMap);
        String prCode = ip.inputPrCodeWh();
        int quantity = ip.inputQuantity("Import");
        for (Product product : prMap.getValues()) {
            if (product.getPrCode().equalsIgnoreCase(prCode)) {
                product.setQuantity(product.getQuantity() + quantity);
            }
        }
        wareHouseDetailList1.add(new WareHouseDetailImport(prCode, quantity, exCode, LocalDate.now(), wareHouseDetailList1, wareHouseDetailList2));
        WareHouseDetailImport whDImport = new WareHouseDetailImport(prCode, quantity, exCode, LocalDate.now(), wareHouseDetailList1, wareHouseDetailList2);
        boolean checkVar = whMap.addWareHouse(whDImport);
        if (checkVar) {
            System.out.println("added success");
        } else {
            System.out.println("added not success");
        }
    }

    @Override
    public void createExport() {
        System.out.println("input Export Code to create: ");
        int exCode = ip.inputExcode(whMap);
        String prCode = ip.inputPrCodeWh();
        int quantity = ip.inputQuantity("Export");
        for (Product product : prMap.getValues()) {
            if (product.getPrCode().equalsIgnoreCase(prCode)) {
                if (product.getQuantity() <= quantity) {
                    System.out.println("the quantity of this product enought to export a receipt");
                } else {
                    product.setQuantity(product.getQuantity() - quantity);
                }
            }
        }
        wareHouseDetailList2.add(new WareHouseDetailExport(prCode, quantity, exCode, LocalDate.now(), wareHouseDetailList1, wareHouseDetailList2));
        WareHouseDetailExport whDExport = new WareHouseDetailExport(prCode, quantity, exCode, LocalDate.now(), wareHouseDetailList1, wareHouseDetailList2);
        boolean checkVar = whMap.addWareHouse(whDExport);
        if (checkVar) {
            System.out.println("added success");
        } else {
            System.out.println("added not success");
        }
    }

    @Override
    public void expired() {
        do {
            try {
                if (prMap.isEmpty()) {
                    throw new Exception("There is no product");
                } else {
                    System.out.println("input Product Code to find:");
                    String prCode = ip.inputPrCode(prMap, "find");
                    Product foundPr = prMap.findProductByCode(prCode);
                    if (foundPr.getManuDate().isAfter(foundPr.getExDate())) {
                        System.out.println("the product is out of date");
                        System.out.println("Product Code: " + foundPr.getPrCode());
                        System.out.println("Product Name: " + foundPr.getPrName());
                        System.out.println("Product ManuDate: " + foundPr.getManuDate());
                        System.out.println("Product ExDate: " + foundPr.getExDate());
                        break;
                    } else {
                        System.out.println("the product not out of date");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    @Override
    public void prSelling() {
        do {
            try {
                System.out.println("please input product code:");
                String prCode = ip.inputPrCode(prMap, "find");
                if (prMap.isEmpty()) {
                    throw new Exception("There is no product.");
                } else {
                    for (Product product : prMap.getValues()) {
                        if (product.getPrCode().equalsIgnoreCase(prCode) && product.getQuantity() > 0 && product.getManuDate().isBefore(product.getExDate())) {
                            System.out.println("Product Code: " + product.getPrCode());
                            System.out.println("Product Name: " + product.getPrName());
                            System.out.println("Manufacturing Date: " + product.getManuDate());
                            System.out.println("Expiration Date: " + product.getExDate());
                            System.out.println("Quantity: " + product.getQuantity());
                            System.out.println("Attributes: " + product.getAttributes());
                            System.out.println("==============================");
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    @Override
    public void outOfStock() {
        do {
            try {
                System.out.println("please input product code:");
                String prCode = ip.inputPrCode(prMap, "find");
                if (prMap.isEmpty()) {
                    throw new Exception("There is no product.");
                } else {
                    ArrayList<Product> outOfStockProducts = new ArrayList<>();
                    for (Product product : prMap.getValues()) {
                        if (product.getPrCode().equalsIgnoreCase(prCode) && product.getQuantity() <= 3) {
                            outOfStockProducts.add(product);
                        }
                        if (outOfStockProducts.isEmpty()) {
                            System.out.println("There is no product are running of stock");
                            return;
                        } else {
                            Collections.sort(outOfStockProducts, Comparator.comparingInt(Product::getQuantity));
                            System.out.println("Products running out of stock (quantity <= 3):");
                            for (Product products : outOfStockProducts) {
                                System.out.println("Product Code: " + products.getPrCode());
                                System.out.println("Product Name: " + products.getPrName());
                                System.out.println("Manufacturing Date: " + products.getManuDate());
                                System.out.println("Expiration Date: " + products.getExDate());
                                System.out.println("Quantity: " + products.getQuantity());
                                System.out.println("Attributes: " + products.getAttributes());
                                System.out.println("==============================");
                            }
                        }
                    }
                }

            } catch (Exception e) {
            }

        } while (true);

    }

    @Override
    public void receipt() {
        String filePath = "C:\\Users\\Admin\\Desktop\\LAB221\\ConvenientStore\\WareHouse.txt";
        String prCode;
        ArrayList<WareHouse> whReceipt = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bfReader = new BufferedReader(fileReader);
            String line;
            System.out.println("Please input product Code:");

            while ((line = bfReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (line.length() == 5) {
                    int exCodeTxt = Integer.parseInt(parts[1].trim());
                    LocalDate exSlipTxt = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String prCodeTxt = parts[3].trim();
                    int quantity = Integer.parseInt(parts[4].trim());
                    whReceipt.add(new WareHouse(exCodeTxt, exSlipTxt, wareHouseDetailList1, wareHouseDetailList2));
                }
            }
            bfReader.close();

            prCode = ip.inputPrCode(prMap, "find");

            if (whMap.isEmpty()) {
                throw new Exception("There is no Import/Export receipt");
            } else {
                for (WareHouseDetailImport whImports : wareHouseDetailList1) {
                    if (whImports.getPrCode().equalsIgnoreCase(prCode)) {
                        whReceipt.add(whImports);
                        System.out.println("Found Import receipt.....");
                    }
                }
                for (WareHouseDetailExport whExports : wareHouseDetailList2) {
                    if (whExports.getPrCode().equalsIgnoreCase(prCode)) {
                        whReceipt.add(whExports);
                        System.out.println("Found Export receipt......");
                    }
                }
            }

            for (WareHouse wareHouse : whReceipt) {
                System.out.println("ExCode: " + wareHouse.getExCode());
                System.out.println("ExSlip: " + wareHouse.getExSlip().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                if (wareHouse instanceof WareHouseDetailImport) {
                    WareHouseDetailExport exportReceipt = (WareHouseDetailExport) wareHouse;
                    System.out.println("PrCode: " + exportReceipt.getPrCode());
                    System.out.println("Quantity: " + exportReceipt.getQuantity());
                } else if (wareHouse instanceof WareHouseDetailImport) {
                    WareHouseDetailImport importReceipt = (WareHouseDetailImport) wareHouse;
                    System.out.println("PrCode: " + importReceipt.getPrCode());
                    System.out.println("Quantity: " + importReceipt.getQuantity());

                }
                System.out.println("==============================");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void storeDataPr() {
        String filePath = "C:\\Users\\Admin\\Desktop\\LAB221\\ConvenientStore\\Product.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bfWriter = new BufferedWriter(fileWriter);

            for (Product product : prMap.getValues()) {
                bfWriter.write(product.getPrCode());
                bfWriter.write(",");
                bfWriter.write(product.getPrName());
                bfWriter.write(",");
                bfWriter.write(product.getManuDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                bfWriter.write(",");
                bfWriter.write(product.getExDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                bfWriter.write(",");
                bfWriter.write(String.valueOf(product.getQuantity()));
                bfWriter.write(",");
                bfWriter.write(product.getAttributes());
                bfWriter.write("\n");
            }
            bfWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void storeDateWh() {
        String filePath = "C:\\Users\\Admin\\Desktop\\LAB221\\ConvenientStore\\WareHouse.txt";
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bfWriter = new BufferedWriter(fileWriter);
            for (WareHouseDetailImport whImport : wareHouseDetailList1) {
                int exCode = whImport.getExCode();
                LocalDate exSlip = whImport.getExSlip();

                bfWriter.write("Import");
                bfWriter.write(",");
                bfWriter.write(String.valueOf(exCode));
                bfWriter.write(",");
                bfWriter.write(exSlip.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                bfWriter.write(",");
                bfWriter.write(whImport.getPrCode());
                bfWriter.write(",");
                bfWriter.write(String.valueOf(whImport.getQuantity()));
                bfWriter.write("\n");
            }
            for (WareHouseDetailExport whExport : wareHouseDetailList2) {
                int exCode = whExport.getExCode();
                LocalDate exSlip = whExport.getExSlip();

                bfWriter.write("Export");
                bfWriter.write(",");
                bfWriter.write(String.valueOf(exCode));
                bfWriter.write(",");
                bfWriter.write(exSlip.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                bfWriter.write(",");
                bfWriter.write(whExport.getPrCode());
                bfWriter.write(",");
                bfWriter.write(String.valueOf(whExport.getQuantity()));
                bfWriter.write("\n");
            }
            bfWriter.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteAllpr() {
        try {
            if (prMap.isEmpty()) {
                throw new Exception("There is no product");
            } else {
                System.out.println("input ManuDate:");
                LocalDate x = ip.inputManuDate(prMap);
                System.out.println("input exDate:");
                LocalDate y = ip.inputExDate(prMap);
                for (Product product : prMap.getValues()) {
                    if (product.getExDate().isAfter(x) || product.getExDate().isBefore(y)) {
                        prMap.removeProduct(product);
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void setQuantityforExDate() {
        ArrayList<LocalDate> ManuDate = new ArrayList<>();
        for (Product pr : prMap.getValues()) {
            ManuDate.add(pr.getManuDate());
        }
        Collections.sort(ManuDate);
        Collections.reverse(ManuDate);
        ArrayList<Product> sortedPr = new ArrayList<>();
        for (LocalDate localDate : ManuDate) {
            System.out.println(localDate);

        }
        for (LocalDate quan : ManuDate) {
            for (Product pr : prMap.getValues()) {
                if (pr.getManuDate().equals(quan)) {
                    sortedPr.add(pr);
                }
            }
        }
        for (Product value : sortedPr) {
            System.out.println(value);
        }
        prMap.clear();
        System.out.println("================");
        for (Product product : sortedPr) {
            prMap.addProduct(product);
        }
        for (Product value : prMap.getValues()) {
            System.out.println(value);
        }
    }
}
