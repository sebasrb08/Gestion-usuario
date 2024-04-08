package com.curso.vs.springboot.curso1.DAO.imp;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.vs.springboot.curso1.DAO.UsuarioDao;
import com.curso.vs.springboot.curso1.models.Usuario;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Usuario> listar() {
        String query="FROM Usuario";

        List <Usuario> usuarios= entityManager.createQuery(query).getResultList();
        return usuarios;
    }

    @Override
    public void eliminar(int id) {
        Usuario usuario =  entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public void agregar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Usuario verificarEmailPassword(Usuario usuario) {
        
        String query="FROM Usuario WHERE email = :email";
        
        List <Usuario> usuarios= entityManager.createQuery(query)
        .setParameter("email", usuario.getEmail())
        .getResultList();

        if (usuarios.isEmpty()) {
            return null;
        }
        
        String hashPassword = usuarios.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

       if (argon2.verify(hashPassword, usuario.getPassword())){
            return usuarios.get(0);
       }
       return null;
        

    }
}
