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
public class MultimediaController {
    
    public List<Multimedia> consultarmultimedia(int albumID) {
        MultimediaDAO midao = new MultimediaDAO();
        return midao.consultarmultimedia(albumID);
    }
    
    public boolean agregarMultimedia(Multimedia multimedia) {
        MultimediaDAO midao = new MultimediaDAO();
        return midao.agregarMultimedia(multimedia);
    }    
    
    
}
