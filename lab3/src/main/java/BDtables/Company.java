/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BDtables;

/**
 *
 * @author User
 */
public class Company {
    private int id_company;
    private String company_name;
    
public void setIDCompany(int id){
    this.id_company = id;
}
public int getIDCompany(){
    return id_company;
}
public void setCompanyName(String name){
    this.company_name = name;
}
public String getCompanyName(){
    return company_name;
}
}
