/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author henry
 */
import DAO.AlbumDAO;
import DAO.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ComponentSystemEvent;
import negocio.Album;
import negocio.Multimedia;
import negocio.Usuario;

@ManagedBean(name = "loginController", eager = true)
@RequestScoped
public class LoginController {

    //Atributos de usuario
    private String usuario;
    private String clave;
    private String nombreapellido;
    private String email;
    private String facebook;
    private String twitter;
    private String google;
    private String direccion;
    private String nick;
    private String privacidad;
    private int id;
    private String imagenperfil;
    //mensajes de respuesta
    private String mensaje;
    private String mensaje2;
    //atributos de albumes
    private String AlbumNombre;
    private String AlbumDesc;
    private String Albumpriv;
    private List<Album> albumes;
    private List<Multimedia> multimedia;
    private String busqueda;
    private String agregarFoto;
    private String imagenSeleccionada;
    private String imagenesAgregar;
      private ArrayList<Usuario> listaUsuario;
      private ArrayList<Usuario> listaSolicitud;
      private ArrayList<Usuario> listaAmigos;
      private String busquedaUsu;

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
        this.nick = "";
        this.imagenperfil= "";
        this.privacidad = "publica";
        this.id = 0;
        this.albumes = new ArrayList<Album>();
        this.multimedia = new ArrayList<Multimedia>();
        this.AlbumNombre = "";
        this.AlbumDesc = "";
        this.Albumpriv = "publica";
        this.busqueda = "surf";
        this.agregarFoto = "";
        this.imagenSeleccionada = "";
        this.imagenesAgregar = "";
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
            nick = user.getUsuario();
            clave = user.getPassword();
            privacidad = user.getPrivacidad();
            imagenperfil = user.getImagenperfil();
            return "principal?faces-redirect=true";
        } else {
            mensaje = "Usuario o clave incorrecta";
            mensaje2 = "";
            return "index";
        }
    }

    public String logout() {
        // some validations may be done here...
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
        this.nick = "";
        this.privacidad = "";
        this.id = 0;
        this.imagenperfil = "";
        return "index?faces-redirect=true";
    }
  
     public String  BusquedaUsuario() {
        // some validations may be done here...
        UsuarioDAO busquedaUsu = new UsuarioDAO();
     /*  if ( (this.busquedaUsu == null) || (this.busquedaUsu.isEmpty()) ) 
       {  this.setBusquedaUsu("hen");}*/
       
       
       this.listaUsuario  = busquedaUsu.BusquedaUsuario(this.busquedaUsu);
        
       this.busquedaUsu="";
       return "BusquedaUsu?faces-redirect=true";
    }
     
     
           public String  BusquedaSolicitud(int id) {
        // some validations may be done here...
       // SolicitudAmistadDAO busquedaSolicitud = new SolicitudAmistadDAO();
                UsuarioDAO busquedalis = new UsuarioDAO();
     /*  if ( (this.busquedaUsu == null) || (this.busquedaUsu.isEmpty()) ) 
       {  this.setBusquedaUsu("hen");}*/
       
       
       //this.setListaSolicitud(busquedaSolicitud.BusquedaSolicitudes(24));
        this.listaSolicitud=busquedalis.BusquedaSolicitudes(id);
      
       return "SolicitudAmistad?faces-redirect=true";
    }
           
           
        public String  BusquedaAmistad(int id) {
        // some validations may be done here...
      
                UsuarioDAO busquedaamigo = new UsuarioDAO();
    
           this.listaAmigos=busquedaamigo.BusquedaAmigos(id);
      
       return "Amigos?faces-redirect=true";
    }

      public String  EliminarAmistad(int id,int id_amigo) {
      boolean resultado;
                UsuarioDAO Eliminaramigo = new UsuarioDAO();
   
       
      
        resultado=Eliminaramigo.EliminarAmigos(id, id_amigo);
      
       return "principal?faces-redirect=true";
    }    
        
     
    public boolean isLoggedIn() {

        return usuario != null;

    }

    public String Actualizar() {
        // some validations may be done here...


        if (usuario.equals("")) {
            mensaje = "";
            mensaje2 = "Nombre obligatorio";
            return "ActualizarUsuario?faces-redirect=true";
        } else {
            if (clave.equals("")) {
                mensaje = "";
                mensaje2 = "clave obligatorio";
                return "ActualizarUsuario?faces-redirect=true";
            } else {
                if (email.equals("")) {
                    mensaje = "";
                    mensaje2 = "email obligatorio";
                    return "ActualizarUsuario?faces-redirect=true";
                } else {
                    if (nick.equals("")) {
                        mensaje = "";
                        mensaje2 = "Usuario obligatorio";
                        return "ActualizarUsuario?faces-redirect=true";
                    } else {
                        if (privacidad.equals("")) {
                            mensaje = "";
                            mensaje2 = "privacidad obligatorio";
                            return "ActualizarUsuario?faces-redirect=true";
                        } else {
                            if (clave.equals("")) {
                                mensaje = "";
                                mensaje2 = "clave obligatoria";
                                return "ActualizarUsuario?faces-redirect=true";
                            } else {
                                boolean Actualizado = false;
                                mensaje = "";
                                mensaje2 = "Actualizado";
                                Usuario user = new Usuario();
                                UsuarioDAO Actusuario = new UsuarioDAO();
                                user.setIdUsuario(id);
                                user.setNombre(nombreapellido);
                                user.setUsuario(nick);
                                user.setPassword(clave);
                                user.setEmail(email);
                                user.setDireccion(direccion);
                                user.setFacebook(facebook);
                                user.setTwittter(twitter);
                                user.setGoogle(google);
                                user.setPrivacidad(privacidad);
                                user.setImagenperfil(imagenperfil);
                                Actualizado = Actusuario.ActualizarUsuario(user);
                                if (Actualizado) {
                                    return "principal?faces-redirect=true";
                                } else {
                                    mensaje2 = "no se logro actualizar el usuario";
                                    return "ActualizarUsuario?faces-redirect=true";
                                }
                            }
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
            if (nick.equals("")) {
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
                        user.setUsuario(nick);
                        user.setPassword(clave);
                        user.setEmail(email);
                        user.setImagenperfil(imagenperfil);
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public String getAlbumNombre() {
        return AlbumNombre;
    }

    public void setAlbumNombre(String AlbumNombre) {
        this.AlbumNombre = AlbumNombre;
    }

    public String getAlbumDesc() {
        return AlbumDesc;
    }

    public void setAlbumDesc(String AlbumDesc) {
        this.AlbumDesc = AlbumDesc;
    }

    public String getAlbumpriv() {
        return Albumpriv;
    }

    public void setAlbumpriv(String Albumpriv) {
        this.Albumpriv = Albumpriv;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }

    public String getAgregarFoto() {
        return agregarFoto;
    }

    public void setAgregarFoto(String agregarFoto) {
        this.agregarFoto = agregarFoto;
    }

    public String getImagenSeleccionada() {
        return imagenSeleccionada;
    }

    public void setImagenSeleccionada(String imagenSeleccionada) {
        this.imagenSeleccionada = imagenSeleccionada;
    }

    public String getImagenesAgregar() {
        return imagenesAgregar;
    }

    public void setImagenesAgregar(String imagenesAgregar) {
        this.imagenesAgregar = imagenesAgregar;
    }

    public String getImagenperfil() {
        return imagenperfil;
    }

    public void setImagenperfil(String imagenperfil) {
        this.imagenperfil = imagenperfil;
    }

    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public ArrayList<Usuario> getListaSolicitud() {
        return listaSolicitud;
    }

    public void setListaSolicitud(ArrayList<Usuario> listaSolicitud) {
        this.listaSolicitud = listaSolicitud;
    }

    public ArrayList<Usuario> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(ArrayList<Usuario> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public String getBusquedaUsu() {
        return busquedaUsu;
    }

    public void setBusquedaUsu(String busquedaUsu) {
        this.busquedaUsu = busquedaUsu;
    }

    
    
    public String insertaralbum() {
        if (AlbumNombre.equals("")) {
            mensaje = "Nombre de Album Obligatorio";
            return "CrearAlbum?faces-redirect=true";
        } else {
            if (imagenesAgregar.equals("")) {
                mensaje = "Debe agregar al menos una imagen";
                return "CrearAlbum?faces-redirect=true";
            } else {
                boolean registrado = false;
                AlbumController albumcontroller = new AlbumController();
                MultimediaController multimediacontroller = new MultimediaController();
                Album album = new Album();
                //album.setIdAlbum(4);
                album.setNombre(AlbumNombre);
                album.setDescripcion(AlbumDesc);
                album.setIdUsuario(id);
                album.setPrivacidad(Albumpriv);
                registrado = albumcontroller.agregarAlbum(album);
                if (registrado) {
                    boolean registrado2 = false;
                    int idAlbum = albumcontroller.ObtenerUltimoAlbum(id);
                    StringTokenizer tokenizer = new StringTokenizer(imagenesAgregar, ";");
                    int numberOfTokens = tokenizer.countTokens();
                    String[] multimedias = new String[numberOfTokens];
                    for (int i = 0; i < numberOfTokens; i++) {
                        multimedias[i] = tokenizer.nextToken();
                    }
                    for (int i = 0; i< multimedias.length; i++){
                        Multimedia multimedia = new Multimedia();
                        multimedia.setNombre("");
                        multimedia.setUrl(multimedias[i]);
                        multimedia.setIdAlbum(idAlbum);
                        registrado2 = multimediacontroller.agregarMultimedia(multimedia);
                    }
                    AlbumNombre = "";
                    AlbumDesc = "";
                    Albumpriv = "publica";
                    mensaje2 = "";
                    imagenesAgregar = "";
                    return paginaAlbum();
                } else {
                    return "CrearAlbum?faces-redirect=true";
                }

            }

        }

    }

    public String paginaAlbum() {
        this.AlbumNombre = "";
        this.AlbumDesc = "";
        this.Albumpriv = "publica";
        AlbumController albumcontroller = new AlbumController();
        albumes = albumcontroller.consultarcatalogo(id);

        if (albumes.isEmpty()) {
            mensaje = "albumes vacio1";
        } else {
            mensaje = "albumes lleno1";
        }
        return "album?faces-redirect=true";
    }

    public String paginaFotosAlbum() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        this.AlbumNombre = params.get("AlbumNombre");
        this.AlbumDesc = params.get("AlbumDesc");
        int albumID = Integer.parseInt(params.get("idalbum"));
        MultimediaController multimediacontroller = new MultimediaController();
        multimedia = multimediacontroller.consultarmultimedia(albumID);

        if (multimedia.isEmpty()) {
            mensaje = "albumes vacio1";
        } else {
            mensaje = "albumes lleno1";
        }
        return "FotosAlbum?faces-redirect=true";
    }

    public String updateAgregarFoto() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params =
                fc.getExternalContext().getRequestParameterMap();
        this.mensaje = params.get("menuItem");
        mensaje = "hola mundo";
        return "#";
    }

    public void imagenesParaAgregar() {

        imagenesAgregar = imagenSeleccionada + ";" + imagenesAgregar;
        imagenSeleccionada = "";
        mensaje = "Imagen Agregada";
    }
}
