/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDtables;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Region {
    private int id_region;
    private String region_name;
    private ArrayList<Country> countriesByRegion;
    
public void setIDRegion(int id){
    this.id_region = id;
}
public int getIDRegion(){
    return id_region;
}
public void setRegionName(String name){
    this.region_name = name;
}
public String getRegionName(){
    return region_name;
}
public void setCountriesByRegion(ArrayList<Country> list){
    this.countriesByRegion = list;
}
public ArrayList<Country> getCountriesByRegion(){
    return countriesByRegion;
}
}

