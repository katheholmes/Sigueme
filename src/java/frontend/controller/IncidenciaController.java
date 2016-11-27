/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controller;

import backend.entities.Incidencia;
import static backend.entities.Incidencia_.agente;
import backend.entities.Servicio;
import backend.entities.Usuario;
import backend.model.IncidenciaFacadeLocal;
import backend.model.ServicioFacadeLocal;
import backend.model.UsuarioFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Santi
 */
@Named(value = "incidenciaController")
@SessionScoped
public class IncidenciaController implements Serializable {

    @EJB
    private IncidenciaFacadeLocal incidenciaFacadeLocal;
    @EJB
    private ServicioFacadeLocal servicioFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal; 
    
    private Incidencia incidencia;
    private Servicio servicio;
    private Usuario agente;
    private List<Servicio> servicios;
    private List<Usuario> usuarios;
            
    public IncidenciaController() {
    }

    @PostConstruct
    public void init(){
        incidencia = new Incidencia();
        servicio = new Servicio();
        agente = new Usuario();
        servicios = servicioFacadeLocal.findAll();
        usuarios = usuarioFacadeLocal.findAll();
    }
    
    public List<Incidencia> listarIncidencias(){
        return incidenciaFacadeLocal.findAll();
    }
    
    public void eliminarIncidencia(Incidencia incidencia){
        this.incidencia = incidencia;
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.incidenciaFacadeLocal.remove(this.incidencia);
            context.addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La incidencia se ha registrado correctamente") );
        }catch(Exception e){
            context.addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eror", "No se pudo eliminar la incidencia") );
        }
        
    }
    
    public void registrarIncidencia(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.incidencia.setAgente(agente);
            this.incidencia.setIdCategoria(servicio);
            this.incidenciaFacadeLocal.create(incidencia);
            context.addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La incidencia se ha registrado correctamente") );
                   
        }catch(Exception e){
           context.addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eror", "No se pudo registrar la incidencia") );
        }
    }
    
    public String editarIncidencia(Incidencia incidencia){
        this.incidencia = incidencia;
        return "editIncident";
    }
    
    public void editarIncidencia(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.incidenciaFacadeLocal.edit(incidencia);
             context.addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "La incidencia se ha modificado correctamente") );
        }catch(Exception e){
          context.addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Eror", "No se pudo modificar la incidencia") );
        }
    }
    
    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public Usuario getAgente() {
        return agente;
    }

    public void setAgente(Usuario agente) {
        this.agente = agente;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
}
