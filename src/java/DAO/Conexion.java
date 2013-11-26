/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author fernando
 */
public class Conexion { 
   private static final String DRIVER = "org.postgresql.Driver";   
   private static final String URL = "jdbc:postgresql://ec2-54-204-20-28.compute-1.amazonaws.com:5432/ddqv5h4lohc339?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";   
   private static final String USERNAME = "hoqxbrlxdjueau";   
   private static final String PASSWORD ="p74ONV0NKa4pTA6H-cgfNqS5xU";   
   public static Connection getConnection() throws SQLException {   
    try {   
     Class.forName(DRIVER);   
    } catch (ClassNotFoundException ex) {   
     System.out.println("Where is your PostgreSQL JDBC Driver? "   
       + "Include in your library path!");   
     return null;   
    }   
    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);   
    return conn;   
   }  
    
  }
