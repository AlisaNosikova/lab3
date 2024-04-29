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
    private String source;
  
    public String getClassName() {
        return className;
    }
  
    public void setClassName(String className) {
        this.className = className;
    }
  
    public double getBurnup() {
        return burnup;
    }
  
    public void setBurnup(double burnup) {
        this.burnup = burnup;
    }
  
    public double getKpd() {
        return kpd;
    }
  
    public void setKpd(double kpd) {
        this.kpd = kpd;
    }
  
    public double getEnrichment() {
        return enrichment;
    }
  
    public void setEnrichment(double enrichment) {
        this.enrichment = enrichment;
    }
  
    public double getTermal_capacity() {
        return termal_capacity;
    }
  
    public void setTermal_capacity(double termalCapacity) {
        this.termal_capacity = termalCapacity;
    }
  
    public double getElectrical_capacity() {
        return electrical_capacity;
    }
  
    public void setElectrical_capacity(double electricalCapacity) {
        this.electrical_capacity = electricalCapacity;
    }
  
    public double getLife_time() {
        return life_time;
    }
  
    public void setLife_time(double lifeTime) {
        this.life_time = lifeTime;
    }
  
    public double getFirst_load() {
        return first_load;
    }
  
    public void setFirst_load(double firstLoad) {
        this.first_load = firstLoad;
    }
    public void setSource(String source){
        this.source = source;
    }
    public String getSource(){
        return source;
    }
}
