package com.testejava.testejavaws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.text.MessageFormat.format;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImagemException extends Exception {

    private static final long serialVersionUID = 1L;

       public ImagemException(String texto) {
        super("Problema com Imagem: "+  texto);
    }
}
