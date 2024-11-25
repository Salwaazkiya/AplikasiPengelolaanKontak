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
    private static final String URL = "jdbc:sqlite:C:\\Users\\USER\\Documents\\NetBeansProjects\\AplikasiPengelolaanKontak\\src\\kontak.db";
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
