/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tp2_poo2;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class enregistrer_prop extends Fenetre{
    protected JTextField nomField = new JTextField();
    protected JTextField adressField = new JTextField();
    protected JTextField emailField = new JTextField();
    protected JTextField telNumberField = new JTextField();
    protected JTextField idField = new JTextField();
    protected JTextField MarqueField = new JTextField();
    protected JTextField SerieField = new JTextField();
    public enregistrer_prop(){
        super("informations proriétaire");
        JLabel nomLabel = new JLabel("nom:");
        JLabel adressLabel = new JLabel("Adresse:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel telNumberLabel = new JLabel("N° Tel:");
        JLabel idLabel = new JLabel("Identifiant Telephone:");
        JLabel MarqueLabel = new JLabel("Marque Telephone:");
        JLabel SerieLabel = new JLabel("Serie Telephone:");
        
        // Placement des composants
        nomLabel.setBounds(50, 30, 100, 25);
        nomField.setBounds(200, 30, 150, 25);

        adressLabel.setBounds(50, 70, 100, 25);
        adressField.setBounds(200, 70, 150, 25);

        emailLabel.setBounds(50, 110, 100, 25);
        emailField.setBounds(200, 110, 150, 25);

        telNumberLabel.setBounds(50, 150, 100, 25);
        telNumberField.setBounds(200, 150, 150, 25);

        idLabel.setBounds(50, 190, 100, 25);
        idField.setBounds(200, 190, 150, 25);

        MarqueLabel.setBounds(50, 230, 100, 50);
        MarqueField.setBounds(200, 230, 150, 25);

        SerieLabel.setBounds(50, 270, 120,50);
        SerieField.setBounds(200, 270, 150, 25);

        bouton.setBounds(200, 310, 150, 30);
        
        container.add(nomLabel);
        container.add(nomField);
        container.add(adressLabel);
        container.add(adressField);
        container.add(emailLabel);
        container.add(emailField);
        container.add(telNumberLabel);
        container.add(telNumberField);
        container.add(idLabel);
        container.add(idField);
        container.add(MarqueLabel);
        container.add(MarqueField);
        container.add(SerieLabel);
        container.add(SerieField);
        container.add(bouton);

        bouton.addActionListener(this);

        this.setVisible(true);
    }
     @Override
public void actionPerformed(ActionEvent ae) {
    String nom = nomField.getText().trim();
    String adress = adressField.getText().trim();
    String email = emailField.getText().trim();
    String telNumber = telNumberField.getText().trim();
    String id = idField.getText().trim();
    String Marque = MarqueField.getText().trim();
    String Serie = SerieField.getText().trim();

    // Vérification si les champs sont vides
    if (nom.isEmpty() || adress.isEmpty() || email.isEmpty() || telNumber.isEmpty() || id.isEmpty() || Marque.isEmpty() || Serie.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Vérification si l'ID existe déjà avant d'insérer
    if (idExisteDeja(id)) {
        JOptionPane.showMessageDialog(this, "Cet ID existe déjà dans la base.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try (Connection conn = ConnectioBD.getConnection()) {
        conn.setAutoCommit(false); // pour rollback en cas d'erreur

        // 1. Insertion du propriétaire
        String insertProp = "INSERT INTO Proprietaire (nom, adress, email, telNumber, id, Marque, Serie) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmtProp = conn.prepareStatement(insertProp, Statement.RETURN_GENERATED_KEYS);
        stmtProp.setString(1, nom);
        stmtProp.setString(2, adress);
        stmtProp.setString(3, email);
        stmtProp.setString(4, telNumber);
        stmtProp.setString(5, id);
        stmtProp.setString(6, Marque);
        stmtProp.setString(7, Serie);
        
        int rowsAffected = stmtProp.executeUpdate();

        if (rowsAffected > 0) {
            conn.commit(); // Validation de la transaction si l'insertion a réussi
            JOptionPane.showMessageDialog(this, "Enregistrement réussi.", "Succès", JOptionPane.INFORMATION_MESSAGE);
        } else {
            conn.rollback(); // Annulation de la transaction si l'insertion échoue
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement en base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement en base de données.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}

public boolean idExisteDeja(String id) {
    try (Connection conn = ConnectioBD.getConnection()) {
        String sql = "SELECT id FROM Proprietaire WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();
        return rs.next(); // Retourne true si l'ID existe
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}



}
