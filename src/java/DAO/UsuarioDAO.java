/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Usuario;

/**
 *
 * @author henry h
 */
public class UsuarioDAO {

        
    public ArrayList<Usuario> BusquedaUsuario(String palabra) {

        String sql;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
     
       ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
   if ( (palabra == null) || (palabra.isEmpty()) ) {
    // cadena está vacía
       con = Conexion.getConnection();
            ps = con.prepareStatement("select * from Usuario ");
            //ps.setString(1, usuario);
            
            rs = ps.executeQuery();
}
   else {    con = Conexion.getConnection();
            ps = con.prepareStatement("select * from Usuario where nombre LIKE '%"+palabra+"%'");
           // ps.setString(1,palabra);
            
            rs = ps.executeQuery();
   }
            while (rs.next()) {

            Usuario user = new Usuario(rs.getInt("ID_USUARIO"), rs.getString("USUARIO"), rs.getString("PASSWD"), rs.getString("NOMBRE"), "",rs.getString("DIRECCION"),rs.getString("EMAIL"),rs.getString("CTAFACEBOOK"), rs.getString("CTATWITER"), rs.getString("CTAGOOGLE"),rs.getString("PRIVACIDAD"),rs.getString("IMAGENPERFIL"));
            listaUsuario.add(user);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return listaUsuario;
    }
    
    
        public ArrayList<Usuario> BusquedaSolicitudes(int id) {

        String sql;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
     
       ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement("select ID_USUARIO,USUARIO,PASSWD,NOMBRE,DIRECCION,EMAIL,CTAFACEBOOK,CTATWITER,CTAGOOGLE,PRIVACIDAD,IMAGENPERFIL from usuario u,solicitud_amigo s where s.id_solicitante = u.id_usuario and id_solicitado=? and s.status_solicitado=0");
          ps.setInt(1,id);
            
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
        
        
    public ArrayList<Usuario> BusquedaAmigos(int id) {

        String sql;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
     
       ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement("select u.ID_USUARIO,USUARIO,PASSWD,NOMBRE,DIRECCION,EMAIL,CTAFACEBOOK,CTATWITER,CTAGOOGLE,PRIVACIDAD,IMAGENPERFIL from usuario u,usuario_amigo a where a.id_amigo = u.id_usuario and a.id_usuario=?");
          ps.setInt(1,id);
            
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
    
    public boolean EliminarAmigos(int id,int id_amigo) {

        String sql;
        Connection con = null;
        Connection con2 = null;
        Connection con3 = null;
         Connection con4 = null;
        PreparedStatement ps = null;
         PreparedStatement ps2 = null;
          PreparedStatement ps3 = null;
           PreparedStatement ps4 = null;
     boolean resultado;
      

        try {
            
            //borramos a nuestro amigo en nuestra lista
            con = Conexion.getConnection();
            ps = con.prepareStatement(" DELETE from usuario_amigo a where a.id_usuario=?  and a.id_amigo=?");
          ps.setInt(1,id);
          ps.setInt(2,id_amigo);   
            ps.executeUpdate();
        

           //borramos a nosotros en la lista de nuestro amigo
            con2 = Conexion.getConnection();
            ps2 = con2.prepareStatement(" DELETE from usuario_amigo a where a.id_usuario=?  and a.id_amigo=?");
          ps2.setInt(2,id);  
          ps2.setInt(1,id_amigo);  
            ps2.executeUpdate();
            
            //borramos la solicitud de nuestro amigo 
             con3 = Conexion.getConnection();
            ps3 = con3.prepareStatement("delete from solicitud_amigo where id_solicitado=? and id_solicitante=?");
          ps3.setInt(2,id);
          ps3.setInt(1,id_amigo);  
            ps3.executeUpdate();

            //si es de manera contraria tambien borramos la solicitud
             con3 = Conexion.getConnection();
            ps3 = con3.prepareStatement("delete from solicitud_amigo where id_solicitado=? and id_solicitante=?");
          ps3.setInt(1,id);
          ps3.setInt(2,id_amigo);  
            ps3.executeUpdate();
            
            resultado=true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            resultado=false;
        } finally {
            try {
               
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return resultado;
    }
        
    
    
    
    public Usuario conseguirUsuario(String usuario, String clave) {

        String sql;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuario user = new Usuario();


        try {

            con = Conexion.getConnection();
            ps = con.prepareStatement("select * from Usuario where USUARIO=? and PASSWD=?");
            ps.setString(1, usuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();

            while (rs.next()) {

                user.setIdUsuario(rs.getInt("ID_USUARIO"));
                user.setUsuario(rs.getString("USUARIO"));
                user.setPassword(rs.getString("PASSWD"));
                user.setNombre(rs.getString("NOMBRE"));
                user.setDireccion(rs.getString("DIRECCION"));
                user.setEmail(rs.getString("EMAIL"));
                user.setFacebook(rs.getString("CTAFACEBOOK"));
                user.setTwittter(rs.getString("CTATWITER"));
                user.setGoogle(rs.getString("CTAGOOGLE"));
                user.setPrivacidad(rs.getString("PRIVACIDAD"));
                user.setImagenperfil(rs.getString("IMAGENPERFIL"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
               
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return user;
    }
    
    public boolean ValidarUsuario (String usuario,String clave){
        Usuario user= new Usuario();
        user=this.conseguirUsuario(usuario, clave);
        if (user.getNombre().isEmpty()){
            return false;
        } else {
            return true;
        }
        
    }
    
    
    public boolean agregarUsuario(Usuario usuario) {
        Connection con = null;
        boolean resultado=true;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            
            con = Conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO usuario(ID_USUARIO,USUARIO,PASSWD,NOMBRE,DIRECCION,EMAIL,CTAFACEBOOK,CTATWITER,CTAGOOGLE,PRIVACIDAD,IMAGENPERFIL)"
                    + " VALUES(nextval('usuarioseq'),?,?,?,?,?,?,?,?,?,?)");//10
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, "");
            ps.setString(5, usuario.getEmail());
            ps.setString(6, "");
            ps.setString(7, "");
            ps.setString(8, "");
            ps.setString(9, "publico");
            ps.setString(10, usuario.getImagenperfil());
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
    
    
    
   public boolean ActualizarUsuario(Usuario usuario) {
        Connection con = null;
        boolean resultado=true;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {
            
            con = Conexion.getConnection();
            ps = con.prepareStatement("UPDATE usuario SET USUARIO=?,PASSWD=?,NOMBRE=?,DIRECCION=?,EMAIL=?,CTAFACEBOOK=?,CTATWITER=?,CTAGOOGLE=?,PRIVACIDAD=?,IMAGENPERFIL=? WHERE ID_USUARIO=?");//10
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getEmail());
            ps.setString(6, usuario.getFacebook());
            ps.setString(7, usuario.getTwittter());
            ps.setString(8, usuario.getGoogle());
            ps.setString(9, usuario.getPrivacidad());
            ps.setString(10, usuario.getImagenperfil());
            ps.setInt(11, usuario.getIdUsuario());
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
     public ArrayList<String> obtenerResoluciones()
     {
     Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     ArrayList<String> ress = new ArrayList<String>();
     try{
            
     con = conexion.getConnection();
     ps = con.prepareStatement("select tipo from resolucion");
     //System.out.println("entro");
     rs = ps.executeQuery();
     while(rs.next())
     {
     String res = rs.getString("tipo");
     ress.add(res);
     }
     }
     catch (SQLException ex) {
     Logger.getLogger(usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
     }finally {
     try {
     if (rs != null) {
     rs.close();
     }
     if (ps != null) {
     ps.close();
     }
     if (con != null) {
     con.close();
     }
     } catch (SQLException ex) {
     Logger.getLogger(usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
     }

     }
     return ress;
     }
    
     public ArrayList<foto> verHistorial(int resolucion, int usu, String fecha)
     {
        
     Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     foto f = new foto();
     ArrayList<foto> fotos = new ArrayList<foto>();
     try{
     con = conexion.getConnection();
     ps = con.prepareStatement("with uno as(select nombre, count(foto.id_foto) as cuenta from foto, pago where id_usuario = ? and id_resolucion = ? and pago.id_foto = foto.id_foto and pago.fecha_compra > ? group by nombre), dos as( select nombre, sum(precio) as suma from foto, pago where id_usuario = ? and id_resolucion = ? and pago.id_foto = foto.id_foto and pago.fecha_compra > ?  group by nombre) select uno.nombre as nombrito, uno.cuenta as cuentica, dos.suma as sumita from uno, dos where uno.nombre = dos.nombre");
     ps.setInt(1, usu);
     ps.setInt(2, resolucion);
     ps.setString(3, fecha);
     ps.setInt(4, usu);
     ps.setInt(5, resolucion);
     ps.setString(6, fecha);
     rs = ps.executeQuery();
     while (rs.next()) {
     f.setNombre(rs.getString("nombrito"));
     f.setCuenta(rs.getInt("cuentica"));
     f.setSuma(rs.getInt("sumita"));
     fotos.add(f);
     }
            
     }
     catch (SQLException ex) {
     Logger.getLogger(usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
     }finally {
     try {
     if (rs != null) {
     rs.close();
     }
     if (ps != null) {
     ps.close();
     }
     if (con != null) {
     con.close();
     }
     } catch (SQLException ex) {
     Logger.getLogger(usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
     }

     }
     return fotos;
     }

     public ArrayList<foto> reporte()
     {

     Connection con = null;
     PreparedStatement ps = null;
     ResultSet rs = null;
     foto f = new foto();
     ArrayList<foto> fotos = new ArrayList<foto>();
     try{
     con = conexion.getConnection();
     ps = con.prepareStatement("with uno as(select pnombre, count(foto.id_foto) as cuenta from foto, pago, pais where foto.id_foto = pago.id_foto and foto.id_pais = pais.id_pais and fecha_compra >'01-JUL-2011' group by pnombre), dos as(select pnombre, sum(foto.precio) as suma from foto, pago, pais where foto.id_foto = pago.id_foto and foto.id_pais = pais.id_pais and fecha_compra >'01-JUL-2011' group by pnombre) select uno.cuenta as cantidad, dos.suma as valor from uno, dos where uno.pnombre = dos.pnombre");

     rs = ps.executeQuery();
     while (rs.next()) {
     f.setCuenta(rs.getInt("cantidad"));
     f.setSuma(rs.getInt("valor"));
     fotos.add(f);
     }

     }
     catch (SQLException ex) {
     Logger.getLogger(usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
     }finally {
     try {
     if (rs != null) {
     rs.close();
     }
     if (ps != null) {
     ps.close();
     }
     if (con != null) {
     con.close();
     }
     } catch (SQLException ex) {
     Logger.getLogger(usuario_DAO.class.getName()).log(Level.SEVERE, null, ex);
     }

     }
     return fotos;
     }
     */
}
