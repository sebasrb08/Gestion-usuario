package com.curso.vs.springboot.curso1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.vs.springboot.curso1.DAO.UsuarioDao;
import com.curso.vs.springboot.curso1.models.Usuario;
import com.curso.vs.springboot.curso1.utils.JWTUtil;

@RestController
public class AuthController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String  registrar(@RequestBody Usuario usuario ){

        Usuario usuario2= usuarioDao.verificarEmailPassword(usuario);

        if(usuario2 != null){
            String tokenJwt = jwtUtil.generateToken(String.valueOf(usuario2.getId()),usuario2.getEmail());
            return tokenJwt;
        }else{
            return "FAIL";
        }
    }
}
