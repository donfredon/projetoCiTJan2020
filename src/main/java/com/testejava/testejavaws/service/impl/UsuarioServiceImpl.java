package com.testejava.testejavaws.service.impl;

import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.dominio.repository.UsuarioRepository;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import com.testejava.testejavaws.service.UsuarioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long usuarioId){
        final Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);

        return usuario;
    }

    public Usuario create(Usuario usuario) {
        usuario.setName(usuario.getName());
        usuario.setEmail(usuario.getEmail());

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario updateUsuario(Usuario usuario, Long idUsuario) throws UsuarioInvalidoException {
        Optional<Usuario> novoUsuario = findById(idUsuario);
        if (!novoUsuario.isPresent()) {
            return null;
        }
        Usuario usuarioAtualizar = novoUsuario.get();


        if (!StringUtils.isEmpty(usuario.getName()) && !usuario.getName().equals(usuarioAtualizar.getName())) {
            usuarioAtualizar.setName(usuario.getName());
        }
        if (!Objects.isNull(usuario.getEmail()) && !usuario.getName().equals(usuarioAtualizar.getName())) {
            usuarioAtualizar.setEmail(usuario.getName());
        }

        return usuarioRepository.save(usuarioAtualizar);
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
}