package Readers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Reactor {
    private String className;
    private double burnup;
    private double kpd;
    private double enrichment;
    private double termal_capacity;
    private double electrical_capacity;
    private double life_time;
    private double first_load;
    
    public void setClassName(String name){
        this.className = name;    
}
    public void setBurnup(double burnup){
        this.burnup = burnup;    
}
    public void setKpd(double kpd){
        this.kpd = kpd;
    }
    public void setEnrichment( double enrichment){
        this.enrichment = enrichment;
    }
    public void setTermalCapacity(double termal_capasity){
        this.termal_capacity = termal_capasity;
    }
    public void setElectricalCapacity(double electrical_capacity){
        this.electrical_capacity = electrical_capacity;
    }
    public void setLifeTime(double life_time){
        this.life_time = life_time;
    }
    public void setFirstLoad(double first_load){
        this.first_load = first_load;
    }

    
}
