/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import BDtables.*;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class StorageDB {
    private ArrayList<Region> regionList;
    private ArrayList<Country> countryList;
    private ArrayList<ReactorDB> reactorList;
    public StorageDB(){
        
    }
    public void addRegions(ArrayList<Region> regions){
        regionList = regions;
    }
    public void addCountries(ArrayList<Country> countries){
        countryList = countries;
    }
    public ArrayList<Region> getList(){
        return regionList;
    }
    public ArrayList<Country> getCountryList(){
       return countryList;
    }
    public void addReactors(ArrayList<ReactorDB> reactors){
        reactorList = reactors;
    }
    public ArrayList<ReactorDB> getReactorList(){
       return reactorList;
    }
    
}
