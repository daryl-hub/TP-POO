/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author daryl
 */
public class Rechercher extends JFrame implements ActionListener{
    protected JTextField idField = new JTextField();
    protected JTextField MarqueField = new JTextField();
    protected JTextField SerieField = new JTextField();
    protected JButton btn = new JButton("Rechercher");
    public Rechercher(){
        this.setTitle("Paramètres téléphone (Acheteur)");
        this.setSize(400, 250);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null); // Pas de layout manager, on utilise setBounds

        // Labels et champs
        JLabel idLabel = new JLabel("Identifiant :");
        idLabel.setBounds(30, 30, 100, 25);
        idField.setBounds(150, 30, 200, 25);

        JLabel MarqueLabel = new JLabel("Marque :");
        MarqueLabel.setBounds(30, 70, 100, 25);
        MarqueField.setBounds(150, 70, 200, 25);

        JLabel SerieLabel = new JLabel("Série :");
        SerieLabel.setBounds(30, 110, 100, 25);
        SerieField.setBounds(150, 110, 200, 25);

        btn.setBounds(150, 160, 120, 30);
        btn.addActionListener(this);

        // Ajout des composants
        add(idLabel);
        add(idField);
        add(MarqueLabel);
        add(MarqueField);
        add(SerieLabel);
        add(SerieField);
        add(btn);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String id = idField.getText().trim();
        String marque = MarqueField.getText().trim();
        String serie = SerieField.getText().trim();

        if (id.isEmpty() || marque.isEmpty() || serie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (idExisteDeja(id)) {
            JOptionPane.showMessageDialog(this, "Attention Téléphone Volé", "Téléphone Volé", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Aucun téléphone trouvé avec cet ID. Vous pouvez procéder à l'achat.");
        }
    }

    private boolean idExisteDeja(String id) {
        try (Connection conn = ConnectioBD.getConnection()) {
            String sql = "SELECT id FROM Proprietaire WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true si l'ID existe déjà
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
