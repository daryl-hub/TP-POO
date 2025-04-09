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
public class Proprietaire {
    private String nom;
    private String adress;
    private String email;
    private String telNumber;
    public Proprietaire(String nom,String adress,String email,String telNumber)
    {
        this.adress=adress;
        this.email=email;
        this.nom=nom;
        this.telNumber=telNumber;
    }
     public String getadress()
    {
        return this.adress;
    }
    public void setadress(String adress)
    {
        this.adress=adress;
    }
    public String getnom()
    {
        return this.nom;
    }
    public void setnom(String nom)
    {
        this.nom=nom;
    }
    public String getemail()
    {
        return this.email;
    }
    public void setemail(String email)
    {
        this.email=email;
    }
    public String gettelNumber()
    {
        return this.telNumber;
    }
    public void settelNumber(String telNumber)
    {
        this.telNumber=telNumber;
    }
}
