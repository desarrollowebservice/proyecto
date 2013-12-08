/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.SolicitudAmistad;
import negocio.Usuario;

/**
 *
 * @author fernando
 */
public class SolicitudAmistadDAO {
    
    
    
  
     public boolean agregarUsuario(SolicitudAmistad solicitud) {
         Connection con= null;
         Connection con2 = null;
         Connection con3 = null;
          Connection con4 = null;
          Connection con5 = null;
        boolean resultado=true;
       PreparedStatement ps = null; 
         PreparedStatement ps2 = null;
         PreparedStatement ps3 = null;
           PreparedStatement ps4 = null;
           PreparedStatement ps5 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
         ResultSet rs4 = null;
          ResultSet rs5 = null;
        try {
            
            // validamos que el amigo ya lo haya solicitado a el
               con3 = Conexion.getConnection();
            ps3 = con3.prepareStatement("select * from solicitud_amigo where id_solicitante=? and id_solicitado=?");
            ps3.setInt(2, solicitud.getId_solicitante());
            ps3.setInt(1, solicitud.getId_solicitado());
            rs3= ps3.executeQuery();
            
            //validamos que el usuario ya no alla hecho la validacion
             con2 = Conexion.getConnection();
            ps2 = con2.prepareStatement("select * from solicitud_amigo where id_solicitante=? and id_solicitado=?");
            ps2.setInt(1, solicitud.getId_solicitante());
            ps2.setInt(2, solicitud.getId_solicitado());
            rs2= ps2.executeQuery();
             
             //validamos que ya no lo tengas como amigo
                 con4 = Conexion.getConnection();
            ps4 = con4.prepareStatement("select * from usuario_amigo where  id_usuario=? and id_amigo=?");
            ps4.setInt(1, solicitud.getId_solicitante());
            ps4.setInt(2, solicitud.getId_solicitado());
            rs4= ps4.executeQuery();
            
            //validemos que el no te tenga como amigo
            con5 = Conexion.getConnection();
            ps5 = con5.prepareStatement("select * from usuario_amigo where  id_usuario=? and id_amigo=?");
            ps5.setInt(2, solicitud.getId_solicitante());
            ps5.setInt(1, solicitud.getId_solicitado());
            rs5= ps5.executeQuery();
            
             
            if ( rs2.getRow() == 0 ){
                if (rs3.getRow() == 0 && !rs3.next() ){
                     if (rs4.getRow() == 0 && !rs4.next() ){
                          if (rs5.getRow() == 0 && !rs5.next() ){
            con = Conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO solicitud_amigo( id_solicitado,status_solicitante,id_solicitante,status_solicitado )"
                    + " VALUES(?,?,?,?)");//10
            
         
            ps.setInt(1, solicitud.getId_solicitado());
            ps.setInt(2, solicitud.getStatus_solicitante());
            ps.setInt(3, solicitud.getId_solicitante());
            ps.setInt(4, solicitud.getStatus_solicitado());
            ps.executeUpdate();
            resultado=true;
                          } else{
             resultado=false;
            }
                     }
                     else{
             resultado=false;
            }
            }
             else{
             resultado=false;
            }
            }
            else{
             resultado=false;
            }
           
         
             
        } catch (SQLException ex ) {
             resultado=false;
               Logger.getLogger(SolicitudAmistadDAO.class.getName()).log(Level.SEVERE, null, ex);
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
     
     
     
      public boolean ConfirmarSolicituds(int id_solicitante,int id_solicitado) {
        Connection con = null;
        boolean resultado=true;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            
            con = Conexion.getConnection();
            ps = con.prepareStatement("UPDATE solicitud_amigo SET status_solicitado=1 WHERE id_solicitante=? and id_solicitado=?");//10
            
            ps.setInt(2, id_solicitante);
            ps.setInt(1, id_solicitado);
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
      
      
      public boolean InsertarAmigoConf(int id_solicitante,int id_solicitado) {
        Connection con = null;
        Connection con2 = null;
        boolean resultado=true;
        PreparedStatement ps = null;
         PreparedStatement ps2 = null;
        //ResultSet rs = null;
        try {
            //insertamos al usuario en la tabla amigo
            con = Conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO usuario_amigo(id_usuario,id_amigo) VALUES(?,?)");//2
            ps.setInt(1, id_solicitante);
            ps.setInt(2, id_solicitado);
            ps.executeUpdate();
            
            
             //insertamos al amigo en la tabla del usuario
            con = Conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO usuario_amigo(id_usuario,id_amigo) VALUES(?,?)");//2
            ps.setInt(2, id_solicitante);
            ps.setInt(1, id_solicitado);
            ps.executeUpdate();
            
            
            resultado=true;
          
             
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
      
      
      
    
      
         public boolean RechazarSolicituds(int id_solicitante,int id_solicitado) {
        Connection con = null;
        boolean resultado=true;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            
            con = Conexion.getConnection();
            ps = con.prepareStatement("UPDATE solicitud_amigo SET status_solicitado=3 WHERE id_solicitante=? and id_solicitado=?");//10
            
            ps.setInt(2, id_solicitante);
            ps.setInt(1, id_solicitado);
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
    
     
     /*
      public ArrayList<Usuario> BusquedaSolicitudes(int id) {

        String sql;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
     
       ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement("select * from Usuario ");
          // ps.setInt(1,id);
            
            rs = ps.executeQuery();
   
            while (rs.next()) {

            Usuario user = new Usuario(rs.getInt("ID_USUARIO"), rs.getString("USUARIO"), rs.getString("PASSWD"), rs.getString("NOMBRE"), "",rs.getString("DIRECCION"),rs.getString("EMAIL"),rs.getString("CTAFACEBOOK"), rs.getString("CTATWITER"), rs.getString("CTAGOOGLE"),rs.getString("PRIVACIDAD"),rs.getString("IMAGENPERFIL"));
            listaUsuario.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SolicitudAmistadDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SolicitudAmistadDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return listaUsuario;
    }
    
    */
    
}
