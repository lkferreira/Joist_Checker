package joist.checker;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author lkferreira
 */
public class ConnectionDB {
    public static void main(String[] args) {
        
    }
    
    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            Connection cn = DriverManager.getConnection("jdbc:h2:./database/myDB;IFEXISTS=TRUE", "admin", "");
            System.out.println("Connected");
            return cn;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Database Connection Error \n" + ex);
            return null;
        }
    }
}
