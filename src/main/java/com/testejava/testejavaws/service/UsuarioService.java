package com.testejava.testejavaws.service;

import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    Optional<Usuario> findById(Long usuarioId);

    Usuario create(Usuario usuario);

    void deleteUsuario(Long usuarioId);

    Usuario updateUsuario(Usuario usuario, Long idUsuario) throws UsuarioInvalidoException;
}