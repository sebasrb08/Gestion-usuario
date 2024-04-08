package com.curso.vs.springboot.curso1.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.vs.springboot.curso1.DAO.UsuarioDao;
import com.curso.vs.springboot.curso1.models.Usuario;
import com.curso.vs.springboot.curso1.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;
    
    @RequestMapping(value = "api/usuarios")
    public List <Usuario>  getUsarios(@RequestHeader (value = "Authorization") String token){
        
        String usuarioId = jwtUtil.getIdFromToken(token);

        if (usuarioId == null) {
            return new ArrayList<>();
        }
        
        return usuarioDao.listar() ;


    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void  eliminar(@RequestHeader (value = "Authorization") String token, 
                            @PathVariable int id ){

        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void  registrar(@RequestBody Usuario usuario ){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.agregar(usuario);
    }

}
