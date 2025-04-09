/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;

import java.util.Date;

/**
 *
 * @author daryl
 */
public  class Appareil {
    
    private String id;
    private String Marque;
    private Date date_fabrication;
    private double RAM;
    private double ROM;
    private boolean Vol;
    public Appareil(String id,String Marque,Date date_fabrication,double RAM,double ROM,boolean vol){
        this.id=id;
        this.Marque=Marque;
        this.date_fabrication= date_fabrication;
        this.RAM=RAM;
        this.ROM=ROM;
        this.Vol=vol;
    }
    public String getid(){
      return this.id;  
    }
     public String getMarque(){
      return this.Marque;  
    }
     public Date getDate(){
      return this.date_fabrication;  
    }
    public boolean getvol(){
      return this.Vol;  
    }
    public void setid(String id){
        this.id=id;
    }
    public void setMarque(String Marque){
        this.Marque= Marque;
    }
    public void setDate(Date date){
        this.date_fabrication=date;
    }
    public void setVol(boolean vol){
        this.Vol=vol;
    }
     public double getRAM(){
        return this.RAM;
    }
    public double getROM(){
        return this.ROM;
    }
    public void setRAM(double RAM){
        this.RAM=RAM;
    }
    public void setROM(double ROM){
        this.ROM=ROM;
    }
    
}
