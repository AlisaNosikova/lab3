/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import BDtables.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class StorageDB {
    private ArrayList<Region> regionList;
    private ArrayList<Country> countryList;
    private ArrayList<ReactorDB> reactorList;
    private ArrayList<Company> companyList;
    private HashMap<Integer, HashMap<Integer,Double>> consumpByReactors;
    private HashMap<String, HashMap<Integer,Double>> consumpByCountries;
    private HashMap<String, HashMap<Integer,Double>> consumpByRegions;
    private HashMap<String, HashMap<Integer,Double>> consumpByOperators;
    public void addRegions(ArrayList<Region> regions){
        regionList = regions;
    }
    public void addCountries(ArrayList<Country> countries){
        countryList = countries;
    }
    public ArrayList<Region> getRegionList(){
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
    public void addCompanies(ArrayList<Company> companies){
        companyList = companies;
    }
    public ArrayList<Company> getCompanyList(){
       return companyList;
    }
    public void addConsumpReactor(HashMap<Integer, HashMap<Integer,Double>> list){
          consumpByReactors = list;
    }
    public HashMap<Integer, HashMap<Integer,Double>> getConsumpReactor(){
          return consumpByReactors;
      }
        public void addConsumpCountry(HashMap<String, HashMap<Integer,Double>> list){
          consumpByCountries = list;
    }
      public HashMap<String, HashMap<Integer,Double>> getConsumpCountry(){
          return consumpByCountries;
      }
    public void addConsumpRegion(HashMap<String, HashMap<Integer,Double>> list){
          consumpByRegions = list;
    }
      public HashMap<String, HashMap<Integer,Double>> getConsumpRegion(){
          return consumpByRegions;
      }
       public void addConsumpOperator(HashMap<String, HashMap<Integer,Double>> list){
          consumpByOperators = list;
    }
      public HashMap<String, HashMap<Integer,Double>> getConsumpOperator(){
          return consumpByOperators;
      }
}
