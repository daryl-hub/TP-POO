/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Fenetre_principale extends JFrame {

    public Fenetre_principale() {
        setTitle("Bienvenue dans notre application de vente de téléphone !");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton bouton1 = new JButton("Rechercher Etat Appareil");
        //JButton bouton2 = new JButton("Acheter votre Téléphone");
        JButton statutButton = new JButton("Statut");

        panel.add(bouton1);
        //panel.add(bouton2);
        panel.add(statutButton);

        add(panel);

        // Actions
        bouton1.addActionListener(e -> new Rechercher().setVisible(true));
       // bouton2.addActionListener(e -> new enregistrer_prop().setVisible(true));
        statutButton.addActionListener(e -> afficherChoixStatut());

        setVisible(true);
    }

    public void afficherChoixStatut() {
        String[] options = {"Vendeur", "propriétaire"};
        int choix = JOptionPane.showOptionDialog(
                this,
                "Choisissez votre statut :",
                "Statut utilisateur",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choix == 0) {
            new enregistrer_tel().setVisible(true);
        } else if (choix == 1) {
            new enregistrer_prop().setVisible(true);
        }
    }
}
