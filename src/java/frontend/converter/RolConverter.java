/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.converter;

import backend.entities.Rol;
import backend.model.RolFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 *
 * @author Santi
 */
@FacesConverter (value = "rolConvert")
@SessionScoped
public class RolConverter implements Converter{

    @EJB
    private RolFacadeLocal rolFacadeLocal;

    public RolConverter() {
    }
    
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Rol> roles = this.rolFacadeLocal.findAll();
        for (Rol objeto : roles) {
            if (objeto.getRolID() == Integer.parseInt(value)) {
                return objeto;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
          if (value != null) {
            return ((Rol) value).getRolID().toString();
        } else {
            return "";
        }
    }
    
}
