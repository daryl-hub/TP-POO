/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;

import java.util.Date;

public class Laptop extends Appareil{
   
    private double CPU;
    public Laptop(String id, String Marque, Date date_fabrication,boolean vol,double RAM,double ROM,double CPU){
        super(id,Marque,date_fabrication,RAM,ROM,vol);
        this.CPU=CPU;
    }
    public double getCPU(){
        return this.CPU;
    }
    public void setCPU(double ROM){
        this.CPU=CPU;
    }
    
 }

