package com.testejava.testejavaws.service.impl;

import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.dominio.repository.UsuarioRepository;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void before() {
        initMocks(this);
    }



    @Test
    public void testGetUsuario() throws UsuarioInvalidoException {
        Usuario usuario = Usuario.builder().id(1L).name("Fred").email("Teste@teste.com.br").build();
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        final Optional<Usuario> usuarioOptional = usuarioServiceImpl.findById(1L);

        Assert.assertNotNull(usuarioOptional.get());
        verify(usuarioRepository, times(1)).findById(any(Long.class));
    }

    @Test
    public void testGetUsuarios() {
        Usuario usuario = Usuario.builder().id(1L).name("Fred").email("Teste@teste.com.br").build();
        Usuario usuario2 = Usuario.builder().id(2L).name("Luana").email("Luana@teste.com.br").build();
        when(usuarioRepository.findAll()).thenReturn(asList(usuario, usuario2));

        List<Usuario> usuarios = usuarioServiceImpl.getUsuarios();

        Assert.assertNotNull(usuarios);
        Assert.assertEquals(usuarios.size(), 2);
        verify(usuarioRepository, times(1)).findAll();
    }


    @Test
    public void testUpdateUsuario() throws UsuarioInvalidoException {
        Usuario usuario = Usuario.builder().name("Fred").email("Teste@teste.com.br").id(1L).build();
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));


        usuarioServiceImpl.updateUsuario(usuario, 1L);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testDeletarUsuario() {
        Usuario usuario = Usuario.builder().id(1L).name("Fred").email("Teste@teste.com.br").build();
        doNothing().when(usuarioRepository).deleteById(usuario.getId());
        usuarioServiceImpl.deleteUsuario(usuario.getId());
        verify(usuarioRepository, times(1)).deleteById(any(Long.class));
    }
}