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
public ArrayList<Region> SQLRegionsReader() throws SQLException{
    String queryNested = "SELECT id_region, region_name FROM regions reg";
    PreparedStatement statementNested = con.prepareStatement(queryNested);
    ResultSet resultSetNested = statementNested.executeQuery();
    ArrayList<Region> regions = new ArrayList<>();

     while(resultSetNested.next()){
            Region region = new Region();
            region.setIDRegion(resultSetNested.getInt("id_region"));
            region.setRegionName(resultSetNested.getString("region_name"));
            region.setCountriesByRegion(SQLCountryReader(resultSetNested.getInt("id_region")));
            regions.add(region);
    }
     return regions;
}
public ArrayList<Country> SQLCountryReader(int id_region) throws SQLException{
       String query = "SELECT id_country, country_name FROM countries WHERE id_region = ?";
        PreparedStatement statement = con.prepareStatement(query);
        statement.setInt(1, id_region);  
       ResultSet resultSet = statement.executeQuery();
       ArrayList<Country> countries = new ArrayList<>();
         while(resultSet.next()){
            Country country = new Country();
            country.setIDCountry(resultSet.getInt("id_country"));
            country.setCountryName(resultSet.getString("country_name"));
            countries.add(country);
            country.setReactorsByCountry(SQLReactorsReader(country));
    } 
     return countries;
}
public ArrayList<Company> SQLCompanyReader(ArrayList<ReactorDB> readyReactors) throws SQLException{
      String query = "SELECT id_company, company_name FROM companies";
        PreparedStatement statement = con.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Company> companies = new ArrayList<>();
         while(resultSet.next()){
            Company company = new Company();
            company.setIDCompany(resultSet.getInt("id_company"));
            company.setCompanyName(resultSet.getString("company_name"));
            company.setReactorsByCompany(addReactors(company,readyReactors));
            companies.add(company);
            System.out.println("компания добавлена");
    } 
        return companies;
    
}
 public ArrayList<ReactorDB> SQLReactorsReader(Country country) throws SQLException{
        String queryNested = "SELECT id_reactor,reactor_name, reactor_type, model, status, thermal_capacity,first_grid_connection,date_shutdown, id_country, id_owner, id_operator FROM reactors c WHERE id_country = ?";
        PreparedStatement statementNested = con.prepareStatement(queryNested);
        statementNested.setInt(1, country.getIDCountry());  
        ResultSet resultSetNested = statementNested.executeQuery();
        ArrayList<ReactorDB> reactors = new ArrayList<>();
        while(resultSetNested.next()){
            ReactorDB reactor = new ReactorDB();
            reactor.setID(resultSetNested.getInt("id_reactor"));
            reactor.setID_Owner(resultSetNested.getInt("id_owner"));
            reactor.setID_Operator(resultSetNested.getInt("id_operator"));
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
            reactor.setKiumsByReactor(getKiums(reactor));
            matchReactorsType(reactor);
            reactors.add(reactor);
    }
      return reactors;  
    }
 public HashMap<String, ArrayList<ReactorDB>> addReactors(Company company, ArrayList<ReactorDB> reactors){
     HashMap<String, ArrayList<ReactorDB>> reactorsByCompany = new HashMap<>();
     ArrayList<ReactorDB> listByOperators = new ArrayList<>();
     ArrayList<ReactorDB> listByOwners = new ArrayList<>();
      for (ReactorDB reactor: reactors){
          if (company.getIDCompany() == reactor.getID_Operator()){
             reactor.setName_operator(company.getCompanyName());
             listByOperators.add(reactor);
             
          }
          if(company.getIDCompany() == reactor.getID_Owner()){
              reactor.setName_owner(company.getCompanyName());
              listByOwners.add(reactor);
          }     
      }
      reactorsByCompany.put("По операторам", listByOperators);
      reactorsByCompany.put("По владельцам", listByOwners);
      
        return reactorsByCompany;
     
 }
public ArrayList<KIUM> getKiums(ReactorDB reactor) throws SQLException{
    String query = "SELECT k.kium_value, k.year FROM kiums k WHERE k.id_reactor = ?";
    PreparedStatement statement = con.prepareStatement(query);
    statement.setInt(1, reactor.getID());  
    ResultSet resultSet = statement.executeQuery();
    Integer year_down = 0;
    Integer year_start = 0;
    HashMap<Integer,Double> kiumsByReactor = new HashMap<>();
    if (reactor.getDate_shutdown()!=null){
    year_down = reactor.getDate_shutdown().getYear();
    }
    year_start = reactor.getFirst_grid_connection().getYear();
    while(resultSet.next()){
        kiumsByReactor.put(resultSet.getInt("year"),resultSet.getDouble("kium_value"));
    }
       
  return completeYears(kiumsByReactor, year_down, year_start);
       
}
public ArrayList<KIUM> completeYears(HashMap<Integer,Double> map, int year_down, int yearStart){
    ArrayList<KIUM> completeKiums = new ArrayList<>();
        for (int year = 2014; year <= 2024; year++) {
            KIUM newKium = new KIUM();
            if (!map.containsKey(year)) {    
                if (year< yearStart || (year>= year_down && year_down!=0)){
                    newKium.setYear(year);
                    newKium.setKium(0.0);
                }
                else{
                    newKium.setYear(year);
                    newKium.setKium(0.85);
                }
                
            }
            else{
                newKium.setYear(year);
                newKium.setKium(map.get(year)*0.01);
               
            }
            completeKiums.add(newKium);
        }
      return completeKiums;
    }
public void matchReactorsType(ReactorDB reactor){
   String reactor_type = reactor.getType();
    switch(reactor_type){
        case "LWGR" -> reactor.setType("RBMK");
        case "GCR" -> reactor.setType("MAGNOX");
        case "FBR" -> reactor.setType("BN");
        }
    }


}


