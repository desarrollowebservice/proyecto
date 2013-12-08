/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author henry
 */
public class Multimedia {
    private int idMultimedia;//id_multimedia
    private String nombre;//nombre 
    private String url;//url
    private int idAlbum;//id_album

    public Multimedia() {
        this.idMultimedia = 0;
        this.nombre = "";
        this.url = "";
        this.idAlbum = 0;
    }
  
    
    public Multimedia(int idMultimedia, String nombre, String url, int idAlbum) {
        this.idMultimedia = idMultimedia;
        this.nombre = nombre;
        this.url = url;
        this.idAlbum = idAlbum;
    }

    public int getIdMultimedia() {
        return idMultimedia;
    }

    public void setIdMultimedia(int idMultimedia) {
        this.idMultimedia = idMultimedia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    
  
    
}
