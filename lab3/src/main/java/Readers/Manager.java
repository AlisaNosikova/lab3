/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import BDtables.*;
import Chain.BaseHandler;
import com.mycompany.lab3.SQLReader;
import com.mycompany.lab3.StorageDB;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Manager {
   private Storage storage;
   private StorageDB storageDB;
   private BaseHandler startHandler;
   private SQLReader SQLReader;
   private DBCreator creator;
   private Calculator calculator;
   private Matcher matcher;

    public Manager() {
        this.storage = new Storage();
        this.storageDB = new StorageDB();
        this.creator = new DBCreator();
        this.calculator = new Calculator();
        BaseHandler handlerJSON = new BaseHandler(new ReaderJSON());
        BaseHandler handlerXML = new BaseHandler(new ReaderXML());
        BaseHandler handlerYaml= new BaseHandler( new ReaderYaml());
        startHandler = handlerJSON;
        handlerJSON.setNext(handlerXML);
        handlerXML.setNext(handlerYaml);
    }
    public void useChain(File file){
        storage.addReactors( startHandler.handle(file));
    }
    public void createDB() throws SQLException, IOException{
        creator.createDB();
        creator.createDB();
        creator.createTables();
    }
    public void deleteDB() throws SQLException{
        creator.dropDB();
    }
    public ArrayList<Reactor> getInfo(){
        return storage.getList();
    }
    public Connection getConnection(){
        return creator.getConnection();
    }
    
    public void loadInfo() throws SQLException{
        ArrayList<Country> countries = new ArrayList<>(); 
        ArrayList <ReactorDB> reactors = new ArrayList<>();
        SQLReader = new SQLReader(creator.getConnection());
        ArrayList<Region> regionList = SQLReader.SQLRegionsReader();
        storageDB.addRegions(regionList);
     
        for (Region region: regionList){
            for (Country country: region.getCountriesByRegion()){
                countries.add(country);
            for(ReactorDB reactor: country.getReactorsByCountry()){
                reactors.add(reactor);
        }
    }
    }
        matcher = new Matcher();
        matcher.match(getInfo(), reactors);
        storageDB.addCompanies(SQLReader.SQLCompanyReader(reactors));
        storageDB.addCountries(countries);
        storageDB.addReactors(reactors);
        
    }
    public void calculate(String way){
        
         if(storageDB.getConsumpReactor() == null){  
           storageDB.addConsumpReactor(calculator.calculateReactor(storageDB.getReactorList()));
         }
        switch(way){
               case "country" ->calculator.calculateByCountry(storageDB.getCountryList(), storageDB.getConsumpReactor());
               case "region" -> {
                   if (storageDB.getConsumpCountry()==null){
                       HashMap<String,HashMap<Integer,Double>> consumpCountry =calculator.calculateByCountry(storageDB.getCountryList(), storageDB.getConsumpReactor());
                       calculator.calculateByRegion(storageDB.getRegionList(), consumpCountry);
                   }
                   else{
                       calculator.calculateByRegion(storageDB.getRegionList(), storageDB.getConsumpCountry());
                   }
               } 
               case "operator" -> {
                   calculator.calculateByOperator(storageDB.getCompanyList(), storageDB.getConsumpReactor(), way);
               }
              
        }
          
    }
 
    } 


