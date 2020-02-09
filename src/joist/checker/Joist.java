package joist.checker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lkferreira
 */
public class Joist {
    private String model;
    private String TJI_series;
    private double depth;
    private double EI;
    private double bearingLength;
    private double momentCapacity;
    private double shearCapacity;
    private double endShearCapacity;
    private double span;
    private ConnectionDB db;
    
    public Joist() {
        this.db = new ConnectionDB();
    }
    
    public Joist(String model, double span, double bearingLength) {
        this.db = new ConnectionDB();
        this.model = model;
        this.span = span;
        this.bearingLength = bearingLength;
        
        this.setMomentCapacity();
        this.setShearCapacity();
        this.setEndShearCapacity();
        this.setTJI_series();
        this.setEI();
        this.setDepth();
        
        System.out.println(this.depth);
    }
    
    public void setModel(String model) {
        this.model = model;
        this.setMomentCapacity();
        this.setShearCapacity();
        this.setEndShearCapacity();
        this.setTJI_series();
        this.setEI();
        this.setDepth();
    }
    
    private void setMomentCapacity() {
        
        try {
            Connection cn = db.getConnection();
            Statement stmt = null;
            String query = "SELECT moment\n" +
                "FROM JOIST_TABLE\n" +
                "WHERE name = '" + this.model + "'";
            stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.momentCapacity = rs.getInt("MOMENT");
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setShearCapacity() {
        try {
            Connection cn = db.getConnection();
            Statement stmt = null;
            String query = "SELECT shear\n" +
                "FROM JOIST_TABLE\n" +
                "WHERE name = '" + this.model + "'";
            stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.shearCapacity = rs.getInt("shear");
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setEndShearCapacity() {
        try {
            Connection cn = db.getConnection();
            Statement stmt = null;
            
            String column;
            if (this.bearingLength == 1.75) {
                column = "SHORT_ENDREACTION";
            } else if (bearingLength == 3.5) {
                column = "LONG_ENDREACTION";
            } else {
                throw new IllegalStateException();
            }
            
            String query = "SELECT " + column + "\n" +
                "FROM JOIST_TABLE\n" +
                "WHERE name = '" + this.model + "'";
            stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.endShearCapacity = rs.getInt(column);
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setTJI_series() {
        try {
            Connection cn = db.getConnection();
            Statement stmt = null;
            
            String query = "SELECT TJI \n" +
                "FROM JOIST_TABLE\n" +
                "WHERE name = '" + this.model + "'";
            stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.TJI_series = rs.getString("TJI");
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setDepth() {
        try {
            Connection cn = db.getConnection();
            Statement stmt = null;
            
            String query = "SELECT DEPTH \n" +
                "FROM JOIST_TABLE\n" +
                "WHERE name = '" + this.model + "'";
            stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.depth = rs.getDouble("DEPTH");
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    private void setEI() {
        try {
            Connection cn = db.getConnection();
            Statement stmt = null;
            
            String query = "SELECT EI \n" +
                "FROM JOIST_TABLE\n" +
                "WHERE name = '" + this.model + "'";
            stmt = cn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.EI = rs.getDouble("EI");
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        }
    }
    
    public void setBearingLength(double length) {
        this.bearingLength = length;
    }
    
    public void setSpan(double span) {
        this.span = span;
    }
    
    public String getModelName() {
        return this.model;
    }
    
    public double getMomentCapacity() {
        return this.momentCapacity;
    }
    
    public double getShearCapacity() {
        return this.shearCapacity;
    }
    
    public double getEndShearCapacity() {
        return this.endShearCapacity;
    }
    
    public String getTJI_series() {
        return this.TJI_series;
    }
    
    public double getDepth() {
        return this.depth;
    }
    
    public double getEI() {
        return this.EI;
    }
    
    public double getSpan() {
        return this.span;
    }
    
}
