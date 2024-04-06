/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class Product implements Serializable{

    private String prCode;
    private String prName;
    private LocalDate manuDate;
    private LocalDate exDate;
    private int quantity;
    private String attributes;
    
    public Product(){
        
    }

    public Product(String prCode, String prName, LocalDate manuDate, LocalDate exDate, int quantity, String attributes) {
        this.prCode = prCode;
        this.prName = prName;
        this.manuDate = manuDate;
        this.exDate = exDate;
        this.quantity = quantity;
        this.attributes = attributes;
    }   

    public String getPrCode() {
        return prCode;
    }

    public void setPrCode(String prCode) {
        this.prCode = prCode;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public LocalDate getManuDate() {
        return manuDate;
    }

    public void setManuDate(LocalDate manuDate) {
        this.manuDate = manuDate;
    }

    public LocalDate getExDate() {
        return exDate;
    }

    public void setExDate(LocalDate exDate) {
        this.exDate = exDate;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Override
    public String toString() {
        return "Product{" + "prCode=" + prCode + ", prName=" + prName + ", manuDate=" + manuDate + ", exDate=" + exDate + ", attributes=" + attributes + '}';
    }
    
    

}
