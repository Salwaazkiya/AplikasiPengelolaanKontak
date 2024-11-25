import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class KontakCRUD {
    public void addKontak(String nama, String nomor, String kategori) throws SQLException {
        String query = "INSERT INTO kontak (nama, nomor, kategori) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, nomor);
            stmt.setString(3, kategori);
            stmt.executeUpdate();
        }
    }
    
    public List<String[]> getAllKontak() throws SQLException {
        String query = "SELECT * FROM kontak";
        List<String[]> kontak = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                kontak.add(new String[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("nomor"),
                    rs.getString("kategori")
                });
            }
        }
        return kontak;
    }
    
    public List<String[]> cariKontak(String keyword) throws SQLException {
        String query = "SELECT * FROM kontak WHERE nama LIKE ? OR nomor LIKE ?";
        List<String[]> kontak = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                kontak.add(new String[]{
                    rs.getString("id"),
                    rs.getString("nama"),
                    rs.getString("nomor"),
                    rs.getString("kategori")
                });
            }
        }
        return kontak;
    }
    
    public void editKontak(int id, String nama, String nomor, String kategori) throws SQLException {
        String query = "UPDATE kontak SET nama = ?, nomor = ?, kategori = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nama);
            stmt.setString(2, nomor);
            stmt.setString(3, kategori);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }
    
    public void hapusKontak(int id) throws SQLException {
        String query = "DELETE FROM kontak WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public void eksporData(String filePath) throws SQLException, IOException {
        List<String[]> kontak = getAllKontak();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Nama,Nomor,Kategori\n");
            for (String[] contact : kontak) {
                writer.write(String.join(",", contact) + "\n");
            }
        }
    }
    
    public void imporData(String filePath) throws SQLException, IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 3) {
                    addKontak(fields[0], fields[1], fields[2]);
                }
            }
        }
    }
    
}
