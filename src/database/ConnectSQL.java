/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author DELL
 */
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectSQL {
    public static Connection DBConnection(){
        String url = "jdbc:sqlserver://localhost:1433;databasename=finance;encrypt=false;username=sa;password=123456";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
