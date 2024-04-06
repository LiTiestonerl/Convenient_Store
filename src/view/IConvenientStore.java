/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author Admin
 */
public interface IConvenientStore {

    // manage products
    public void addPr();

    public void updatePr();

    public void deletePr();

    public void showAllPr();
    
    public void deleteAllpr();

    // manage warehouse
    public void createImport();

    public void createExport();

    //report
    public void expired();

    public void prSelling();

    public void outOfStock();

    public void receipt();
    
    
    // them chuc nang xoa tat ca cac san pham co ngay het han tu ngay x den ngay y;
    // store data
    public void storeDataPr();

    public void storeDateWh();
    
    public void setQuantityforExDate();
}
