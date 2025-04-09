/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author daryl
 */
public class ConnectioBD  {
    public static Connection getConnection() {
        try {
            // Chargement du driver MySQL (optionnel à partir de JDBC 4.0)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Paramètres de connexion
            String url = "jdbc:mysql://localhost:3306/TP2_POO2";
            String username = "root";
            String password = "da13ryl03";

            // Connexion à la base de données
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}


