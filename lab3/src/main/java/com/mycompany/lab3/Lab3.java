/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.lab3;

import BDtables.Country;
import BDtables.Region;
import Readers.Manager;
import excelProvider.ExcelReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 *
 * @author User
 */
public class Lab3 {

    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException {
    // Frame frame = new Frame("Реакторы");
    Manager manager = new Manager();
    manager.createDB();
    manager.loadInfo();
    for (Region region: manager.getInfoDB()){
        System.out.println(region.getRegionName());
     for (Country country: region.getCountriesByRegion()){
         System.out.println(country.getCountryName());
     }
   }
}
}


