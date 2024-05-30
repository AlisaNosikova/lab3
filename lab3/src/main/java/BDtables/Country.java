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
public class Country {
    private int id_country;
    private String country_name;
    private int id_region;
    private ArrayList<ReactorDB> reactorsByCountry;
    
public void setIDCountry(int id){
    this.id_country = id;
}
public int getIDCountry(){
    return id_country;
}
public void setCountryName(String name){
    this.country_name = name;
}
public String getCountryName(){
    return country_name;
}
public void setIDRegion(int id){
    this.id_region = id;
}
public int getIDRegion(){
    return id_region;
}
public void setReactorsByCountry(ArrayList<ReactorDB> list){
    this.reactorsByCountry = list;
}
public ArrayList<ReactorDB> getReactorsByCountry(){
    return reactorsByCountry;
}
}
