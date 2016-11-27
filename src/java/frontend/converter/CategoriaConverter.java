/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.converter;

import backend.entities.Servicio;
import backend.model.ServicioFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value = "categoriaConverter")
@ViewScoped
public class CategoriaConverter implements Converter{
    @EJB
    private ServicioFacadeLocal servicioFacadeLocal;

    public CategoriaConverter() {
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        List<Servicio> categorias = this.servicioFacadeLocal.findAll();
        for (Servicio objeto : categorias) {
            if (objeto.getServicioID() == Integer.parseInt(value)) {
                return objeto;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
          if (value != null) {
            return ((Servicio) value).getServicioID().toString();
        } else {
            return "";
        }
    }
    
    
}
