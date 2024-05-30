/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab3;

import BDtables.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

    
/**
 *
 * @author User
 */
public class SQLReader {
    Connection con;
    public SQLReader(Connection con){
    this.con = con;
}
public void SQLRegionsReader() throws SQLException{
    String queryNested = "SELECT id_region, region_name FROM regions reg";
    PreparedStatement statementNested = con.prepareStatement(queryNested);
    ResultSet resultSetNested = statementNested.executeQuery();
    ArrayList<Region> regions = new ArrayList<Region>();
     while(resultSetNested.next()){
            Region region = new Region();
            region.setIDRegion(resultSetNested.getInt("id_region"));
            region.setRegionName(resultSetNested.getString("region_name"));
            region.setCountriesByRegion(SQLCountryReader(resultSetNested.getInt("id_region")));
            regions.add(region);
    }
}
public ArrayList<Country> SQLCountryReader(int id_region) throws SQLException{
       String query = "SELECT id_country, country_name FROM countries WHERE id_region = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id_region);  
       ResultSet resultSet = statement.executeQuery();
       ArrayList<Country> countries = new ArrayList<Country>();
         while(resultSet.next()){
            Country country = new Country();
            country.setIDCountry(resultSet.getInt("id_country"));
            country.setCountryName(resultSet.getString("country_name"));
            countries.add(country);
            country.setReactorsByCountry(SQLReactorsReader(country.getIDCountry()));
    }
        return countries;
    
}
 public ArrayList<ReactorDB> SQLReactorsReader(int id_country) throws SQLException{
        String queryNested = "SELECT id_reactor,reactor_name, reactor_type, model, status, thermal_capacity,first_grid_connection,date_shutdown, id_country FROM reactors c WHERE id_country = ?";
        PreparedStatement statementNested = con.prepareStatement(queryNested);
        statementNested.setInt(1, id_country);  
        ResultSet resultSetNested = statementNested.executeQuery();
        HashMap<Integer, HashMap<Integer, Double>> reactorData = new HashMap<>();
        ArrayList<ReactorDB> reactors = new ArrayList<ReactorDB>();
        ArrayList<KIUM> KIUMSbyReactor = new ArrayList<KIUM>();
        while(resultSetNested.next()){
            ReactorDB reactor = new ReactorDB();
            reactor.setID(resultSetNested.getInt("id_reactor"));
            reactor.setName(resultSetNested.getString("reactor_name"));
            reactor.setType(resultSetNested.getString("reactor_type"));
            reactor.setModel(resultSetNested.getString("model"));
            reactor.setStatus(resultSetNested.getString("status"));
            reactor.setTermal_capacity(resultSetNested.getInt("thermal_capacity"));
            reactor.setFirst_grid_connection(resultSetNested.getDate("first_grid_connection").toLocalDate());
            if (resultSetNested.getDate("date_shutdown")!=null){
                reactor.setDate_shutdown(resultSetNested.getDate("date_shutdown").toLocalDate());
            }else{
                reactor.setDate_shutdown(null);
            }
            reactor.setID_country(resultSetNested.getInt("id_country"));
         //   reactor.setName_owner(resultSetNested.getString("name_owner"));
           // reactor.setName_operator(resultSetNested.getString("name_operator"));
           
            reactors.add(reactor);
           // HashMap<Integer, Double> kiumData = getKiums(reactor_ID);
           // reactorData.put(reactor_ID, kiumData);   
    }
       
      return reactors;  
    }
public ArrayList<KIUM> getKiums(ReactorDB reactor) throws SQLException{
    String query = "SELECT k.kium_value, k.year FROM kiums k WHERE k.id_reactor = ?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setInt(1, reactor.getID());  
    ResultSet resultSet = statement.executeQuery();
    Integer year = 0;
    Integer year_down = 0;
    Integer year_start = 0;
    ArrayList<KIUM> kiumsByReactor = new ArrayList<>();
    if (reactor.getDate_shutdown()!=null){
    year_down = reactor.getDate_shutdown().getYear();
    }
    year_start = reactor.getFirst_grid_connection().getYear();
    while(resultSet.next()){
        KIUM kium = new KIUM();
        kium.setKium(resultSet.getDouble("kium_value")); 
        kium.setYear(resultSet.getInt("year"));
        kiumsByReactor.add(kium);
    }
       
  return completeYears(kiumsByReactor, year_down, year_start);
       
}
public ArrayList<KIUM> completeYears(ArrayList<KIUM> kiums, int year_down, int yearStart){
    for (KIUM kium: kiums){
        for (int year = 2014; year <= 2024; year++) {
            if (kium.getYear()!=year) {
                KIUM newKium = new KIUM();
                if ((year< yearStart || year>= year_down) && year_down!=0){
                    newKium.setYear(year);
                    newKium.setKium(0.0);
                }
                else{
                    newKium.setYear(year);
                    newKium.setKium(0.85);
                }
                kiums.add(newKium);
            }
        }
    }
      return kiums;
    }
}
