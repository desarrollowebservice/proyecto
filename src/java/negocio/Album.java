/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author henry
 */
public class Album {
    private int idAlbum;//id_album
    private String nombre;//name
    private String descripcion;//descripcion
    private String privacidad; //privacidad
    private int idUsuario;//id_usuario

    public Album() {
        this.idAlbum = 0;
        this.nombre = "";
        this.descripcion = "";
        this.privacidad = "";
        this.idUsuario = 0;
    }
  
    
    public Album(int idAlbum, String nombre, String descripcion, String privacidad,int idUsuario) {
        this.idAlbum = idAlbum;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.privacidad = privacidad;
        this.idUsuario = idUsuario;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
  
    
}
