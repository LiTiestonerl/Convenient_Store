/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */

public class WareHouse implements Serializable{
    private int exCode;
    private LocalDate exSlip;
    private ArrayList<WareHouseDetailImport> wareHouseDetailImport;
    private ArrayList<WareHouseDetailExport> wareHouseDetailExport;

    public WareHouse(int exCode, LocalDate exSlip, ArrayList<WareHouseDetailImport> wareHouseDetailImport, ArrayList<WareHouseDetailExport> wareHouseDetailExport) {
        this.exCode = exCode;
        this.exSlip = exSlip;
        this.wareHouseDetailImport = wareHouseDetailImport;
        this.wareHouseDetailExport = wareHouseDetailExport;
    }

    public int getExCode() {
        return exCode;
    }

    public void setExCode(int exCode) {
        this.exCode = exCode;
    }

    public LocalDate getExSlip() {
        return exSlip;
    }

    public void setExSlip(LocalDate exSlip) {
        this.exSlip = exSlip;
    }

    public ArrayList<WareHouseDetailImport> getWareHouseDetailImport() {
        return wareHouseDetailImport;
    }

    public void setWareHouseDetailImport(ArrayList<WareHouseDetailImport> wareHouseDetailImport) {
        this.wareHouseDetailImport = wareHouseDetailImport;
    }

    public ArrayList<WareHouseDetailExport> getWareHouseDetailExport() {
        return wareHouseDetailExport;
    }

    public void setWareHouseDetailExport(ArrayList<WareHouseDetailExport> wareHouseDetailExport) {
        this.wareHouseDetailExport = wareHouseDetailExport;
    }
}