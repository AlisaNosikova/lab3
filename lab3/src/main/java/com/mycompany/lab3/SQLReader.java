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
    }
        return countries;
    
}
 public void SQLReader() throws SQLException{
        String queryNested = "SELECT id_reactor,reactor_name, reactor_type, model, status, thermal_capacity,first_grid_connection,date_shutdown, id_country FROM reactors c";
        PreparedStatement statementNested = con.prepareStatement(queryNested);
        ResultSet resultSetNested = statementNested.executeQuery();
        HashMap<Integer, HashMap<Integer, Double>> reactorData = new HashMap<>();
        ArrayList<ReactorDB> reactors = new ArrayList<ReactorDB>();
        while(resultSetNested.next()){
            ReactorDB reactor = new ReactorDB();
            reactor.setID(resultSetNested.getInt("id_reactor"));
            reactor.setName(resultSetNested.getString("reactor_name"));
            reactor.setType(resultSetNested.getString("reactor_type"));
            reactor.setModel(resultSetNested.getString("model"));
            reactor.setStatus(resultSetNested.getString("status"));
            reactor.setTermal_capacity(resultSetNested.getInt("thermal_capacity"));
            reactor.setFirst_grid_connection(resultSetNested.getDate("first_grid_connection").toLocalDate());
          //  reactor.setDate_shutdown(resultSetNested.getDate("date_shutdown").toLocalDate());
          reactor.setID_country(resultSetNested.getInt("id_country"));
         //   reactor.setName_owner(resultSetNested.getString("name_owner"));
           // reactor.setName_operator(resultSetNested.getString("name_operator"));
            reactors.add(reactor);
           // HashMap<Integer, Double> kiumData = getKiums(reactor_ID);
           // reactorData.put(reactor_ID, kiumData);   
    }
       
        
    }
public HashMap<Integer, Double> getKiums(int reactor_ID) throws SQLException{
    String query = "SELECT k.kium_value, k.year FROM kiums k WHERE k.id_reactor = ?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setInt(1, reactor_ID);  
    ResultSet resultSet = statement.executeQuery();
    Double kium = 0.0;
    Integer year = 0;
    HashMap<Integer, Double> yearsKiums = new HashMap<>();
    int year_down = checkYear(reactor_ID);
    int year_start = checkYearLoad(reactor_ID);
    while(resultSet.next()){
        kium = resultSet.getDouble("kium_value");
        year = resultSet.getInt("year");
        yearsKiums.put(year, kium);
    }
       
  return completeYears(yearsKiums, year_down, year_start);
       
}

public int checkYear( int ID_reactor) throws SQLException{
    String query = "SELECT r.date_shutdown FROM reactors r WHERE r.id_reactor = ?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setInt(1, ID_reactor);  
    ResultSet resultSet = statement.executeQuery();
    int year_down = 0;
    while(resultSet.next()){
    String date =  resultSet.getString("date_shutdown");
        if (date != null){
            LocalDate localDate = LocalDate.parse(date);  
            year_down = localDate.getYear();

        }
     }
        return year_down;
}
public int checkYearLoad(int ID_reactor) throws SQLException{
    String query = "SELECT r.first_grid_connection FROM reactors r WHERE r.id_reactor = ?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setInt(1, ID_reactor);  
    ResultSet resultSet = statement.executeQuery();
    int year_down = 0;
     while(resultSet.next()){
    String date =  resultSet.getString("first_grid_connection");
        if (date != null){
            LocalDate localDate = LocalDate.parse(date);  
            year_down = localDate.getYear();

        }
     }
        return year_down;
}


public HashMap<Integer, Double> completeYears(HashMap<Integer, Double> set, int year_down, int yearStart){
        for (int year = 2014; year <= 2024; year++) {
            if (!set.containsKey(year)) {
                if ((year< yearStart || year>= year_down) && year_down!=0){
                    set.put(year, 0.0);
                }
                else{
                    set.put(year, 0.85);
                }
            }
        }
        // for (Integer key:set.keySet()){
    //    System.out.println( key + " " + set.get(key));
   // }
      //  System.out.println(set.keySet());
        return set;
    }
}

