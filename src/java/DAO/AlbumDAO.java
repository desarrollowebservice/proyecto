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

/**
 *
 * @author henry
 */
public class AlbumDAO {

    public List<Album> consultaralbumes(int idUsuario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Album> albumes = new ArrayList<Album>();

        try {
            //1.- obtengo la conexion
            con = Conexion.getConnection();
            //2.- obtengo la sentencia
            ps = con.prepareStatement("select * from album_usuario where id_usuario=?");//
            ps.setInt(1, idUsuario);
            
            rs = ps.executeQuery();
            //4.- ejecuto

            //5.- leo
            while (rs.next()) {
                Album myalbum = new Album();
                myalbum.setNombre(rs.getString("NAME"));
                myalbum.setDescripcion(rs.getString("DESCRIPCION"));
                myalbum.setIdAlbum(rs.getInt("ID_ALBUM"));
                myalbum.setIdUsuario(rs.getInt("ID_USUARIO"));
                myalbum.setPrivacidad(rs.getString("PRIVACIDAD"));
                albumes.add(myalbum);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return albumes;
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
            Logger.getLogger(AlbumDAO.class.getName()).log(Level.SEVERE, null, ex);
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
