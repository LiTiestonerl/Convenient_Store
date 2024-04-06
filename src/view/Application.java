/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.ConvenientStore;
import java.util.Scanner;
import validate.Input;

/**
 *
 * @author Admin
 */
public class Application {

    public static void main(String[] args) {
        ConvenientStore cvStore = new ConvenientStore();
        Input ip = new Input();
        Scanner sc = new Scanner(System.in);
        int choice;
        int choice_1;

        do {
            System.out.println("1.Manage products");
            System.out.println("2.Manager warehouse");
            System.out.println("3.Report");
            System.out.println("4.Store date to files");
            System.out.println("5.Close the application");
            System.out.println("=========================");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    boolean exitChoie_1 = false;
                    do {
                        System.out.println("1.Add a product");
                        System.out.println("2.Update product information.");
                        System.out.println("3.Delete product.");
                        System.out.println("4.Show all product.");
                        System.out.println("5.Back to Main menu.");
                        System.out.println("6.new function.");
                        System.out.println("7.sort");
                        System.out.println("================================");
                        choice_1 = sc.nextInt();
                        switch (choice_1) {
                            case 1:
                                boolean cont1;
                                do {
                                    cvStore.addPr();
                                    cont1 = ip.getYesNo("add ");
                                    if (!cont1) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                boolean cont2;
                                do {
                                    cvStore.updatePr();
                                    cont2 = ip.getYesNo("Update Product ");
                                    if (!cont2) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 3:
                                boolean cont3;
                                do {
                                    cvStore.deletePr();
                                    cont3 = ip.getYesNo("delete Product ");
                                    if (!cont3) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 4:
                                boolean cont4;
                                do {
                                    cvStore.showAllPr();
                                    cont4 = ip.getYesNo("show Product ");
                                    if (!cont4) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 5:
                                exitChoie_1 = true;
                                break;
                            case 6:
                                cvStore.deleteAllpr();
                                break;
                            case 7:
                                cvStore.setQuantityforExDate();
                                break;
                            default:
                                System.out.println("Invalid Choice, please input again");
                        }

                    } while (!exitChoie_1);
                    break;
                case 2:
                    boolean exitChoie_2 = false;
                    do {
                        System.out.println("1.Create an import receipt");
                        System.out.println("2.Create an export receipt");
                        System.out.println("3.Back to Main menu.");
                        int choice_2 = sc.nextInt();
                        switch (choice_2) {
                            case 1:
                                boolean cont1;
                                do {
                                    cvStore.createImport();
                                    cont1 = ip.getYesNo("import receipt");
                                    if (!cont1) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                boolean cont2;
                                do {
                                    cvStore.createExport();
                                    cont2 = ip.getYesNo("export receipt");
                                    if (!cont2) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 3:
                                exitChoie_2 = true;
                                break;
                            default:
                                System.out.println("Invalid Choice, please input again");
                        }

                    } while (!exitChoie_2);
                    break;
                case 3:
                    boolean exitChoice_3 = false;
                    do {
                        System.out.println("1.Products that have expired");
                        System.out.println("2.The products that the store is selling");
                        System.out.println("3.Products that are running out of stock");
                        System.out.println("4.Import/export receipt of a product");
                        System.out.println("5.Back to Main menu.");
                        int choice_3 = sc.nextInt();
                        switch (choice_3) {
                            case 1:
                                boolean cont1;
                                do {
                                    cvStore.expired();
                                    cont1 = ip.getYesNo("export receipt");
                                    if (!cont1) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                cvStore.prSelling();
                                break;
                            case 3:
                                cvStore.outOfStock();
                                break;
                            case 4:
                                boolean cont4;
                                do {
                                    cvStore.receipt();
                                    cont4 = ip.getYesNo("show receipt ");
                                    if (!cont4) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 5:
                                exitChoice_3 = true;
                                break;
                            default:
                                System.out.println("Invalid Choice, please input again");

                        }
                    } while (!exitChoice_3);
                    break;
                case 4:
                    System.out.println("Store date to files:");
                    cvStore.storeDataPr();
                    cvStore.storeDateWh();
                    System.out.println("Store succesfull");
                    break;
                case 5:
                    System.out.println("Closing the application.....");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice, please input again");
            }

        } while (true);
    }

}
