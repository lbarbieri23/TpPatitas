package com.dds.mascotas.dao;

import com.dds.mascotas.model.security.Usuario;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Service
public class UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Usuario findBy(String username, String password) {
         Usuario singleResult=null;
        try {
             singleResult = (Usuario) entityManager.
                    createQuery(
                            "from Usuario where username=:username and password=:password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return singleResult;
    }

    public void registrarUsuario(Usuario usuario){
        try {
            entityManager.persist(usuario);
        } catch (Exception e) {
            System.out.println("error al intentar registrar usuario" + e);
        }
    }


}
