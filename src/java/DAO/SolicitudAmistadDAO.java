/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import negocio.SolicitudAmistad;

/**
 *
 * @author fernando
 */
public class SolicitudAmistadDAO {
    
     public boolean agregarUsuario(SolicitudAmistad solicitud) {
        Connection con = null;
        boolean resultado=true;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            
            con = Conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO solicitud_amigo(ID_SOLICITANTE,ID_SOLICITADO,STATUS_SOLICITANTE,STATUS_SOLICITADO)"
                    + " VALUES(?,?,?,?)");//10
            
            ps.setInt(1, solicitud.getId_solicitante());
            ps.setInt(2, solicitud.getId_solicitado());
            ps.setInt(3, solicitud.getStatus_solicitante());
            ps.setInt(4, solicitud.getStatus_solicitado());
            ps.executeUpdate();
            resultado=true;
          //  con.commit();
             
        } catch (SQLException ex ) {
             resultado=false;
            // java.util.logging.Logger.getLogger(carrito_DAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
              
               
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                // Logger.getLogger(temaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultado;
       
    }
    
    
    
}
