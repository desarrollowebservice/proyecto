/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.AlbumDAO;
import DAO.MultimediaDAO;
import java.util.List;
import negocio.Album;
import negocio.Multimedia;

/**
 *
 * @author henry
 */
public class AlbumController {
    
    public List<Album> consultarcatalogo(int idUsuario) {
        AlbumDAO midao = new AlbumDAO();
        return midao.consultaralbumes(idUsuario);
    }
    
    public boolean agregarAlbum(Album album) {
        AlbumDAO midao = new AlbumDAO();
        return midao.agregarAlbum(album);
    }

    public List<Multimedia> consultarmultimedia(int albumID) {
        MultimediaDAO midao = new MultimediaDAO();
        return midao.consultarmultimedia(albumID);
    }
    
    
    
}
