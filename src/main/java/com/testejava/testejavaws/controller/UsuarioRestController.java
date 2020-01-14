package com.testejava.testejavaws.controller;

import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import com.testejava.testejavaws.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioRestController() {
    }

    @Valid
    @RequestMapping(method = POST, value = "/create")
    public ResponseEntity createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.create(usuario));
    }

    @RequestMapping(method = DELETE, value = "/{usuarioId}/delete")
    public ResponseEntity deleteUsuario(@PathVariable(name = "usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(method = GET, value = "/")
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @RequestMapping(method = GET, value = "/{usuarioId}")
    public ResponseEntity<Usuario> findById(@PathVariable(name = "usuarioId") Long usuarioId)
            throws UsuarioInvalidoException {
        Optional<Usuario> usuario = usuarioService.findById(usuarioId);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @RequestMapping(method = PUT, value = "/{usuarioId}/update")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario novoUsuario,
            @PathVariable(name = "usuarioId") Long usuarioId) throws UsuarioInvalidoException {
        Usuario usuario = usuarioService.updateUsuario(novoUsuario, usuarioId);
        if (usuario == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.accepted().body(usuario);
    }
}
