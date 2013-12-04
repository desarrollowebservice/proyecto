/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.AlbumDAO;
import java.util.List;
import negocio.Album;

/**
 *
 * @author henry
 */
public class AlbumController {
    
    public List<Album> consultarcatalogo(int idUsuario) {
        AlbumDAO midao = new AlbumDAO();
        return midao.consultaralbumes(idUsuario);
    }
    
    
    
}
