/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author henry
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String password;
    private String Nombre;
    private String direccion;
    private String email;
    private String facebook;
    private String twittter;
    private String google;
    private String privacidad;

    public Usuario() {
        this.idUsuario = 0;
        this.usuario = "";
        this.password = "";
        this.Nombre = "";
        this.direccion = "";
        this.email = "";
        this.facebook = "";
        this.twittter = "";
        this.google = "";
        this.privacidad = "";
    }

    public Usuario(int idUsuario, String usuario, String password, String Nombre, String apellido, String direccion, String email, String facebook, String twittter, String google, String privacidad) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.password = password;
        this.Nombre = Nombre;
        this.direccion = direccion;
        this.email = email;
        this.facebook = facebook;
        this.twittter = twittter;
        this.google = google;
        this.privacidad = privacidad;
    }
    
    

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

  

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getTwittter() {
        return twittter;
    }

    public void setTwittter(String twittter) {
        this.twittter = twittter;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }
    
    
    
    
}
