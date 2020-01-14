package com.testejava.testejavaws.service;

import com.testejava.testejavaws.dominio.Imagem;
import com.testejava.testejavaws.dominio.ImagemDTO;
import com.testejava.testejavaws.exception.ImagemException;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface DesafioUploadService {

    String upload(ImagemDTO imagemDTO) throws ImagemException, UsuarioInvalidoException, IOException;

    Collection<Imagem> findByUsuario(Long idUsuario) throws UsuarioInvalidoException;

    Collection<Imagem> findAll();

    Collection<Imagem> findByNome(String nomeImagem) ;

    void removerImagemPorId(Long imagemId) throws ImagemException;
}
