/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;

import java.util.Date;

public class Telephone extends Appareil {
    private String Version_sys;
    public Telephone(String id, String Marque, Date date_fabrication,boolean vol,double RAM,double ROM , String Version_sys){
        super(id,Marque,date_fabrication,RAM,ROM,vol);
        this.Version_sys=Version_sys;
    }
   public String getVersion_sys(){
        return this.Version_sys;
    }
    public void setCPU(String Version_sys){
        this.Version_sys=Version_sys;
    }
    
}
