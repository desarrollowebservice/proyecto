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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Album;
import negocio.Multimedia;


/**
 *
 * @author henry
 */
public class MultimediaDAO {

    public List<Multimedia> consultarmultimedia(int albumID) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Multimedia> multimedia = new ArrayList<Multimedia>();

        try {
            //1.- obtengo la conexion
            con = Conexion.getConnection();
            //2.- obtengo la sentencia
            ps = con.prepareStatement("select * from multimedia where id_album=?");//
            ps.setInt(1, albumID);
            
            rs = ps.executeQuery();
            //4.- ejecuto

            //5.- leo
            while (rs.next()) {
                Multimedia myalbum = new Multimedia();
                myalbum.setIdMultimedia(rs.getInt("ID_MULTIMEDIA"));
                myalbum.setNombre(rs.getString("NOMBRE"));
                myalbum.setUrl(rs.getString("URL"));
                myalbum.setIdAlbum(rs.getInt("ID_ALBUM"));
                multimedia.add(myalbum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MultimediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MultimediaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return multimedia;
    }

    public boolean agregarAlbum(Album album) {
        Connection con = null;
        boolean resultado = false;
        PreparedStatement ps = null;
        //ResultSet rs = null;
        try {

            con = Conexion.getConnection();
            ps = con.prepareStatement("INSERT INTO album_usuario(id_album,name,privacidad,id_usuario,descripcion)"
                    + " VALUES(nextval('seq_album'),?,?,?,?)");

            //ps.setInt(1, album.getIdAlbum());
            ps.setString(1, album.getNombre());
            ps.setString(2, album.getPrivacidad());
            ps.setInt(3, album.getIdUsuario());
            ps.setString(4, album.getDescripcion());
            ps.executeUpdate();
            resultado = true;
            //  con.commit();

        } catch (SQLException ex) {
            resultado = false;
            Logger.getLogger(MultimediaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
