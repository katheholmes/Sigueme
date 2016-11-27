/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.controller;

import backend.entities.Rol;
import backend.entities.Usuario;
import backend.model.RolFacadeLocal;
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
@Named(value = "usuarioController")
@SessionScoped
public class usuarioController implements Serializable {
    
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal; 
    @EJB
    private RolFacadeLocal rolFacadeLocal ;
    private Usuario usuario;
    private Rol rol;
    private List<Rol> roles;
    
    
    public usuarioController() {
    }
    
    @PostConstruct
    public void init(){
       usuario = new Usuario();
       rol = new Rol();
       roles = rolFacadeLocal.findAll();
    }

    public String iniciarSesion(){
        String redireccion = null;
        Usuario usuarioValidado;
        try{
           usuarioValidado =  usuarioFacadeLocal.iniciarSesion(this.usuario);
            if(usuario != null){
                //Almacenar los datos al obtener un inicio de sesión exitoso
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioValidado);
                redireccion = "/sigueme?faces-redirect = true";                
            }else{
                FacesContext.getCurrentInstance().addMessage(
                    null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Error","datos incorrectos"));
            }
            
        }catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(
                    null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Error: ", "datos incorrectos"));
        }
        return redireccion; 
    }
    
    public List<Usuario> listarUsuarios(){
        return this.usuarioFacadeLocal.findAll();
    }
    
    public void eliminarUsuario(Usuario usuario){
        this.usuario = usuario;
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.usuarioFacadeLocal.remove(this.usuario);
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha eliminado corectamente"));
            
        }catch(Exception e){
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Error", "No se puso eliminar el usuario"));
        }
    }
    
    public String editarUsuario(Usuario usuario){
       this.usuario = usuario;
       return "editUser";        
    }
    
    public void editarUsuario(){
        FacesContext context = FacesContext.getCurrentInstance();
     
        try{
            this.usuario.setIdRol(rol);
            this.usuarioFacadeLocal.edit(usuario);
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha modificado corectamente"));
           
        }catch(Exception e){
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Error", "No se puso modificar el usuario"));
        }
        
    }
    
    public void registrarUsuario(){
        FacesContext context = FacesContext.getCurrentInstance();
        
        try{
            this.usuario.setIdRol(rol);
            this.usuarioFacadeLocal.create(this.usuario);
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "Aviso", "El usuario se ha registrado corectamente"));
            
        }catch(Exception e){
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Error", "No se puso registrar el usuario"));
        }
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }
    
}
