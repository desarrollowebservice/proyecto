/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author henry velasquez
 */
import DAO.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import negocio.Usuario;

@ManagedBean(name = "navegationController", eager = true)
@RequestScoped
public class LoginController {

    private String usuario;
    private String clave;
    private String mensaje;
    private String mensaje2;
    private String nombreapellido;
    private String email;
    private String facebook;
    private String twitter;
    private String google;
    private String direccion;
    private String privacidad;
    private int id;

    public LoginController() {
        this.usuario = "";
        this.clave = "";
        this.mensaje = "";
        this.mensaje2 = "";
        this.nombreapellido = "";
        this.email = "";
        this.facebook = "";
        this.twitter = "";
        this.google = "";
        this.direccion = "";
        this.privacidad = "";
        this.id = 0;

    }

    public String login() {
        // some validations may be done here...

        UsuarioDAO userDao = new UsuarioDAO();
        Usuario user = new Usuario();
        if (userDao.ValidarUsuario(usuario, clave)) {
            UsuarioDAO buscaruser = new UsuarioDAO();
            user = buscaruser.conseguirUsuario(usuario, clave);
            id = user.getIdUsuario();
            usuario = user.getUsuario();
            nombreapellido = user.getNombre();
            email = user.getEmail();
            direccion = user.getDireccion();
            facebook = user.getFacebook();
            twitter = user.getTwittter();
            google = user.getGoogle();
            clave = user.getPassword();
            privacidad = user.getPrivacidad();
            return "principal?faces-redirect=true";
        } else {
            mensaje = "Usuario o clave incorrecta";
            mensaje2 = "";
            return "index";
        }
    }

    public String logout() {
        // some validations may be done here...
        id = 0;
        direccion = null;
        facebook = null;
        twitter = null;
        google = null;
        clave = null;
        privacidad = null;
        usuario = null;
        mensaje = null;
        mensaje2 = null;
        nombreapellido = null;
        email = null;
        return "index?faces-redirect=true";
    }

    public boolean isLoggedIn() {

        return usuario != null;

    }

    public String Actualizar() {
        // some validations may be done here...

        if ((nombreapellido.equals(""))) {
            mensaje = "";
            mensaje2 = "nombre obligatorio";
            return "index";
        } else {
            if (usuario.equals("")) {
                mensaje = "";
                mensaje2 = "Usuario obligatorio";
                return "index";
            } else {
                if (clave.equals("")) {
                    mensaje = "";
                    mensaje2 = "clave obligatorio";
                    return "index";
                } else {
                    if (email.equals("")) {
                        mensaje = "";
                        mensaje2 = "email obligatorio";
                        return "index";
                    } else {
                        boolean Actualizado = false;
                        mensaje = "";
                        mensaje2 = "actualizado";
                        Usuario user = new Usuario();
                        UsuarioDAO Actusuario = new UsuarioDAO();
                        user.setIdUsuario(id);
                        user.setNombre(nombreapellido);
                        user.setUsuario(usuario);
                        user.setPassword(clave);
                        user.setEmail(email);
                        user.setDireccion(direccion);
                        user.setFacebook(facebook);
                        user.setTwittter(twitter);
                        user.setGoogle(google);
                        user.setPrivacidad(privacidad);
                        Actualizado = Actusuario.ActualizarUsuario(user);
                        if (Actualizado) {
                            return "principal?faces-redirect=true";
                        } else {
                            mensaje2 = "no se logro actualizar el usuario";
                            return "ActualizarUsuario";
                        }
                    }

                }
            }
        }

        


    }

    public String registrar() {
        // some validations may be done here...

        if ((nombreapellido.equals(""))) {
            mensaje = "";
            mensaje2 = "nombre obligatorio";
            return "index";
        } else {
            if (usuario.equals("")) {
                mensaje = "";
                mensaje2 = "Usuario obligatorio";
                return "index";
            } else {
                if (clave.equals("")) {
                    mensaje = "";
                    mensaje2 = "clave obligatorio";
                    return "index";
                } else {
                    if (email.equals("")) {
                        mensaje = "";
                        mensaje2 = "email obligatorio";
                        return "index";
                    } else {
                        boolean registrado = false;
                        mensaje = "";
                        mensaje2 = "registrado";
                        Usuario user = new Usuario();
                        UsuarioDAO addusuario = new UsuarioDAO();

                        user.setNombre(nombreapellido);
                        user.setUsuario(usuario);
                        user.setPassword(clave);
                        user.setEmail(email);
                        registrado = addusuario.agregarUsuario(user);
                        if (registrado) {
                            return "principal?faces-redirect=true";
                        } else {
                            mensaje2 = "no se logro registrar el usuario";
                            return "index?faces-redirect=true";
                        }
                    }

                }
            }
        }


    }

    public void pullValuesFromFlash(ComponentSystemEvent e) {
        Flash flash = FacesContext.getCurrentInstance().
                getExternalContext().getFlash();
        usuario = (String) flash.get("usuario");

    }

    public int getId() {
        return id;
    }

    public void setPrivacidad(int id) {
        this.id = id;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreapellido() {
        return nombreapellido;
    }

    public void setNombreapellido(String nombreapellido) {
        this.nombreapellido = nombreapellido;
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

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getMensaje2() {
        return mensaje2;
    }

    public void setMensaje2(String mensaje2) {
        this.mensaje2 = mensaje2;
    }
}