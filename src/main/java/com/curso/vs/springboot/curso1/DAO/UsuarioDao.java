package com.curso.vs.springboot.curso1.DAO;

import java.util.List;

import com.curso.vs.springboot.curso1.models.Usuario;

public interface UsuarioDao {
    List <Usuario> listar();

    void eliminar(int id);

    void agregar(Usuario usuario);

    Usuario verificarEmailPassword(Usuario usuario);   
}
