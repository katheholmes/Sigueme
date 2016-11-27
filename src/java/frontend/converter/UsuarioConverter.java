/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.converter;

import backend.entities.Usuario;
import backend.model.UsuarioFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value = "usuarioConverter")
@ViewScoped
public class UsuarioConverter implements Converter{
    
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    public UsuarioConverter() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       List<Usuario> usuarios = this.usuarioFacadeLocal.findAll();
        for (Usuario objeto : usuarios) {
            if (objeto.getCedula().equals(value)) {
                return objeto;
            }
        }
        return null;    
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            return ((Usuario) value).getCedula();
        } else {
            return "";
        }
    }
    
}
