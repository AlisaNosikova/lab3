/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import BDtables.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Calculator {
    HashMap<Integer, HashMap<Integer,Double>> consumpList = new HashMap<>(); 
    public  HashMap<Integer, HashMap<Integer,Double>> calculateReactor(ArrayList<ReactorDB> reactors){
        for (ReactorDB reactor: reactors){
            HashMap<Integer, Double> consumpByReactor = new HashMap<>();
            for (KIUM kium: reactor.getKiumsByReactor()){
            double consump = 0.0;
            if (kium.getYear() == reactor.getFirst_grid_connection().getYear()){
               consump = reactor.getFirst_load();
               consumpByReactor.put(reactor.getFirst_grid_connection().getYear(), consump);
            }
            else{ 
                consump = reactor.getTermal_capacity()/reactor.getBurnup()*kium.getKium();
                consumpByReactor.put(kium.getYear(), consump);
            }
            }
           consumpList.put(reactor.getID(),consumpByReactor);
        }
      System.out.println(consumpList);   
    return consumpList;
    }
    public HashMap<String, HashMap<Integer,Double>> calculateByCountry(ArrayList<Country> countries,HashMap<Integer, HashMap<Integer,Double>> consumpList ){
        HashMap <String, HashMap<Integer,Double>> consumpCountry = new HashMap <>();
        for (Country country: countries){
            HashMap<Integer,Double> consumpYears = new HashMap<>();
            for (int year=2014;year<=2024;year++){
            double energy = 0;
            for (ReactorDB reactor: country.getReactorsByCountry()){
              energy += consumpList.get(reactor.getID()).get(year);
            }
            consumpYears.put(year, energy);
        }
            consumpCountry.put(country.getCountryName(), consumpYears);
            }
        System.out.println(consumpCountry);
        return consumpCountry;
    }
        public HashMap<String, HashMap<Integer,Double>> calculateByRegion(ArrayList<Region> regions,HashMap<String, HashMap<Integer,Double>> consumpList ){
        HashMap <String, HashMap<Integer,Double>> consumpRegion = new HashMap <>();
        for (Region region: regions){
            HashMap<Integer,Double> consumpYears = new HashMap<>();
            for (int year=2014;year<=2024;year++){
            double energy = 0;
            for (Country country: region.getCountriesByRegion()){
              energy += consumpList.get(country.getCountryName()).get(year);
            }
            consumpYears.put(year, energy);
        }
            consumpRegion.put(region.getRegionName(), consumpYears);
            }
         System.out.println(consumpRegion);
        return consumpRegion;
    }
        public HashMap<String, HashMap<Integer,Double>> calculateByCompany(ArrayList<Company> companies,HashMap<Integer, HashMap<Integer,Double>> consumpList, String key ){
         HashMap <String, HashMap<Integer,Double>> consumpCompany = new HashMap <>();
         for (Company company: companies){
            HashMap<Integer,Double> consumpYears = new HashMap<>();
            for (int year=2014;year<=2024;year++){
            double energy = 0;
            System.out.println(company.getReactorsByCompany());
           for (ReactorDB reactor: company.getReactorsByCompany().get(key)){
             energy += consumpList.get(reactor.getID()).get(year);
            }
            consumpYears.put(year, energy);
        }
            consumpCompany.put(company.getCompanyName(), consumpYears);
            }
        System.out.println(consumpCompany);
        return consumpCompany;
            
        }
}
    

