/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class WareHouseDetailImport extends WareHouse{
    private String prCode;
    private int quantity;

    public WareHouseDetailImport(String prCode, int quantity, int exCode, LocalDate exSlip, ArrayList<WareHouseDetailImport> wareHouseDetailImport, ArrayList<WareHouseDetailExport> wareHouseDetailExport) {
        super(exCode, exSlip, wareHouseDetailImport, wareHouseDetailExport);
        this.prCode = prCode;
        this.quantity = quantity;
    }

    public String getPrCode() {
        return prCode;
    }

    public void setPrCode(String prCode) {
        this.prCode = prCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}