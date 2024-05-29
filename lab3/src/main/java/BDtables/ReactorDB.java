/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDtables;

import java.time.LocalDate;

/**
 *
 * @author User
 */
public class ReactorDB {
    private int ID_reactor;
    private String reactor_name;
    private String reactor_type;
    private String model;
    private String status;
    private int termal_capacity;
    private LocalDate first_grid_connection;
    private LocalDate date_shutdown;
    private String name_owner;
    private String name_operator;
    private int ID_country;
    private double burnup;
    private int first_load;

public int getID(){
        return ID_reactor;
} 
public void setID(int id){
      this.ID_reactor = id;
  }
public String getName(){
    return reactor_name;
}
public void setName(String name){
    this.reactor_name = name ;
}
public String getType(){
    return reactor_type;
}
public void setType(String type){
    this.reactor_type = type;
}
public String getModel(){
    return model;
}
public void setModel(String model){
    this.model = model;
}
public String getStatus(){
    return status;
}
public void setStatus(String status){
    this.status = status;
}
public int getTermal_capacity(){
    return termal_capacity;
}
public void setTermal_capacity(int termal_capacity){
    this.termal_capacity = termal_capacity;
}
public LocalDate getFirst_grid_connection(){
    return  first_grid_connection;
}
public void setFirst_grid_connection(LocalDate first_grid_connection){
    this.first_grid_connection = first_grid_connection;
}
public LocalDate getDate_shutdown(){
    return date_shutdown;
}
public void setDate_shutdown(LocalDate date_shutdown){
    this.date_shutdown = date_shutdown;
}
public String getName_owner(){
    return name_owner;
}
public void setName_owner(String name_owner){
    this.name_owner = name_owner;
}
public String getName_operator(){
    return name_operator;
}
public void setName_operator(String name_operator){
    this.name_operator = name_operator;
}
public int getId_Country(){
    return ID_country;
}
public void setID_country(int ID_country){
    this.ID_country = ID_country;
}
public double getBurnup(){
    return burnup;
}
public void setBurnup(double burnup){
    this.burnup = burnup;
}
public int getFirst_load(){
    return first_load;
}
public void setFirst_load(int first_load){
    this.first_load = first_load;
}
}
   
   
 