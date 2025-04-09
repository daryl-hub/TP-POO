package com.mycompany.tp2_poo2;
import javax.swing.*;
import java.awt.event.*;

public class Fenetre extends JFrame implements ActionListener {
    
    protected JButton bouton = new JButton("Enregistrer");
    protected JPanel container = new JPanel();
    protected JLabel label = new JLabel("Bienvenu");
    public Fenetre(String nom_fenetre){
        this.setTitle(nom_fenetre);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        container.setLayout(null); // Positionnement libre
        this.setContentPane(container);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        }
}
