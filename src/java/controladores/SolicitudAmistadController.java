/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import DAO.SolicitudAmistadDAO;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import negocio.SolicitudAmistad;
import negocio.Usuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;


/**
 *
 * @author fernando
 */

@ManagedBean(name = "solicitudAmistadController", eager = true)
@RequestScoped
public class SolicitudAmistadController implements Serializable{
    private String mensaje3;
    private ArrayList<Usuario> listaSolicitud;

    public SolicitudAmistadController() {
        this.mensaje3 = "";
        this.listaSolicitud = new ArrayList<Usuario>();
    }
    
    
    
      public String EnviarSolicitud(int id_solicitante,int id_solicitado) {
        // some validations may be done here...
       SolicitudAmistadDAO solicitudDAO = new SolicitudAmistadDAO();
       boolean registrado;
       SolicitudAmistad solicitud = new SolicitudAmistad(id_solicitante, id_solicitado, 1, 0);
      
       registrado = solicitudDAO.agregarUsuario(solicitud);
       if (registrado){
           this.mensaje3="";
       return "principal?faces-redirect=true";
       }
       else {
           this.mensaje3="No se envio la solicitud Debido a que ya la pudo haber enviado o el amigo a usted revise su ventana principal de solicitud";
       return "BusquedaUsu?faces-redirect=true";
       }
    }
    
      
       public String AceptarSolicitud(int id_solicitante,int id_solicitado) {
        // some validations may be done here...
       SolicitudAmistadDAO solicitudDAO = new SolicitudAmistadDAO();
        SolicitudAmistadDAO solicitudDAO2 = new SolicitudAmistadDAO();
       boolean registrado;
       boolean insertado;
      // SolicitudAmistad solicitud = new SolicitudAmistad(id_solicitante, id_solicitado, 1, 0);
      
       registrado = solicitudDAO.ConfirmarSolicituds(id_solicitante,id_solicitado);
       if (registrado){
           this.mensaje3="";
           insertado = solicitudDAO2.InsertarAmigoConf(id_solicitante, id_solicitado);
           if(insertado){
       return "principal?faces-redirect=true";
           }
           else{
              return "principal?faces-redirect=true";
           }
               
       }
       else {
           this.mensaje3="No se envio la solicitud Debido a que ya la pudo haber enviado o el amigo a usted revise su ventana principal de solicitud";
       return "principal?faces-redirect=true";
       }
    }
      
          public String RechazarSolicitud(int id_solicitante,int id_solicitado) {
        // some validations may be done here...
       SolicitudAmistadDAO solicitudDAO = new SolicitudAmistadDAO();
       boolean registrado;
      // SolicitudAmistad solicitud = new SolicitudAmistad(id_solicitante, id_solicitado, 1, 0);
      
       registrado = solicitudDAO.RechazarSolicituds(id_solicitante,id_solicitado);
       if (registrado){
           this.mensaje3="";
       return "principal?faces-redirect=true";
       }
       else {
           this.mensaje3="No se envio la solicitud Debido a que ya la pudo haber enviado o el amigo a usted revise su ventana principal de solicitud";
       return "principal?faces-redirect=true";
       }
    }
      
      /*
      public String  BusquedaSolicitud() {
        // some validations may be done here...
        SolicitudAmistadDAO busquedaSolicitud = new SolicitudAmistadDAO();
     /*  if ( (this.busquedaUsu == null) || (this.busquedaUsu.isEmpty()) ) 
       {  this.setBusquedaUsu("hen");}*/
       
    /*   
       //this.setListaSolicitud(busquedaSolicitud.BusquedaSolicitudes(24));
        this.listaSolicitud=busquedaSolicitud.BusquedaSolicitudes(24);
      
       return "SolicitudAmistad?faces-redirect=true";
    }
*/
      
      
    public ArrayList<Usuario> getListaSolicitud() {
        return listaSolicitud;
    }

    public void setListaSolicitud(ArrayList<Usuario> listaSolicitud) {
        this.listaSolicitud = listaSolicitud;
    }
      
      
    public String getMensaje3() {
        return mensaje3;
    }

    public void setMensaje3(String mensaje3) {
        this.mensaje3 = mensaje3;
    }
    
    
}
