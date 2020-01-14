package com.testejava.testejavaws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UsuarioInvalidoException extends Exception {

    private static final long serialVersionUID = 1L;

    public UsuarioInvalidoException(String usuarioId) {
        super("Não encontrado usuário com id: " + usuarioId);
    }

    public UsuarioInvalidoException() {
        super("Usuário não informado.");
    }
}