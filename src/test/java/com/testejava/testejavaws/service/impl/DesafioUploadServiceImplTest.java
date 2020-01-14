package com.testejava.testejavaws.service.impl;

import com.testejava.testejavaws.dominio.Imagem;
import com.testejava.testejavaws.dominio.ImagemDTO;
import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.dominio.repository.ImagemRepository;
import com.testejava.testejavaws.dominio.repository.UsuarioRepository;
import com.testejava.testejavaws.exception.ImagemException;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import com.testejava.testejavaws.service.UsuarioService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DesafioUploadServiceImplTest {

    @InjectMocks
    private DesafioUploadServiceImpl desafioUploadServiceImpl;

    @Mock
    private ImagemRepository imagemRepository;

    @Mock
    private UsuarioService usuarioService;


    @Mock
    private UsuarioRepository usuarioRepository;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void upload() throws ImagemException, UsuarioInvalidoException, IOException {

        MultipartFile multipartFile = new MockMultipartFile("file", "imagem.png", "imagem/png", "teste conteudo".getBytes());
        ImagemDTO imagemDTO = ImagemDTO.builder().idUsuario(1L).imagens(asList(multipartFile)).build();
        final Usuario usuario = Usuario.builder().id(1L).name("Fred").build();
        when(usuarioService.findById(1L)).thenReturn(Optional.ofNullable(usuario));

        desafioUploadServiceImpl.upload(imagemDTO);

        verify(imagemRepository, times(1)).save(any(Imagem.class));
    }

    @Test
    public void findAll() {

        Imagem imagem1 = Imagem.builder().nome("Imagem1").build();
        Imagem imagem2 = Imagem.builder().nome("Imagem2").build();
        when(imagemRepository.findAll()).thenReturn(asList(imagem1, imagem2));

        final Collection<Imagem> imagensList = desafioUploadServiceImpl.findAll();

        Assert.assertNotNull(imagensList);
        Assert.assertEquals(imagensList.size(), 2);
        verify(imagemRepository, times(1)).findAll();

    }

    @Test
    public void findByUsuario() throws UsuarioInvalidoException {
        final Usuario usuario = Usuario.builder().id(1L).name("Fred").build();
        Imagem imagem1 = Imagem.builder().nome("Imagem1").usuario(usuario).build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.ofNullable(usuario));
        when(imagemRepository.findByUsuario(usuario)).thenReturn(asList(imagem1));

        final Collection<Imagem> imagensList = desafioUploadServiceImpl.findByUsuario(1L);

        Assert.assertNotNull(imagensList);
        Assert.assertEquals(imagensList.size(), 1);
        verify(imagemRepository, times(1)).findByUsuario(any(Usuario.class));
        verify(usuarioRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void findByNome() {
        Imagem imagem1 = Imagem.builder().nome("Imagem1").build();
        when(imagemRepository.findByNome("Imagem1")).thenReturn(asList(imagem1));

        final Collection<Imagem> imagensList = desafioUploadServiceImpl.findByNome("Imagem1");

        Assert.assertNotNull(imagensList);
        Assert.assertEquals(imagensList.size(), 1);
        verify(imagemRepository, times(1)).findByNome(any(String.class));
    }

    @Test
    public void removerImagemPorId() throws ImagemException {
        Imagem imagem1 = Imagem.builder().id(1L).nome("Imagem1").build();
        doNothing().when(imagemRepository).deleteById(imagem1.getId());
        when(imagemRepository.findById(imagem1.getId())).thenReturn(Optional.of(imagem1));
        desafioUploadServiceImpl.removerImagemPorId(imagem1.getId());

        verify(imagemRepository, times(1)).deleteById(any(Long.class));
    }
}