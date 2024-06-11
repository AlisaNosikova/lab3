/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Readers;

import excelProvider.ExcelReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 *
 * @author User
 */
public class DBCreator {
     Connection connection = null;
    public void createDB() throws SQLException{
    String url = "jdbc:postgresql://aws-0-us-west-1.pooler.supabase.com:6543/postgres";
    String username = "postgres.wpmfalbkqugwqcvqsxmk";
    String password = "aa23082003aab@";
      
    connection = DriverManager.getConnection(url, username, password);
 //   excelReader.readExcel(connection, "countries");
    
//createTables(connection);
 //insertTables(connection, "regions");
 //insertTables(connection, "countries");
//insertTables(connection, "companies");
 //insertTables(connection, "reactors");
 //insertTables(connection, "kiums");
    // System.out.println(excelReader.readExcel(connection));
//dropTables("kiums", connection);
//dropTables("reactors", connection);
//dropTables("countries", connection);
//dropTables("companies", connection);
//dropTables("regions", connection);
 
  if (connection!=null){
      System.out.println("Успешное подключение к базе");
   }
    }
      
    
   public Connection getConnection(){
       return connection;
   }
   public static void createTables(Connection con) throws SQLException {
       Statement statement = con.createStatement();
       String regions = "CREATE TABLE regions " 
                    + "(ID_region INTEGER PRIMARY KEY, " 
                    + "region_name VARCHAR(28) NOT NULL)";
    statement.executeUpdate(regions);
    
    
    String countries = "CREATE TABLE countries " 
                        + "(ID_country INTEGER PRIMARY KEY, " 
                    + "country_name VARCHAR(24) NOT NULL, " 
                    + "ID_region INTEGER NOT NULL, " 
                    + "FOREIGN KEY (ID_region) REFERENCES regions(ID_region))";
    statement.executeUpdate(countries);

    String companies = "CREATE TABLE companies " 
                    + "(ID_company INTEGER PRIMARY KEY, " 
                    + "company_name VARCHAR(80) NOT NULL)";
    statement.executeUpdate(companies);
    String reactors = "CREATE TABLE reactors " 
                    + "(ID_reactor INTEGER PRIMARY KEY, " 
                    + "reactor_name VARCHAR(25) NOT NULL, "
                    + "reactor_type VARCHAR(4) NOT NULL, " 
                    + "model VARCHAR(35) NOT NULL, " 
                    + "status VARCHAR(25) NOT NULL, " 
                    + "thermal_capacity INTEGER NOT NULL, " 
                    + "first_grid_connection DATE NOT NULL, " 
                    + "date_shutdown DATE, " 
                    + "ID_owner INTEGER NOT NULL, " 
                    + "ID_operator INTEGER NOT NULL, " 
                    + "ID_country INTEGER NOT NULL, " 
                    + "FOREIGN KEY (ID_country) REFERENCES countries(ID_country), " 
                    + "FOREIGN KEY (ID_owner) REFERENCES companies(ID_company), " 
                    + "FOREIGN KEY (ID_operator) REFERENCES companies(ID_company))";
    statement.executeUpdate(reactors);
    
    String kiums = "CREATE TABLE kiums " 
            + "(ID_kium INTEGER PRIMARY KEY, "
            + "kium_value FLOAT NOT NULL, "
            + "year INTEGER NOT NULL, "
            + "ID_reactor INTEGER NOT NULL, "
            + "FOREIGN KEY (ID_reactor) REFERENCES reactors(ID_reactor))";
      statement.executeUpdate(kiums);
    System.out.println("Table created successfully!");
}
   public static void dropTables(String tableName, Connection con) throws SQLException{
         Statement statement = con.createStatement();
         statement.executeUpdate("DROP TABLE " + tableName);
   }
   
public static void insertTables(Connection connection, String tableName) throws SQLException, IOException {
    ExcelReader reader = new ExcelReader();
    HashMap<Integer, ArrayList<Object>> list = reader.readExcel(connection, tableName);
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
    ResultSetMetaData rsmd = rs.getMetaData();
     PreparedStatement pstmt = null;
    String insertQuery = generateInsertQuery(connection, tableName,  stmt);
     pstmt = connection.prepareStatement(insertQuery);
    for (ArrayList<Object> values : list.values()) {
       // System.out.println(values.get(0));
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) == null) {
                pstmt.setNull(i+1, rsmd.getColumnType(i+1));
            } else {
             //   System.out.println(values.get(i));
              pstmt.setObject(i+1, values.get(i), rsmd.getColumnType(i+1));
            }
        }
        pstmt.addBatch();
    }
    
    pstmt.executeBatch();
    pstmt.close();
    rs.close();
    stmt.close();
}

private static String generateInsertQuery(Connection connection, String tableName,  Statement stmt) throws SQLException {
    ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
    ResultSetMetaData rsmd = rs.getMetaData();
    
    StringJoiner columnNames = new StringJoiner(",");
    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
        columnNames.add(rsmd.getColumnName(i));
    }
    
    String insertQuery = "INSERT INTO " + tableName + " (" + columnNames.toString() + ") VALUES (" + "?,".repeat(rsmd.getColumnCount() - 1) + "?)";
 
    
    return insertQuery;
}

}
