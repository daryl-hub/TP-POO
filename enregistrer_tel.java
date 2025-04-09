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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class enregistrer_tel extends Fenetre {

    protected JTextField idField = new JTextField();
    protected JTextField MarqueField = new JTextField();
    protected JTextField SerieField = new JTextField();
    protected JTextField dateField= new JTextField();
    protected JTextField RAMField = new JTextField();
    protected JTextField ROMField = new JTextField();

    public enregistrer_tel() {
        super("Paramètre Téléphone");
        
        JLabel idLabel = new JLabel("Identifiant:");
        JLabel MarqueLabel = new JLabel("Marque:");
        JLabel SerieLabel = new JLabel("Serie:");
        JLabel dateLabel = new JLabel("date fabrication:");
        JLabel RAMLabel = new JLabel("RAM:");
        JLabel ROMLabel = new JLabel("ROM:");

        // Placement des composants
        idLabel.setBounds(30, 30, 100, 25);
        idField.setBounds(170, 30, 150, 25);

        MarqueLabel.setBounds(30, 70, 100, 25);
        MarqueField.setBounds(170, 70, 150, 25);

        SerieLabel.setBounds(30, 110, 120, 25);
        SerieField.setBounds(170, 110, 150, 25);
        
        dateLabel.setBounds(30, 150, 120, 25);
        dateField.setBounds(170, 150, 150, 25);

        RAMLabel.setBounds(30, 190, 100, 25);
        RAMField.setBounds(170, 190, 150, 25);

        ROMLabel.setBounds(30, 230, 100, 25);
        ROMField.setBounds(170, 230, 150, 25);

        bouton.setBounds(170, 270, 150, 30); // Positionnement du bouton

        container.add(idLabel);
        container.add(idField);
        container.add(MarqueLabel);
        container.add(MarqueField);
        container.add(SerieLabel);
        container.add(SerieField);
        container.add(dateLabel);
        container.add(dateField);
        container.add(RAMLabel);
        container.add(RAMField);
        container.add(ROMLabel);
        container.add(ROMField);
        container.add(bouton);

        bouton.addActionListener(this);

        this.setVisible(true);
    }

  @Override
public void actionPerformed(ActionEvent ae) {
    String id = idField.getText().trim();
    String Marque = MarqueField.getText().trim();
    String Serie= SerieField.getText().trim();
    String RAM = RAMField.getText().trim();
    String ROM = ROMField.getText().trim();
    
    String dateStr = dateField.getText().trim();


    if (id.isEmpty() || Marque.isEmpty() || Serie.isEmpty() || RAM.isEmpty() || ROM.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false);
    Date date;
try {
    date = sdf.parse(dateStr);
} catch (ParseException e) {
    JOptionPane.showMessageDialog(this, "Format de date invalide. Utilisez yyyy-MM-dd.", "Erreur", JOptionPane.ERROR_MESSAGE);
    return;
}
    try {
        Integer.parseInt(RAM);
        Integer.parseInt(ROM);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "RAM et ROM doivent être des nombres entiers.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // ✅ Vérification si l'ID existe déjà
    if (idExisteDeja(id)) {
        JOptionPane.showMessageDialog(this, "Cet ID existe déjà dans la base.", "Erreur", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // ✅ Enregistrement dans la base de données
    try (Connection conn = ConnectioBD.getConnection()) {
        conn.setAutoCommit(false);
        String sql = "INSERT INTO Telephone (id, Marque, Serie, date_fabrication, ram, rom) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        stmt.setString(2, Marque);
        stmt.setString(3, Serie);
        stmt.setDate(4, new java.sql.Date(date.getTime()));
        stmt.setInt(5, Integer.parseInt(RAM));
        stmt.setInt(6, Integer.parseInt(ROM));
        stmt.executeUpdate(); // Exécution de l'insertion

        conn.commit(); // Commit la transaction si tout est correct

        JOptionPane.showMessageDialog(this, "Téléphone enregistré avec succès !", "Succès", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement en base.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }
}
private boolean idExisteDeja(String id) {
    try (Connection conn = ConnectioBD.getConnection()) {
        String sql = "SELECT id FROM Proprietaire natural join Telephone WHERE id = ?";
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
