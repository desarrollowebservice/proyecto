/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author fernando
 */
public class SolicitudAmistad {
  private int  id_solicitante ;
  private int id_solicitado;                        
 private int status_solicitante;
 private int status_solicitado;

    public SolicitudAmistad(int id_solicitante, int id_solicitado, int status_solicitante, int status_solicitado) {
        this.id_solicitante = id_solicitante;
        this.id_solicitado = id_solicitado;
        this.status_solicitante = status_solicitante;
        this.status_solicitado = status_solicitado;
    }

     public SolicitudAmistad() {
        this.id_solicitante = 0;
        this.id_solicitado = 0;
        this.status_solicitante = 1;
        this.status_solicitado = 0;
    }
    
    
    public int getId_solicitante() {
        return id_solicitante;
    }

    public void setId_solicitante(int id_solicitante) {
        this.id_solicitante = id_solicitante;
    }

    public int getId_solicitado() {
        return id_solicitado;
    }

    public void setId_solicitado(int id_solicitado) {
        this.id_solicitado = id_solicitado;
    }

    public int getStatus_solicitante() {
        return status_solicitante;
    }

    public void setStatus_solicitante(int status_solicitante) {
        this.status_solicitante = status_solicitante;
    }

    public int getStatus_solicitado() {
        return status_solicitado;
    }

    public void setStatus_solicitado(int status_solicitado) {
        this.status_solicitado = status_solicitado;
    }
 
 
}
