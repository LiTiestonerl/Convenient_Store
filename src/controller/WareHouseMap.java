/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Collection;
import model.WareHouse;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class WareHouseMap {

    private HashMap<String, WareHouse> wareHouseMap;
    
    public WareHouseMap(){
        wareHouseMap = new HashMap<>();
    }

    public HashMap<String, WareHouse> getWareHouseMap() {
        return wareHouseMap;
    }

    public boolean addWareHouse(WareHouse wareHouse) {
        if (wareHouse != null) {
            String exCode = String.valueOf(wareHouse.getExCode());
            if (!wareHouseMap.containsKey(exCode)) {
                wareHouseMap.put(exCode, wareHouse);
                return true;
            }
        }
        return false;
    }

    public boolean removeWareHouseByCode(WareHouse wareHouse) {
        if (wareHouse != null) {
            String exCode = String.valueOf(wareHouse.getExCode());
            if (wareHouseMap.containsKey(exCode)) {
                wareHouseMap.remove(exCode, wareHouse);
                return true;
            }
        }
        return false;
    }

    public WareHouse findWareHouse(int exCode) {
        return wareHouseMap.get(exCode);
    }

    public boolean isEmpty() {
        return wareHouseMap.isEmpty();
    }

    public int size() {
        return wareHouseMap.size();
    }
    
    public Collection<WareHouse> getValue(){
        return wareHouseMap.values();
    }

}
