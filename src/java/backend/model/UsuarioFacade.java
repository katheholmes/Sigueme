/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.model;

import backend.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Santi
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "SiguemePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario iniciarSesion(Usuario us){
        Usuario usuario = null;
        String consulta;
        try{
            consulta = "SELECT u FROM Usuario u WHERE u.cedula = ?1 AND u.contraseña = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getCedula());
            query.setParameter(2, us.getContraseña());
            
            List<Usuario> usuariosLista = query.getResultList();
            if(usuariosLista.isEmpty()){
                usuario = usuariosLista.get(0);
            }
        }catch(Exception e){
            throw e;
        }
        return usuario;
    }
    
}
