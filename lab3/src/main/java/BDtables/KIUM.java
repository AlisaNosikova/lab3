/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDtables;

/**
 *
 * @author User
 */
public class KIUM {
    private int id_kium;
    private double kium;
    private int year;
    private int id_reactor;
public void setID_kium(int id){
    this.id_kium = id;
} 
public int getID_kium(){
    return id_kium;
}
public void setKium(double kium){
    this.kium = kium;
}
public double getKium(){
    return kium;
}
public void setYear(int year){
    this.year = year;
}
public int getYear(){
    return year;
}
public void setIDReactor(int id){
    this.id_reactor  = id;
}
public int getIDReactor(){
    return id_reactor;
}
}
