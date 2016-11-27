/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.validator;

import backend.entities.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author Santi
 */
@Named(value = "plantillaController")
@SessionScoped
public class PlantillaController implements Serializable {

    
    public PlantillaController() {
    }
    
    public void verificarSesion(){
        Usuario usuario;
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if(usuario != null){
                context.getExternalContext().redirect("./../index.xhtml");
            }
        }catch(Exception e){
            
        }
    }
}
