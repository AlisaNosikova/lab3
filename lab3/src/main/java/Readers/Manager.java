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
   private boolean isLoaded;
   

    public Manager() {
        this.storage = new Storage();
        this.storageDB = new StorageDB();
        this.creator = new DBCreator();
        this.calculator = new Calculator();
        this.isLoaded = false;
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
       creator.createTables();
       creator.insert();
         // insertTables(connection, "companies");
     //insertTables(connection, "reactors");
    // insertTables(connection, "kiums");
        
    }
    public void connectToDB() throws SQLException{
         creator.createDB();
    }
    public void deleteDB() throws SQLException{
        creator.dropDB();
    }
    public ArrayList<Reactor>getInfo(){
        return storage.getList();
    }
    public Connection getConnection(){
        return creator.getConnection();
    }
    public HashMap<String,HashMap<Integer,Double>> getInfoConsump(String text){
        HashMap<String,HashMap<Integer,Double>> list = new HashMap<String,HashMap<Integer,Double>>();
        switch(text){
            case "По странам" -> list = storageDB.getConsumpCountry();
            case "По регионам" -> list = storageDB.getConsumpRegion();
            case  "По операторам" -> list = storageDB.getConsumpOperator();
        }
        return list;
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
                System.out.println(reactor);
        }
    }
    }
        matcher = new Matcher();
        matcher.match(getInfo(), reactors);
        storageDB.addCompanies(SQLReader.SQLCompanyReader(reactors));
        storageDB.addCountries(countries);
        storageDB.addReactors(reactors);
        isLoaded = true;
        
    }
    public boolean getLoadingStatus() throws SQLException{
        return isLoaded;
    }
    public void calculate(String way){
        
         if(storageDB.getConsumpReactor() == null){  
           storageDB.addConsumpReactor(calculator.calculateReactor(storageDB.getReactorList()));
         }
        switch(way){
               case "По странам" ->
               { if (storageDB.getConsumpCountry() == null){
                   storageDB.addConsumpCountry( calculator.calculateByCountry(storageDB.getCountryList(), storageDB.getConsumpReactor()));
               }
               }
               case "По регионам" -> {
                   if (storageDB.getConsumpCountry()==null){
                       HashMap<String,HashMap<Integer,Double>> consumpCountry =calculator.calculateByCountry(storageDB.getCountryList(), storageDB.getConsumpReactor());
                       storageDB.addConsumpRegion(calculator.calculateByRegion(storageDB.getRegionList(), consumpCountry));
                   }
                   else{
                       storageDB.addConsumpRegion(calculator.calculateByRegion(storageDB.getRegionList(), storageDB.getConsumpCountry()));
                   }
               } 
               case "По операторам" -> {
                   storageDB.addConsumpOperator(calculator.calculateByCompany(storageDB.getCompanyList(), storageDB.getConsumpReactor(), way));
               }
              
        }
          
    }
    
 
    } 


