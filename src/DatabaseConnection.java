import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:kontak.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver SQLite tidak ditemukan!", e);
        }
    }

    public static Connection connect() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Koneksi berhasil ke database: " + URL);
            return conn;
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
            throw e;
        }
    }
}
